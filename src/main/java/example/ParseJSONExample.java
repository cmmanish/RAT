package example;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by mmadhusoodan on 3/2/15.
 */
public class ParseJSONExample {
    private static Logger log = Logger.getLogger(ParseJSONExample.class);

    private static JSONParser parser = new JSONParser();

    public static String getBlobDetailFromAgent(JSONObject jAgent) {

        log.info(jAgent.get("blob_inventory_detail"));
        JSONObject blobObject = (JSONObject) jAgent.get("blob_inventory_detail");

        //original
        JSONObject Original = (JSONObject) blobObject.get("ORIGINAL");
        JSONObject OriginalImage = (JSONObject) Original.get("IMAGE");
        JSONObject OriginalVideo = (JSONObject) Original.get("VIDEO");

        JSONObject OriginalImageExternal = (JSONObject) OriginalImage.get("EXTERNALLY_MANAGED");
        JSONObject OriginalVideoExternal = (JSONObject) OriginalVideo.get("EXTERNALLY_MANAGED");

        Long imageCount = (Long) OriginalImageExternal.get("count");
        Long videoCount = (Long) OriginalVideoExternal.get("count");

        log.info("Image count: " + imageCount);
        log.info("Video count: " + videoCount);

        return "imageCount=" + imageCount + "&videoCount=" + videoCount;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String jsonData = "";
        String imageVideoCount = "";
        BufferedReader br = null;
        try {
            String line;
            //br = new BufferedReader(new FileReader("meshStatsMultiple.json"));
            br = new BufferedReader(new FileReader("meshStatsSingle.json"));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonData);
            JSONObject levelObject1 = (JSONObject) obj;
            log.info("mesh_status: " + levelObject1.get("mesh_status"));
            Object agentStatsJSONObject = levelObject1.get("agent_stats");

            if (agentStatsJSONObject instanceof Map) {
                JSONObject jsonObj = (JSONObject) agentStatsJSONObject;
                Map map = (Map) agentStatsJSONObject;
                Set keySet = map.keySet();
                log.info("Agent Count : " + keySet.size());

                for (Object agentKey : keySet) {
                    JSONObject jAgent = (JSONObject) jsonObj.get(agentKey);
                    imageVideoCount = getBlobDetailFromAgent(jAgent);
                    log.info(imageVideoCount.split("&")[0].split("=")[1]);
                    log.info(imageVideoCount.split("&")[1].split("=")[1]);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}