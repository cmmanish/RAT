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
 * Created by mmadhusoodan on 3/12/15.
 */
public class MCPService {

    private static Logger log = Logger.getLogger(DelphiService.class);
    private String Root = "https://mcp.dogfood.blackpearlsystems.net";
    private String BasePath = Root + QaConstants.SLASH + "mcp" + QaConstants.SLASH + "rest" + QaConstants.SLASH + "v1" + QaConstants.SLASH;
    private AgentObject agentObject = new AgentObject();
    private ArrayList<JSONObject> agentObjectList;
    private static MCPService instance;
    private JSONParser parser = new JSONParser();

    //protobufTokenHeaders = protobufHeaders ++ tokenHeaders ++ envelopeHeaders
    public static synchronized MCPService getInstance() {

        if (instance == null) {
            instance = new MCPService();
        }
        return instance;
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

}

