import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

public class CreateIssueTests {

    private static CreateIssueUtil createIssueUtil;
    private static WebDriver driver;
    private static Util util;
    private static WebDriverWait wait;


    @BeforeEach
    public void setUp() {
        switch (System.getenv("driverType")) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
        }
        util = new Util(driver);
        wait = new WebDriverWait(driver, 10);
        createIssueUtil = new CreateIssueUtil();
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }


    @ParameterizedTest
    @MethodSource("issues")
    public void testCoalaCreateIssues(String issue) {
        String projectName = "coala";
        util.getToCreateIssue();
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);

    }

    @ParameterizedTest
    @MethodSource("issues")
    public void testJetiCreateIssues(String issue) {
        String projectName = "jeti";
        util.getToCreateIssue();
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);

    }

    @ParameterizedTest
    @MethodSource("issues")
    public void testToucanCreateIssues(String issue) {
        String projectName = "toucan";
        util.getToCreateIssue();
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);

    }

    @Test
    public void testCreateIssueGeneral(){
        int numberOfIssues = createIssueUtil.navigateAndGetNumOfIssues(driver);
        util.getToCreateIssue();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        WebElement summaryInputField =driver.findElement(By.id("summary"));
        summaryInputField.click();
        summaryInputField.sendKeys("SummaryForTest");
        driver.findElement(By.id("create-issue-submit")).click();
        int newNumberOfIssues =createIssueUtil.navigateAndGetNumOfIssues(driver);
        Assert.assertTrue(numberOfIssues == newNumberOfIssues-1);
    }



    @Test
    public void testCreateSubTask(){
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-89");
        driver.findElement(By.id("opsbar-operations_more")).click();
        WebElement createSubTask = driver.findElement(By.id("create-subtask"));
        Assert.assertNotNull(createSubTask);

    }
    @AfterEach
    public void close() {
        driver.close();
    }

    static Stream<Arguments> issues() {
        return Stream.of(Arguments.of("Story"),
                Arguments.of("Task"), Arguments.of("Bug"));
    }
}
