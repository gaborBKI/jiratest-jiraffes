import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseProjectsTest {

    private static WebDriver driver;
    private static Util util;

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
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }

    @Test
    public void projectListAppears() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/nav")));
        WebElement projectsDropdown = driver.findElement(By.id("browse_link"));
        projectsDropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("project_view_all_link_lnk")));
        WebElement viewAllButton = driver.findElement(By.id("project_view_all_link_lnk"));
        viewAllButton.click();
        WebElement projectList = driver.findElement(By.id("browse-projects-page"));
        Assert.assertNotNull(projectList);
    }

    @Test
    public void projectPageValid(){
        String expectedURL = "https://jira.codecool.codecanvas.hu/projects/MTP/issues";
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@original-title='Main Testing Project']")));
        WebElement mainTestingProject = driver.findElement(By.xpath("//*[@original-title='Main Testing Project']"));
        mainTestingProject.click();
        Assert.assertEquals(expectedURL, driver.getCurrentUrl());
    }

}
