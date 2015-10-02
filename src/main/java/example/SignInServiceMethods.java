package example;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mmadhusoodan on 12/18/14.
 */
public class SignInServiceMethods {

    private final static Logger log = Logger.getLogger(SignInServiceMethods.class);
    private static SignInServiceMethods instance;
    private String BaseURL = "https://accounts.test.blackpearlsystems.net";
    private String accountsByEmail = "/accounts/emails/";

    public static synchronized SignInServiceMethods getInstance() {
        if (instance == null) {
            instance = new SignInServiceMethods();
        }
        return instance;
    }


    public void accountExists(String encodedEmail) {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper jsonMapper = new ObjectMapper();

        Map<String, ArrayList> JSONResponse;

        String URI = BaseURL + accountsByEmail + encodedEmail;
        try {
            String response = restTemplate.getForObject(URI, String.class);
            JSONResponse = jsonMapper.readValue(response, HashMap.class);
            String accountId = JSONResponse.get("account_id").toString();
            log.info(accountId);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {

        String encodedEmail = "gatling_test_01@lyveminds.com";
        SignInServiceMethods.getInstance().accountExists(encodedEmail);


    }
}
