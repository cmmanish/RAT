package example;

/**
 * Created by mmadhusoodan on 1/27/15.
 */

import org.apache.log4j.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionExample {

    private static HttpURLConnectionExample instance;

    public static synchronized HttpURLConnectionExample getInstance() {
        if (instance == null) {
            instance = new HttpURLConnectionExample();
        }
        return instance;
    }

    private final static Logger log = Logger.getLogger(MeshServicesUsingCURL.class);
    private String envelopeHeaders = " -H 'X-BP-Envelope: EgIIARoBMQ==' ";

    private String tokenHeaders = " -H 'X-BP-Token: MSwxLE5Lb05Uc0ZEbDA2RnBGYjlVUGVMcWc9PSxzRXkzNFJXQTh0SjJzQXVYc1Z6akswY0h4UDdqMzJ3OEZ2MHlBN1E5SUxRWmhXRklCV1hRZjEyZlV6T1ZoK0duNlZnTUhvL2N6cVZRcjlXeGdZWThzTmkrSWo1azVVZW92c0NpdkhkQ05HYkVnR0prQWQrM2NoSU5DMGFUZ1c0MDJGUjBpZU81djdFRGx4RS9xM0NKdEhBRElieVJEQUhPSWVMNEhtb3IxVUk9' ";
    private String jsonHeaders = " -H 'Content-Type: application/json' " +
            " -H 'Accept': application/json' ";

    private String jsonEnvelopeHeaders = jsonHeaders + envelopeHeaders;
    private String jsonTokenEnvelopeHeaders = jsonHeaders + tokenHeaders + envelopeHeaders;
    private String BaseURL = "https://accounts.test.blackpearlsystems.net";
    private String accountsByEmail = "/accounts/emails/";

    private final String USER_AGENT = "Mozilla/5.0";

    public static void main(String[] args) throws Exception {

        HttpURLConnectionExample http = new HttpURLConnectionExample();

        log.info("Testing 1 - Send Http GET request meshIDForAccount()");
        String encodedEmail = "portal_test_01%40lyveminds.com";
        HttpURLConnectionExample.getInstance().accountExists(encodedEmail);

        //log.info("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }

    public void accountExists(String encodedEmail) throws IOException {

        String URI = BaseURL + accountsByEmail + encodedEmail;

        URL obj = new URL(URI);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        log.info("\nSending 'GET' request to URL : " + URI);
        log.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        log.info(response.toString());


    }

    // HTTP GET request
    private void sendGet() throws Exception {

        String url = "http://www.google.com/search?q=mkyong";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        log.info("\nSending 'GET' request to URL : " + url);
        log.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        log.info(response.toString());

    }

    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        log.info("\nSending 'POST' request to URL : " + url);
        log.info("Post parameters : " + urlParameters);
        log.info("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        log.info(response.toString());

    }

}