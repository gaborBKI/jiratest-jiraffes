import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;


public class BrowseIssueTest {

    private static WebDriver driver;
    private static Util util;

    @BeforeEach
    public void setUp(){
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
    }

    @Test
    public void searchForIssuesTest() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        util.loginToSite(System.getenv("jiraUser"), System.getenv("jiraPass"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("find_link"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issues_new_search_link_lnk"))).click();
        WebElement input = driver.findElement(By.id("searcher-query"));
        input.click();
        input.sendKeys("none");
        driver.findElement(By.xpath("//*[@original-title='Search for issues']")).click();
        driver.manage().timeouts().pageLoadTimeout(100, SECONDS);
        WebElement summary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals("none", summary.getText());
    }

    //@AfterEach
    //public void tearDown(){
        //util.closeWindow();
    //}

}