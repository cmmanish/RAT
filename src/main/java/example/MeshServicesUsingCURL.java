package example;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmadhusoodan on 1/23/15.
 */
public class MeshServicesUsingCURL {

    private final static Logger log = Logger.getLogger(MeshServicesUsingCURL.class);
    private String envelopeHeaders = " -H 'X-BP-Envelope: EgIIARoBMQ==' ";

    private String tokenHeaders = " -H 'X-BP-Token: MSwxLGFGUnUvOVlYWUM0R2xQWjdPT1MvbEE9PSxIdlZ1TEFLVUI4Tm4zYlBsOXFScmNPVXludVRlSVFYaUVGa0p5UDZYVWdWb3N4WU52UzZ5NTBLREtmV0VTN2JBYVdRNjZubXZ2STU4a3VOeHF3amQyRHN2dnFtNFdseTJwZzFSTjVYRVN1cUYycERWSFRYTHRTSy9FbWdzZTI1a1QyRFhMR3VITDBnc1Z4T2x1bi9Jc29EVjBwUFY5b2lzTlFNUm5NZjFwa0E9' ";
    private String jsonHeaders = " -H 'Content-Type: application/json' ";

    private String jsonEnvelopeHeaders = jsonHeaders + envelopeHeaders;
    private String jsonTokenEnvelopeHeaders = jsonHeaders + envelopeHeaders + tokenHeaders;
    private static MeshServicesUsingCURL instance;

    public static synchronized MeshServicesUsingCURL getInstance() {
        if (instance == null) {
            instance = new MeshServicesUsingCURL();
        }
        return instance;
    }

