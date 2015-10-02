package example;

/**
 * Created by mmadhusoodan on 12/11/14.
 */

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FacebookRestClient {
    private final static Logger log = Logger.getLogger(FacebookRestClient.class);

    private static FacebookRestClient instance;
    private String BaseURL = "https://graph.facebook.com/";
    private String accessToken = "CAAEz5omIYVQBANpTw2UPfEm1pXU6xZBO4UaNZBN4CyFVgTH1XZC5xPPLFZAq99rhyoy4ZAHwhSK6hw8dZBsNl58tHrZBK2dylfc0ZBiM0ZAKNO7ktE14MFbcWaT7qSaVXL9DI6W8l5Qb63jSuowdKYZBorMa5yoS7UV8Dms4otNFxCpr3k3cjpVYTS7GDZBoFaJbwniJXPiYn0iOLf2yUHXrcnr";
    private String query = "search?limit=100&q=India&type=adcity&";
    private String DATA = "data";

    private FacebookRestClient() {
    }

    public static synchronized FacebookRestClient getInstance() {

        if (instance == null) {
            instance = new FacebookRestClient();
        }

        return instance;
    }

    public void sendGet() {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper jsonMapper = new ObjectMapper();

        Map<String, ArrayList<HashMap<String, String>>> JSONResponse = new HashMap<String, ArrayList<HashMap<String, String>>>();
        ArrayList<HashMap<String, String>> cityArrayList = new ArrayList<HashMap<String, String>>();
        String URI = BaseURL + query + accessToken;
        try {
            String response = restTemplate.getForObject(URI, String.class);
            JSONResponse = jsonMapper.readValue(response, HashMap.class);
            cityArrayList = JSONResponse.get(DATA);

            for (int i = 0; i < cityArrayList.size(); i++) {
                String city = cityArrayList.get(i).get("name");
                log.info(city);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {

        FacebookRestClient.getInstance().sendGet();

    }
}