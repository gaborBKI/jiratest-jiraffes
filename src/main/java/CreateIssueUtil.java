import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    public int navigateAndGetNumOfIssues(WebDriver driver){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-89?filter=-2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("issue-list")));
        return driver.findElement(By.className("issue-list")).findElements(By.tagName("li")).size();
    }
}
