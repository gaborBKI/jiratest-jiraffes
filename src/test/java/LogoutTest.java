import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest {

    private static WebDriver driver;
    private static Util util;
    private static WebDriverWait wait;

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
        wait = new WebDriverWait(driver, 10);
    }

    @Order(1)
    @Test
    public void logOutTest(){
        WebElement userMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        userMenu.click();
        WebElement logOutButton = driver.findElement(By.id("log_out"));
        logOutButton.click();
        String userOptionText = driver.findElement(By.id("user-options")).getText();
        Assert.assertEquals("Log In", userOptionText);
    }

    @Order(2)
    @Test
    public void backOnLogOutPage(){
        driver.navigate().back();
        WebElement loginContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-container")));
        Assert.assertNotNull(loginContainer);
    }

    @AfterAll
    public static void tearDown(){
        util.closeWindow();
    }

}