    public ArrayList<String> meshIDForAccount(String encodedEmail) throws IOException {

        String URL = "https://accounts.dogfood.blackpearlsystems.net/accounts/emails/";
        String command = "curl -v -k -X GET " + jsonEnvelopeHeaders + URL + encodedEmail;
        log.info("COMMAND:  " + command);
        String line = "";
        StringBuffer sb = new StringBuffer();
        ArrayList<String> meshIDs;
        ArrayList<String> agentList;

        Process curlProc;
        try {
            curlProc = Runtime.getRuntime().exec(command);
            DataInputStream curlIn = new DataInputStream(curlProc.getInputStream());

            while ((line = curlIn.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        String response = sb.toString();
        Map<String, ArrayList> JSONResponse;
        ObjectMapper jsonMapper = new ObjectMapper();
        JSONResponse = jsonMapper.readValue(response, HashMap.class);

        if (JSONResponse.get("code") != null) {
            log.info(JSONResponse.get("message"));
            return null;
        } else {
            log.info("account_id: " + JSONResponse.get("account_id"));
            log.info("email_primary: " + JSONResponse.get("email_primary"));
            meshIDs = JSONResponse.get("mesh_ids");
            log.info("mesh_ids: " + meshIDs);
            agentList = JSONResponse.get("agent_ids");

            for (int j = 0; j < agentList.size(); j++) {
                log.info("agent_ids:[" + j + 1 + "] " + agentList.get(j));
            }
            return meshIDs;
        }
    }

    /**
     * curl -v -k -X POST -H 'X-BP-Envelope: EgIIARoBMQ=='  -H 'Content-Type:application/json'
     * -d "{\"first_name\": \"fname\",\"last_name\": \"lname\",\"email\": \"portal_test_02@lyveminds.com\", \"password\": \"123456\"}"
     * https://accounts.test.blackpearlsystems.net/accounts
     *
     * @return
     * @throws IOException
     */

    public boolean accountCreate() throws IOException {
        //public boolean accountCreate(String firstName, String lastName, String password, String email) throws IOException {

        String json = "-d '{\"first_name\":\"fname\",\"last_name\":\"lname\",\"email\":\"portal_test_05@lyveminds.com\",\"password\":\"123456\"}'";
        //json = "-d '{ \"first_name\" : \"fname\" }'";
        String URL = " https://accounts.test.blackpearlsystems.net/accounts";
        String command = "curl -v -k -X POST " + jsonEnvelopeHeaders + json + URL;

        log.info("COMMAND:  " + command);
        String line = "";
        StringBuffer sb = new StringBuffer();
        Process curlProc;
        try {
            curlProc = Runtime.getRuntime().exec(command);
            DataInputStream curlIn = new DataInputStream(curlProc.getInputStream());
            while ((line = curlIn.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        String response = sb.toString();
        Map<String, ArrayList> JSONResponse;
        ObjectMapper jsonMapper = new ObjectMapper();
        JSONResponse = jsonMapper.readValue(response, HashMap.class);
        ArrayList<String> agentList = new ArrayList<String>();
        if (JSONResponse.get("code") != null) {
            log.info(JSONResponse.get("message"));
            return false;
        } else {
            log.info("account_id: " + JSONResponse.get("account_id"));
            log.info("email_primary: " + JSONResponse.get("email_primary"));
            log.info("mesh_ids: " + JSONResponse.get("mesh_ids"));
            agentList = JSONResponse.get("agent_ids");

            for (int j = 0; j < agentList.size(); j++) {
                log.info("agent_ids: " + j + " " + agentList.get(j));
            }
            return true;
        }

    }

    //From SARK
    public boolean getAgentsFromMeshID(String meshId) throws IOException {

        String URL = "https://sark.dogfood.blackpearlsystems.net/sark/rest/v1/" + meshId + "/agents";
        String command = "curl -v -k -X GET " + URL + jsonTokenEnvelopeHeaders;

        log.info(command);
        String line = "";
        StringBuffer sb = new StringBuffer();
        Process curlProc;
        try {

            curlProc = Runtime.getRuntime().exec(command);
            DataInputStream curlIn = new DataInputStream(curlProc.getInputStream());

            while ((line = curlIn.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e1) {

            e1.printStackTrace();
        }

        String response = sb.toString();
        log.info(response);
        return true;

    }

    //From SARK
//    public boolean getAccountsFromMeshID(String meshId) throws IOException {
//
//        String URL = "https://sark.dogfood.blackpearlsystems.net/sark/rest/v1/accounts/mesh/" + meshId;
//        String command = "curl -v -k -X GET " + jsonTokenEnvelopeHeaders + URL;
//
//        log.info("COMMAND:  " + command);
//        String line = "";
//        StringBuffer sb = new StringBuffer();
//        Process curlProc;
//        try {
//            curlProc = Runtime.getRuntime().exec(command);
//            DataInputStream curlIn = new DataInputStream(curlProc.getInputStream());
//
//            while ((line = curlIn.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e1) {
//
//            e1.printStackTrace();
//        }
//
//        String response = sb.toString();
//        log.info(response);
//        return true;
//
//    }
    //com.blackpearl.clu.v1.exception.ServiceException: category=Authentication errorCode=1 description=Empty credentials cause=unable to decode null actionCode=NO_ACTION

    public static void main(String arg[]) throws IOException {

        String encodedEmail = "a@lyveminds.com";
        ArrayList<String> meshIds= MeshServicesUsingCURL.getInstance().meshIDForAccount(encodedEmail);
        System.out.println(meshIds);
    //  String meshId =  meshIds.get(meshIds.size() - 1);
//        log.info("meshID: " + meshId);

//        MeshServicesUsingCURL.getInstance().accountCreate();
        //MeshServicesUsingCURL.getInstance().getAgentsFromMeshID("8D30F010-F1C8-4BDF-B11E-F6BC7EB511DF");

        //log.info("accountCreate() Status: "+MeshServicesUsingCURL.getInstance().accountCreate());

        //MeshServicesUsingCURL.getInstance().meshIDForAccount(encodedEmail);


        //MeshServicesUsingCURL.getInstance().getAccountsFromMeshID(meshId);


    }
}