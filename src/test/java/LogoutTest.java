import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutTest {

    private static WebDriver driver;
    private static Util util;

    @BeforeAll
    public static void setUp(){
        switch (System.getenv("driverType")){
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }

    @Test
    public void logOutTest(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        WebElement userMenu = driver.findElement(By.id("header-details-user-fullname"));
        userMenu.click();
        WebElement logOutButton = driver.findElement(By.id("log_out"));
        logOutButton.click();
        String userOptionText = driver.findElement(By.id("user-options")).getText();
        Assert.assertEquals("Log In", userOptionText);
    }

    @AfterEach
    public void tearDown(){
        util.closeWindow();
    }

}
