package example;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpClientExample {

    private Logger log = Logger.getLogger(HttpClientExample.class);
    private static HttpClientExample instance;
    private String envelopeHeaders = " -H 'X-BP-Envelope: EgIIARoBMQ==' ";

    private String tokenHeaders = " -H 'X-BP-Token: MSwxLGFGUnUvOVlYWUM0R2xQWjdPT1MvbEE9PSxIdlZ1TEFLVUI4Tm4zYlBsOXFScmNPVXludVRlSVFYaUVGa0p5UDZYVWdWb3N4WU52UzZ5NTBLREtmV0VTN2JBYVdRNjZubXZ2STU4a3VOeHF3amQyRHN2dnFtNFdseTJwZzFSTjVYRVN1cUYycERWSFRYTHRTSy9FbWdzZTI1a1QyRFhMR3VITDBnc1Z4T2x1bi9Jc29EVjBwUFY5b2lzTlFNUm5NZjFwa0E9' ";
    private String jsonHeaders = " -H 'Content-Type: application/json' ";

    private String jsonEnvelopeHeaders = jsonHeaders + envelopeHeaders;
    private String jsonTokenEnvelopeHeaders = jsonHeaders + envelopeHeaders + tokenHeaders;

    private final String USER_AGENT = "Mozilla/5.0";
    HttpClient client = HttpClientBuilder.create().build();

    private HttpClientExample() {
    }

    public static synchronized HttpClientExample getInstance() {

        if (instance == null) {
            instance = new HttpClientExample();
        }

        return instance;
    }

    public void HttpGetBasics() throws Exception {
        String url = "http://www.google.com/search?q=httpClient";

        HttpGet request = new HttpGet(url);
        // add request header
        request.addHeader("User-Agent", USER_AGENT);
        HttpResponse response = client.execute(request);

        log.info("Response Code : " + response.getStatusLine().getStatusCode());
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        log.info(result);
    }

    public boolean accountExists(String encodedEmail) throws Exception {

        String URL = "https://accounts.test.blackpearlsystems.net";
        String accountsByEmail = "/accounts/emails/";

        //Protocol.registerProtocol("https",new Protocol("https", new MySSLSocketFactory(), 443));


        String URI = URL + accountsByEmail + encodedEmail;
        URI = "https://accounts.dogfood.blackpearlsystems.net/accounts/emails/mmadhusoodan+0105@lyveminds.com";
//        String command = "curl -v -k -X GET " + URL + jsonTokenEnvelopeHeaders ;
//        log.info("COMMAND:  " + command);
        HttpGet request = new HttpGet(URI);
        //request.addHeader("User-Agent", USER_AGENT);
//        request.addHeader("X-BP-Envelope","EgIIARoBMQ==");
//        request.addHeader("X-BP-Token", "MSwxLGFGUnUvOVlYWUM0R2xQWjdPT1MvbEE9PSxIdlZ1TEFLVUI4Tm4zYlBsOXFScmNPVXludVRlSVFYaUVGa0p5UDZYVWdWb3N4WU52UzZ5NTBLREtmV0VTN2JBYVdRNjZubXZ2STU4a3VOeHF3amQyRHN2dnFtNFdseTJwZzFSTjVYRVN1cUYycERWSFRYTHRTSy9FbWdzZTI1a1QyRFhMR3VITDBnc1Z4T2x1bi9Jc29EVjBwUFY5b2lzTlFNUm5NZjFwa0E9");
//        request.addHeader("Content-Type","application/json");

        try {
            HttpResponse response = client.execute(request);
            log.info("Response Code : " + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return true;
    }

//    public void givenAcceptingAllCertificates_whenHttpsUrlIsConsumed_thenException()
//            throws IOException, GeneralSecurityException {
//        TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
//            @Override
//            public boolean isTrusted(X509Certificate[] certificate, String authType) {
//                return true;
//            }
//        };
//        SSLSocketFactory sf = new SSLSocketFactory(acceptingTrustStrategy,
//                SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
//        SchemeRegistry registry = new SchemeRegistry();
//        registry.register(new Scheme("https", 8443, sf));
//        ClientConnectionManager ccm = new PoolingClientConnectionManager(registry);
//
//        DefaultHttpClient httpClient = new DefaultHttpClient(ccm);
//
//        String urlOverHttps = "https://localhost:8443/spring-security-rest-basic-auth/api/bars/1";
//        HttpGet getMethod = new HttpGet(urlOverHttps);
//        HttpResponse response = httpClient.execute(getMethod);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
//    }

//    public boolean getAgentsFromMeshID(String meshId) throws IOException {
//
//        String URL = "https://sark.dogfood.blackpearlsystems.net/sark/rest/v1/" + meshId + "/agents";
//        String command = "curl -v -k -X GET " + URL + jsonTokenEnvelopeHeaders ;
//
//        log.info("COMMAND:  " + command);
//
//        HttpClient client = new HttpClient();
//
//        HttpMethod method = new GetMethod(URL);
//        method.addRequestHeader("X-BP-Envelope","EgIIARoBMQ==");
//        method.addRequestHeader("X-BP-Token","MSwxLGFGUnUvOVlYWUM0R2xQWjdPT1MvbEE9PSxIdlZ1TEFLVUI4Tm4zYlBsOXFScmNPVXludVRlSVFYaUVGa0p5UDZYVWdWb3N4WU52UzZ5NTBLREtmV0VTN2JBYVdRNjZubXZ2STU4a3VOeHF3amQyRHN2dnFtNFdseTJwZzFSTjVYRVN1cUYycERWSFRYTHRTSy9FbWdzZTI1a1QyRFhMR3VITDBnc1Z4T2x1bi9Jc29EVjBwUFY5b2lzTlFNUm5NZjFwa0E9");
//        method.addRequestHeader("Content-Type","application/json");
//
//        try {
//            client.executeMethod(method);
//
//            if (method.getStatusCode() == HttpStatus.SC_OK) {
//
//                log.info(method.getURI());
//                String response = method.getResponseBodyAsString();
//                log.info("Response = " + response);
//            }
//        }
//
//        catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            method.releaseConnection();
//        }
//        return true;
//    }

    public static void main(String[] args) throws Exception {

        HttpClientExample.getInstance().HttpGetBasics();
        HttpClientExample.getInstance().accountExists("rpatil+p15@lyveminds.com");
        //HttpClientExample.getInstance().getAgentsFromMeshID("8D30F010-F1C8-4BDF-B11E-F6BC7EB511DF");

    }
}
