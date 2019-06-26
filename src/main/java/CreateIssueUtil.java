import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateIssueUtil {

    public String parsejson(String jsonText) throws JSONException{

        final JSONObject obj = new JSONObject(jsonText.substring(1, jsonText.length()-1));
        final JSONArray itemArray = obj.getJSONArray("items");
        final String label = itemArray.getJSONObject(0).getString("label");
        return label;
    }

    public boolean checkForMatch(String wordToCheck,String fromText){
        for (String word:fromText.split(" ")) {
            if(word.toLowerCase().equals(wordToCheck.toLowerCase())){
                return true;
            }
        }
        return false;
    }
}
