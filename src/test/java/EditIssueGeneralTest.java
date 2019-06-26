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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

public class EditIssueGeneralTest {

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
        util.loginToSite(System.getenv("jiraUser"), System.getenv(("jiraPass")));
    }

    @ParameterizedTest
    @MethodSource("urlsStream")
    public void editPageOpensTest(String urls) {
        driver.navigate().to(urls);
        driver.manage().window().maximize();
        System.out.println("Page opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editButton.click();
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    static Stream<Arguments> urlsStream() {
        return Stream.of(Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-3"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-3"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-3"));
    }

    @AfterEach
    public void tearDown() {
        util.closeWindow();
    }

}