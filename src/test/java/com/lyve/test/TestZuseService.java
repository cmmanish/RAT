package com.lyve.test;

import com.lyve.service.AccountsService;
import com.lyve.service.ZuseService;
import org.junit.Test;

/**
 * Created by mmadhusoodan on 3/10/15.
 */
    public class TestZuseService {

    @Test
    public void TestGetAgentPresenceObjectFromZuseClientWithMeshId() {

        String meshId = "EE9A3D25-D9AC-4763-B244-887FCE7183C2";

        try {
            ZuseService.getInstance().getAllAgentsPresenceObjectFromZuseClientWithMeshId(meshId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetAgentPresenceObjectFromZuseClientWithEmail() {

        //String email = "mmadhusoodan+emptymesh@lyveminds.com";
        //String email = "mmadhusoodan+multiple@lyveminds.com";
        //String email = "mmadhusoodan+morgan@lyveminds.com";
        //String email = "mmadhusoodan+ita@lyveminds.com";
        String email = "mmadhusoodan+avery@lyveminds.com";
        try {
            String meshId = AccountsService.getInstance().getMeshIdFromEmail(email);
            ZuseService.getInstance().getAllAgentsPresenceObjectFromZuseClientWithMeshId(meshId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}