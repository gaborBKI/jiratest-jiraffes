import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.stream.Stream;

public class CreateIssueTests {

    private static CreateIssueUtil createIssueUtil;
    private static WebDriver driver;
    private static Util util;

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
        Select issueBox = new Select(driver.findElement(By.className("icon aui-ss-icon noloading drop-menu")));
        String issueName = issueBox.getFirstSelectedOption().getText();
        /*String projectResultJSON = driver.findElement(By.id("issuetype-options")).getAttribute("data-suggestions");
        String parsedName = createIssueUtil.parsejson(projectResultJSON);
        Assert.assertTrue(createIssueUtil.checkForMatch(issue, parsedName));*/

    }

    @AfterEach
    public void close(){
        driver.close();
    }

    static Stream<Arguments> issues() {
        return Stream.of(Arguments.of("Story"),
                Arguments.of("Task"), Arguments.of("Bug"));
    }
}