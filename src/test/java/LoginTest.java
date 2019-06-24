import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {

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
    }

    @Test
    public void happyPathTest(){
        util.navigateToPage();
        util.loginToSite();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("user-options")));
        WebElement userButton = driver.findElement(By.id("user-options"));
        Assert.assertNotNull(userButton);
    }

}
