package com.lyve.service;

/**
 * Created by mmadhusoodan on 2/13/15.
 */

import com.lyve.service.object.AgentObject;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class DelphiService extends AbsractServicesBaseClass {
    private static Logger log = Logger.getLogger(DelphiService.class);

    private static DelphiService instance;
    AgentObject agentObject;
    ArrayList<AgentObject> agentObjectList;
    private String BaseURL = "https://delphi.dogfood.blackpearlsystems.net/";

    public static synchronized DelphiService getInstance() {

        if (instance == null) {
            instance = new DelphiService();
        }
        return instance;
    }

    public AgentObject getAgentObjectFromAgent(JSONObject jAgent, Object OAgentId) {

        agentObject = new AgentObject();
        agentObject.agentId = (String) OAgentId;
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

    public ArrayList<AgentObject> getAgentObjectListFromMeshId(CloseableHttpClient httpclient, String meshId) throws Exception {

        String URL = BaseURL + "delphi/rest/v2/meshstats/" + meshId;
        agentObjectList = new ArrayList<AgentObject>();
        agentObject = new AgentObject();
        String resultJSON = executeGET(httpclient, URL);
        //String resultJSON = getJSONFromFile("meshStatsMultiple.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(resultJSON);
        JSONObject level1Object = (JSONObject) obj;
        log.info("mesh_status: " + level1Object.get("mesh_status"));
        JSONObject agentStatsJSONObject = (JSONObject) level1Object.get("agent_stats");

        if (agentStatsJSONObject instanceof Map) {
            Set AgentkeySet = agentStatsJSONObject.keySet();
            log.info("Agent Count : " + AgentkeySet.size());

            for (Object agentId : AgentkeySet) {
                JSONObject jsonAgent = (JSONObject) agentStatsJSONObject.get(agentId);
                agentObject = getAgentObjectFromAgent(jsonAgent, agentId);
                agentObjectList.add(agentObject);
            }
        }
        return agentObjectList;
    }

    public ArrayList<String> getAgentListWithMeshId(String meshId) {
        ArrayList<String> agentList = new ArrayList<String>();
        //takes meshDID, gets the AgentID as a ArrayList
        try {
            // Trust all certs
            agentObjectList = DelphiService.getInstance().getAgentObjectListFromMeshId(httpclient, meshId);

            for (AgentObject agentObject : agentObjectList) {
                log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                log.info("Agent ID: " + agentObject.agentId);
                agentList.add(agentObject.agentId);
                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentList;
    }

    public ArrayList<AgentObject> getAllAgentDetailsFromDelphiClientWithEmail(String email) {
        //takes email, gets the mesh_ids and gets agentObjectList which can then be iterated for each AgentObject
        try {
            Map<String, ArrayList> accountServiceMap = AccountsService.getInstance().getAccountDetailsObjectFromEmail(httpclient, email);
            if ((accountServiceMap.get("code")) != null) {
                log.info(accountServiceMap.get("message"));
            } else {
                ArrayList<String> meshIDs = accountServiceMap.get("mesh_ids");
                if (meshIDs.size() != 0) {
                    ArrayList<AgentObject> agentObjectList = DelphiService.getInstance().getAgentObjectListFromMeshId(httpclient, meshIDs.get(0));
//                    AgentObject agentObject = getEachAgentDetailsFromAgentObjectList(agentObjectList);

                }
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObjectList;
    }

    public ArrayList<AgentObject> getAllAgentDetailsFromDelphiClientWithMeshId(String meshId) {
        //takes mesh_ids and gets agentObjectList which can then be iterated for each AgentObject
        try {
            agentObjectList = DelphiService.getInstance().getAgentObjectListFromMeshId(httpclient, meshId);

//            AgentObject agentObject = getEachAgentDetailsFromAgentObjectList(agentObjectList);
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObjectList;
    }

    public AgentObject getEachAgentDetailsFromAgentObjectList(ArrayList<AgentObject> agentObjectList) {

        try {
            for (AgentObject agentObject : agentObjectList) {
                log.info("--------------------------------------");
                log.info("Agent Was Online: " + agentObject.wasOnline);
                log.info("Agent Last Seen: " + agentObject.lastSeen);
                log.info("Agent Type: " + agentObject.deviceClass);
                log.info("Agent ID: " + agentObject.agentId);
                log.info("Agent Image count: " + agentObject.imageCount);
                log.info("Agent Video count: " + agentObject.videoCount);
                log.info("--------------------------------------");
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObject;
    }

}