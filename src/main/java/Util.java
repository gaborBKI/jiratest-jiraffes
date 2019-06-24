import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Util {

    private WebDriver driver;

    public Util(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPage(){
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.manage().window().maximize();
    }

    public void loginToSite(){
        WebElement userField = driver.findElement(By.id("login-form-username"));
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        userField.sendKeys(System.getenv("jiraUser"));
        passwordField.sendKeys(System.getenv("jiraPass"));
        loginButton.click();
    }

}
