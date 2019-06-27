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
    private static LogoutUtil logoutUtil;

    @BeforeEach
    public void setUp(){
        driver = DriverFactory.getDriver(System.getenv("driverType"));
        wait = new WebDriverWait(driver, 10);
        logoutUtil = new LogoutUtil(driver);
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }

    @Order(1)
    @Test
    public void logOutTest(){
        util.logoutOfSite();
        String userOptionText = driver.findElement(By.id("user-options")).getText();
        Assert.assertEquals("Log In", userOptionText);
    }

    @Order(2)
    @Test
    public void reLogin(){
        util.logoutOfSite();
        WebElement reLoginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/login.jsp']")));
        reLoginLink.click();
        logoutUtil.reLogin(System.getenv("jiraUser"), System.getenv("jiraPass"));
        WebElement userButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        Assert.assertNotNull(userButton);
    }

    @Order(3)
    @Test
    public void backOnLogOutPage(){
        util.logoutOfSite();
        driver.navigate().back();
        WebElement loginContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-container")));
        Assert.assertNotNull(loginContainer);
    }

    @AfterEach
    public void tearDown(){
        util.closeWindow();
    }

}
