package com.lyve.test;

import com.lyve.service.AccountsService;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by mmadhusoodan on 3/9/15.
 */
public class TestAccountsService {

    @Test
    public void TestPrintAccountDetailFromEmail() {

        try {
            //String email = "mmadhusoodan+emptymesh@lyveminds.com";
            //String email = "mmadhusoodan+multiple@lyveminds.com";
            //String email = "mmadhusoodan+events@lyveminds.com";
            String email = "mmadhusoodan+sxsw@lyveminds.com";

            AccountsService.getInstance().printAccountDetailFromEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGetMeshIdFromEmail() {

        String email = "mmadhusoodan+ita_avery@lyveminds.com";
        try {

            String AmeshId = "DAA6E9BD-A504-4970-980D-3B00F615A7BC";
            String EMeshId = AccountsService.getInstance().getMeshIdFromEmail(email);
            Assert.assertEquals("MeshId's don't match", AmeshId, EMeshId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestAccountDetailFromMeshId() {

        try {
            String meshId = "0A6F37CC-DC01-4F35-930A-2EC426FFC35A";
            AccountsService.getInstance().printAccountDetailFromMeshId(meshId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
