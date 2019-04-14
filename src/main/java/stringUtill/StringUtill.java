package stringUtill;

import org.json.JSONArray;
import org.json.JSONObject;

public class StringUtill {


    public static String getValueByJPath(JSONObject responseJson, String JPath) {
        Object obj = responseJson;

        for (String s : JPath.split("/")) {
            if (!s.isEmpty()) {
                if (!(s.contains("[") || s.contains("]"))) {
                    obj = ((JSONObject) obj).get(s);
                } else if ((s.contains("[") || s.contains("]"))) {
                    obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
                }
            }
        }
        return obj.toString();
    }
}
