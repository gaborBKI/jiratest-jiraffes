import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssueSpecificTest {

    private static WebDriver driver;
    private static Util util;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setUp() {
        driver = DriverFactory.getDriver(System.getenv("driverType"));
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
        wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
    }

    @BeforeEach
    public void init() {
        util.navigateToPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        driver.findElement(By.id("edit-issue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
    }

    @Test
    public void deleteRequiredFieldsTest() {
        WebElement summary = driver.findElement(By.id("summary"));
        summary.click();
        summary.clear();
        driver.findElement(By.id("edit-issue-submit")).click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        WebElement issueType = driver.findElement(By.id("issuetype-field"));
        issueType.click();
        issueType.clear();
        Assert.assertNotNull(error);
        Assert.assertNotNull(issueType);
    }

    @Test
    public void deleteIssueSummary() {
        WebElement issueFieldEditable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary")));
        issueFieldEditable.click();
        issueFieldEditable.clear();
        driver.findElement(By.id("edit-issue-submit")).click();
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        Assert.assertNotNull(error);
    }

    @Test
    public void editDescription() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aui-uid-1")));
        driver.findElement(By.id("aui-uid-1")).click();
        WebElement descriptionBox = driver.findElement(By.id("description-wiki-edit"));
        descriptionBox.click();
        WebElement text = driver.findElement(By.id("description"));
        text.clear();
        text.sendKeys("Test");
        WebElement editIssueSubmit = driver.findElement(By.id("edit-issue-submit"));
        editIssueSubmit.click();
        String descriptionText = driver.findElement(By.id("description-val")).getText();
        Assert.assertEquals("Test", descriptionText);
    }

    @Test
    public void editIssueType() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"edit-issue-dialog\"]/div[2]/div[1]/div/form")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuetype-field")));
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"issuetype-field\"]"));
        dropdown.click();
        dropdown.sendKeys("Task");
        WebElement editIssueSubmit = driver.findElement(By.id("edit-issue-submit"));
        editIssueSubmit.click();
        String issueTypeText = driver.findElement(By.id("type-val")).getText();
        Assert.assertEquals("Task", issueTypeText);
    }

    @AfterAll
    public static void tearDown() {
        util.closeWindow();
    }

}
