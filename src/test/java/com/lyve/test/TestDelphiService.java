package com.lyve.test;

import com.lyve.service.DelphiService;
import com.lyve.service.object.AgentObject;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by mmadhusoodan on 3/9/15.
 */
public class TestDelphiService {

    Logger log = Logger.getLogger(TestDelphiService.class);


    @Test
    public void TestGetAgentFromDelphiClientWithEmail() {

        //String email = "mmadhusoodan+emptymesh@lyveminds.com";
        //String email = "mmadhusoodan+multiple@lyveminds.com";
        //String email = "mmadhusoodan+avery@lyveminds.com";
        //String email = "mmadhusoodan+ita@lyveminds.com";
        String email = "dogfood@lyveminds.com";

        ArrayList<AgentObject> allAgentsObjectList = DelphiService.getInstance().getAllAgentDetailsFromDelphiClientWithEmail(email);

        try {
            for (AgentObject agentObject : allAgentsObjectList) {
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

    }

    @Test
    public void TestGetAgentFromDelphiClientWithMeshId() {

        String meshId = "EE9A3D25-D9AC-4763-B244-887FCE7183C2";
        //String meshId = "E89EC84A-3A3E-44FD-8863-F510E2754ED0";

        ArrayList<AgentObject> allAgentsObjectList = DelphiService.getInstance().getAllAgentDetailsFromDelphiClientWithMeshId(meshId);

        try {
            for (AgentObject agentObject : allAgentsObjectList) {
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
    }


//    @Test
//    public void TestGetAgentListWithMeshId() {
//
//        String meshId = "EE9A3D25-D9AC-4763-B244-887FCE7183C2";
//        ArrayList<String> agentList = new ArrayList<String>();
//        ArrayList<String> agentObjectList = DelphiService.getInstance().getAgentListWithMeshId(meshId);
//
//        for (AgentObject agentObject : agentObjectList) {
//            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
//            log.info("Agent ID: " + agentObject.agentId);
//            agentList.add(agentObject.agentId);
//            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        }
//    }
}