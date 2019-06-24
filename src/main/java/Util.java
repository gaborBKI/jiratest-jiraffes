import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

    private WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPage(){
        System.setProperty(System.getenv("webDriver"), System.getenv("driverLocation"));
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.manage().window().maximize();
    }

    public void loginToSite(String username, String password){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        WebElement userButton = driver.findElement(By.id("user-options"));
        WebElement userField = driver.findElement(By.id("login-form-username"));
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void closeWindow(){
        driver.close();
    }

}
