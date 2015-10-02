package com.lyve.service;

import com.lyve.service.object.AgentObject;
import com.lyve.service.object.AgentObject.CellularNetworkInfo;
import com.lyve.service.object.AgentObject.ConnectivityStatus;
import com.lyve.service.object.AgentObject.PowerStatus;
import com.lyve.service.utils.QaConstants;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;

/**
 * Created by mmadhusoodan on 3/10/15.
 */
public class ZuseService extends AbsractServicesBaseClass {
    private static Logger log = Logger.getLogger(DelphiService.class);
    private String Root = "https://zuse.dogfood.blackpearlsystems.net";
    private String BasePath = Root + QaConstants.SLASH + "zuse" + QaConstants.SLASH + "rest" + QaConstants.SLASH + "v1" + QaConstants.SLASH;
    private AgentObject agentObject = new AgentObject();
    private ArrayList<JSONObject> agentObjectList;
    private static ZuseService instance;
    private JSONParser parser = new JSONParser();

    public static synchronized ZuseService getInstance() {

        if (instance == null) {
            instance = new ZuseService();
        }
        return instance;
    }

    public AgentObject getAgentObjectFromAgent(JSONObject jAgent) {

        agentObject = new AgentObject();
        agentObject.deviceClass = (String) jAgent.get("device_class");
        agentObject.lastSeen = getDeviceLastSeen((Long) jAgent.get("last_seen_ms"));
        agentObject.wasOnline = (boolean) jAgent.get("was_online");

        //blob_inventory_detail
        if (!jAgent.get("blob_inventory_detail").toString().contains("{}")) {
            try {
                JSONObject blobObject = (JSONObject) jAgent.get("blob_inventory_detail");

                //ORIGINAL
                JSONObject Original = (JSONObject) blobObject.get("ORIGINAL");
                if (Original != null) { //ORIGINAL

                    JSONObject OriginalImage = (JSONObject) Original.get("IMAGE");
                    JSONObject OriginalVideo = (JSONObject) Original.get("VIDEO");

                    JSONObject OriginalImageExternal = (JSONObject) OriginalImage.get("EXTERNALLY_MANAGED");

                    if (OriginalVideo != null) {
                        JSONObject OriginalVideoExternal = (JSONObject) OriginalVideo.get("EXTERNALLY_MANAGED");
                        agentObject.videoCount = (Long) OriginalVideoExternal.get("count");
                    }

                    agentObject.imageCount = (Long) OriginalImageExternal.get("count");
                }
                //ALTERNATIVE
                else {
                    JSONObject Alternative = (JSONObject) blobObject.get("ALTERNATIVE");
                    JSONObject AlternativeImage = (JSONObject) Alternative.get("IMAGE");
                    JSONObject AlternativeImageCache = (JSONObject) AlternativeImage.get("CACHE");
                    agentObject.imageCount = (Long) AlternativeImageCache.get("count");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //blobs_to_download_details
        else if (!jAgent.get("blobs_to_download_details").toString().contains("{}")) {
            try {
                JSONObject blobToDownloadObject = (JSONObject) jAgent.get("blobs_to_download_details");
                JSONObject AlternativeCount = (JSONObject) blobToDownloadObject.get("ALTERNATIVE");

                agentObject.imageCount = (Long) AlternativeCount.get("count");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return agentObject;
    }

    public AgentObject populateEachAgent(ArrayList<JSONObject> agentObjectList, int i) {

        try {
            JSONObject agentJSONObject = agentObjectList.get(i);
            JSONObject cellularNetworkInfoObject = null;

            JSONObject presenceDataObject = (JSONObject) agentJSONObject.get("presence_data");
            JSONObject connectivityStatusObject = (JSONObject) presenceDataObject.get("connectivity_status");
            JSONObject powerStatusObject = (JSONObject) presenceDataObject.get("power_status");

            if (connectivityStatusObject.get("primary_connectivity_type") != null)
                ConnectivityStatus.primaryConnectivityType = connectivityStatusObject.get("primary_connectivity_type").toString();

            if (connectivityStatusObject.get("cellular_network_info") != null) {
                cellularNetworkInfoObject = (JSONObject) connectivityStatusObject.get("cellular_network_info");
            }
            if (cellularNetworkInfoObject != null) {
                CellularNetworkInfo.carrier_name = cellularNetworkInfoObject.get("carrier_name").toString();
                CellularNetworkInfo.network_operator_name = cellularNetworkInfoObject.get("network_operator_name").toString();
                CellularNetworkInfo.carrier_mcc_mnc_code = cellularNetworkInfoObject.get("carrier_mcc_mnc_code").toString();
            }

            PowerStatus.powerSource = powerStatusObject.get("power_source").toString();
            PowerStatus.batteryStatus = powerStatusObject.get("battery_status").toString();
            PowerStatus.batteryLevel = powerStatusObject.get("battery_level").toString();

            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info("agent_id: " + agentJSONObject.get("agent_id"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObject;
    }

    public ArrayList<JSONObject> getAllAgentsPresenceObjectFromZuseClientWithMeshId(String meshId) {
        try {
            String URL = BasePath + "mesh" + QaConstants.SLASH + meshId;
            String resultJSON = executeGET(httpclient, URL);

            if (!(resultJSON.isEmpty())) {
                Object obj = parser.parse(resultJSON);
                JSONObject level1Object = (JSONObject) obj;
                String mesh_id = level1Object.get("mesh_id").toString();
                log.info("mesh_id: " + mesh_id);
                agentObjectList = (ArrayList<JSONObject>) level1Object.get("agents");

                for (int i = 0; i < agentObjectList.size() - 1; i++) {
                    populateEachAgent(agentObjectList, i);
                    log.info(ConnectivityStatus.primaryConnectivityType);
                    log.info(CellularNetworkInfo.network_operator_name);
                    log.info(CellularNetworkInfo.carrier_name);

                    log.info(PowerStatus.powerSource);
                    log.info(PowerStatus.batteryLevel);
                    log.info(PowerStatus.batteryStatus);
                    log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObjectList;
    }
}