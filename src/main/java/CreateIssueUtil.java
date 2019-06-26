import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateIssueUtil {

    public String parseJson(String jsonText) throws JSONException{

        final JSONObject obj = new JSONObject(jsonText.substring(1, jsonText.length()-1));
        final JSONArray itemArray = obj.getJSONArray("items");
        String activeField = "";
        for(int i=0; i<itemArray.length(); i++){
            JSONObject item = itemArray.getJSONObject(i);
            boolean selected = item.getBoolean("selected");
            if (selected){
                activeField = item.getString("label");
            }
        }
        return activeField;
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
