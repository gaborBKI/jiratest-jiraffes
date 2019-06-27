import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowseProjectsTest {

    private static WebDriver driver;
    private static Util util;
    private static BrowseProjectsUtil browseProjectsUtil;

    @BeforeAll
    public static void setUp() {
        switch (System.getenv("driverType")) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
        browseProjectsUtil = new BrowseProjectsUtil(driver);
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }

    @Test
    public void projectListAppears() {
        browseProjectsUtil.navigateToAllProjects();
        WebElement projectList = driver.findElement(By.id("browse-projects-page"));
        Assert.assertNotNull(projectList);
    }

    @Test
    public void projectPageValid(){
        String expectedURL = "https://jira.codecool.codecanvas.hu/projects/MTP/issues";
        browseProjectsUtil.checkForProjectInList("Main Testing Project");
        Assert.assertEquals(expectedURL, driver.getCurrentUrl());
    }

    @AfterAll
    public static void tearDown(){
        driver.close();
    }

}
