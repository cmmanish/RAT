package com.lyve.test;

import com.lyve.service.AccountsService;
import com.lyve.service.SarkService;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by mmadhusoodan on 3/9/15.
 */
public class TestSarkService {

    Logger log = Logger.getLogger(TestSarkService.class);

    @Test
    public void TestGetDeviceDetailsFromSarkClientWithEmail() {

        //String email = "mmadhusoodan+emptymesh@lyveminds.com";
        //String email = "mmadhusoodan+multiple@lyveminds.com";
        //String email = "mmadhusoodan+morgan@lyveminds.com";
        //String email = "mmadhusoodan+ita@lyveminds.com";
        String email = "dogfood@lyveminds.com";

        SarkService.getInstance().getAgentFromSarkClientWithEmail(email);
    }

    @Test
    public void TestGetDeviceDetailsFromSarkClientWithMeshId() {

        //String meshId = "DE12719E-F84F-484A-B7BB-3B49D11C1874";
        String meshId = "EE9A3D25-D9AC-4763-B244-887FCE7183C2";
        SarkService.getInstance().getDeviceDetailsFromSarkClientWithMeshId(meshId);
    }

    @Test
    public void TestDeleteMeshUsingSarkClientWithMeshId() {

        try {
            String email = "a@lyveminds.com";
            String meshId = AccountsService.getInstance().getMeshIdFromEmail(email);
            SarkService.getInstance().deleteMeshUsingMeshId(meshId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
