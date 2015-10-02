package com.lyve.service;

/**
 * Created by mmadhusoodan on 2/13/15.
 */

import com.lyve.service.utils.QaConstants;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.SSLContext;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AccountsService extends AbsractServicesBaseClass {
    private static Logger log = Logger.getLogger(AccountsService.class);

    private static AccountsService instance;
    private String Root = "https://accounts.dogfood.blackpearlsystems.net";
    private String BasePath = Root + QaConstants.SLASH + "accounts" + QaConstants.SLASH;

    public static synchronized AccountsService getInstance() {
        if (instance == null) {
            instance = new AccountsService();
        }
        return instance;
    }

    public Map<String, ArrayList> getAccountDetailsObjectFromEmail(CloseableHttpClient httpclient, String email) throws Exception {

        String URL = BasePath + "emails" + QaConstants.SLASH + email;

        String resultJSONString = executeGET(httpclient, URL);
        SSLContext sslcontext = buildSSLContext();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        //build a HTTP client with all the above
        httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpGet httpget = new HttpGet(URL);
        Map<String, ArrayList> AccountsJSONArrayList = null;
        ObjectMapper jsonMapper = new ObjectMapper();

        log.info("Executing Request: " + httpget.getRequestLine());

        CloseableHttpResponse httpResponse = httpclient.execute(httpget);
        try {
            HttpEntity entity = httpResponse.getEntity();

            log.info(httpResponse.getStatusLine());
            if (entity != null) {
                //log.info("Response content length: " + entity.getContentLength());
            }
            for (Header header : httpResponse.getAllHeaders()) {
                //log.info(header);
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            //log.info(result);
            AccountsJSONArrayList = jsonMapper.readValue(result.toString(), HashMap.class);
            if (AccountsJSONArrayList.get("code") != null) {
                log.info(AccountsJSONArrayList.get("message"));
                return AccountsJSONArrayList;
            } else {
                //                log.info("Account Exists ");
            }

            EntityUtils.consume(entity);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            httpResponse.close();
        }
        return AccountsJSONArrayList;
    }

    public String getAccountDetailsObjectFromMeshId(String meshId) {

        String URL = BasePath + "meshes" + QaConstants.SLASH + meshId;
        String resultJSON = "";
        try {

            resultJSON = executeGET(httpclient, URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultJSON;
    }

    public void printAccountDetailFromMeshId(String email) throws Exception {
        String resultJSON = AccountsService.getInstance().getAccountDetailsObjectFromMeshId(email);

        if (!resultJSON.isEmpty()) {
            try {

                JSONParser parser = new JSONParser();
                Object obj = parser.parse(resultJSON);
                JSONObject accounts = (JSONObject) obj;

                log.info("account_id: " + accounts.get("account_id"));
                log.info("email_primary: " + accounts.get("email_primary"));
                JSONArray meshIdList = (JSONArray) accounts.get("mesh_ids");

                log.info("mesh_ids: " + meshIdList);

                JSONArray agentList = (JSONArray) accounts.get("agent_ids");
                log.info("Agents Count: " + agentList.size());
                for (int j = 0; j < agentList.size(); j++) {
                    log.info("agent_ids:[" + j + "] " + agentList.get(j));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Map<String, ArrayList> printAccountDetailFromEmail(String email) throws Exception {
        Map<String, ArrayList> accounts = AccountsService.getInstance().getAccountDetailsObjectFromEmail(httpclient, email);
        try {

            log.info("account_id: " + accounts.get("account_id"));
            log.info("email_primary: " + accounts.get("email_primary"));
            ArrayList<String> meshIDs = accounts.get("mesh_ids");
            log.info("mesh_ids: " + meshIDs);
            ArrayList<String> agentList = accounts.get("agent_ids");
            log.info("Agents Count: " + agentList.size());
            for (int j = 0; j < agentList.size(); j++) {
                log.info("agent_ids:[" + j + "] " + agentList.get(j));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public String getMeshIdFromEmail(String email) throws Exception {
        Map<String, ArrayList> accounts = AccountsService.getInstance().getAccountDetailsObjectFromEmail(httpclient, email);
        ArrayList<String> meshIDs = null;
        try {

            log.info("account_id: " + accounts.get("account_id"));
            log.info("email_primary: " + accounts.get("email_primary"));
            meshIDs = accounts.get("mesh_ids");
            if (meshIDs.size() == 0)
                return "";
            log.info("mesh_ids: " + meshIDs);
            ArrayList<String> agentList = accounts.get("agent_ids");
            log.info("Agents Count: " + agentList.size());
            for (int j = 0; j < agentList.size(); j++) {
                log.info("agent_ids:[" + j + "] " + agentList.get(j));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return meshIDs.get(0).toString();
    }
}