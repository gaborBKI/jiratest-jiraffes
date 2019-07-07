import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateIssueTests {

    private static CreateIssueUtil createIssueUtil;
    private static WebDriver driver;
    private static Util util;
    private static WebDriverWait wait;

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
        wait = new WebDriverWait(driver, 10);
        createIssueUtil = new CreateIssueUtil();
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
        util.getToCreateIssue();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("project-field")));
        WebElement projectInputBox = driver.findElement(By.id("project-field"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", projectInputBox);
    }

    @Order(1)
    @ParameterizedTest
    @MethodSource("issues")
    public void testCoalaCreateIssues(String issue) {
        String projectName = "coala";
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);
      

    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("issues")
    public void testToucanCreateIssues(String issue) {
        String projectName = "toucan";
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);

    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("issues")
    public void testJetiCreateIssues(String issue) {
        String projectName = "jeti";
        util.selectProject(projectName);
        util.selectIssue(issue);

        String issues = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String activeIssue = createIssueUtil.parseJson(issues);
        Assert.assertEquals(issue, activeIssue);

    }

    @Order(4)
    @Test
    public void testCreateIssueGeneral(){
        createIssueUtil.navigateToIssues(driver);
        int numberOfIssues = createIssueUtil.getNumOfIssues(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary-val")));
        util.getToCreateIssue();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        WebElement summaryInputField =driver.findElement(By.id("summary"));
        summaryInputField.click();
        summaryInputField.sendKeys("SummaryForTest");
        driver.findElement(By.id("create-issue-submit")).click();
        createIssueUtil.navigateToIssues(driver);
        int newNumberOfIssues =createIssueUtil.getNumOfIssues(driver);
        Assert.assertEquals(numberOfIssues, newNumberOfIssues - 1);
    }


    @Order(5)
    @Test
    public void testCreateSubTask(){
        driver.get("https://jira.codecool.codecanvas.hu/browse/JETI-89");
        driver.findElement(By.id("opsbar-operations_more")).click();
        WebElement createSubTask = driver.findElement(By.id("create-subtask"));
        Assert.assertNotNull(createSubTask);

    }

    @AfterAll
    public static void close() {
        driver.close();
    }

    static Stream<Arguments> issues() {
        return Stream.of(Arguments.of("Story"),
                Arguments.of("Task"), Arguments.of("Bug"));
    }
}
