package com.lyve.service;

/**
 * Created by mmadhusoodan on 2/13/15.
 */

import com.lyve.service.object.AgentObject;
import com.lyve.service.utils.QaConstants;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.SSLContext;
import java.util.ArrayList;
import java.util.Map;

public class SarkService extends AbsractServicesBaseClass {
    private static Logger log = Logger.getLogger(SarkService.class);

    private static SarkService instance;
    private static CloseableHttpClient httpclient;
    AgentObject agentObject;

    private String Root = "https://sark.dogfood.blackpearlsystems.net";
    private String BasePath = Root + QaConstants.SLASH + "sark" + QaConstants.SLASH + "rest" + QaConstants.SLASH + "v1";

    public static synchronized SarkService getInstance() {

        if (instance == null) {
            instance = new SarkService();
        }
        return instance;
    }

    public AgentObject getAgentDeviceDetailsWithMeshIdAndAgentId(String meshId, String agentId) {
        try {
            String URL = BasePath + "/" + meshId + "/" + "agent" + "/" + agentId;
            agentObject = new AgentObject();
            String resultJSON = executeGET(httpclient, URL);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(resultJSON);
            JSONObject level1Object = (JSONObject) obj;

            agentObject.agentId = level1Object.get("agent_id").toString();
            agentObject.displayName = level1Object.get("display_name").toString();
            agentObject.deviceClass = level1Object.get("device_class").toString();
            agentObject.deviceType = level1Object.get("device_type").toString();
            agentObject.devicePlatform = level1Object.get("device_platform").toString();
            agentObject.isReplicationTarget = (boolean) level1Object.get("is_replication_target");
            agentObject.storageCapacityTotalBytes = (long) level1Object.get("storage_capacity_total_bytes");


        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObject;
    }

    public boolean deleteMeshUsingMeshId(String meshId) {
        boolean flag = false;
        try {
            String URL = BasePath + "/" + meshId + "/" + "?really=true";
            flag = executeDelete(httpclient, URL);

        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            return flag;
        } catch (Exception e) {
            e.printStackTrace();
            return flag;
        }
        return flag;
    }

    public boolean deleteAgentsFromMeshUsingMeshId(String meshId, String agentId) {
        try {
            String URL = BasePath + "/" + meshId + "/" + "agent" + "/" + agentId;
            agentObject = new AgentObject();
            String resultJSON = executeGET(httpclient, URL);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(resultJSON);
            JSONObject level1Object = (JSONObject) obj;

        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public AgentObject getAgentFromSarkClientWithEmail(String email) {
        //takes email, gets the mesh_ids and gets agentObjectList which can then be iterated for each AgentObject
        try {
            // Trust all certs
            SSLContext sslcontext = buildSSLContext();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            //build a HTTP client with all the above
            httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

            Map<String, ArrayList> accountServiceMap = AccountsService.getInstance().getAccountDetailsObjectFromEmail(httpclient, email);
            if ((accountServiceMap.get("code")) != null) {
                log.info(accountServiceMap.get("message"));

            } else {
                ArrayList<String> meshIDs = accountServiceMap.get("mesh_ids");

                if (meshIDs.size() != 0) {
                    ArrayList<AgentObject> agentObjectList = DelphiService.getInstance().getAgentObjectListFromMeshId(httpclient, meshIDs.get(0));

                    for (AgentObject agentObject : agentObjectList) {
                        log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                        log.info("Agent Was Online: " + agentObject.wasOnline);
                        log.info("Agent Last Seen: " + agentObject.lastSeen);
                        log.info("Agent Type: " + agentObject.deviceClass);
                        log.info("Agent ID: " + agentObject.agentId);
                        log.info("Agent Image count: " + agentObject.imageCount);
                        log.info("Agent Video count: " + agentObject.videoCount);
                        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    }
                }
            }
        } catch (IndexOutOfBoundsException iob) {
            iob.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return agentObject;
    }

    public void getDeviceDetailsFromSarkClientWithMeshId(String meshId) {

        ArrayList agentList = DelphiService.getInstance().getAgentListWithMeshId(meshId);
        String agentId = "";

        for (int i = 0; i < agentList.size(); i++) {
            agentId = agentList.get(i).toString();
            AgentObject agent = SarkService.getInstance().getAgentDeviceDetailsWithMeshIdAndAgentId(meshId, agentId);

            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info(agent.agentId);
            log.info(agent.deviceClass);
            log.info(agent.devicePlatform);
            log.info(agent.displayName);
            log.info(agent.deviceType);
            log.info(agent.isReplicationTarget);
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

    }
}

