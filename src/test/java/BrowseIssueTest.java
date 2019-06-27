import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BrowseIssueTest {

    private static WebDriver driver;
    private static Util util;
    private static WebDriverWait wait;

    static Stream<Arguments> coalaUrlDataProvider() {
        return Stream.of(Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/COALA-3"));
    }

    static Stream<Arguments> jetiUrlDataProvider() {
        return Stream.of(Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/JETI-3"));
    }

    static Stream<Arguments> toucanUrlDataProvider() {
        return Stream.of(Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-1"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-2"),
                Arguments.of("https://jira.codecool.codecanvas.hu/browse/TOUCAN-3"));
    }

    @BeforeAll
    public static void setUp(){
        driver = DriverFactory.getDriver(System.getenv("driverType"));
        wait = new WebDriverWait(driver, 10);
        util = new Util(driver);
        util.navigateToPage();
        util.loginToSite(System.getenv("jiraUser"), System.getenv("jiraPass"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("find_link")));
    }

    @Order(1)
    @Test
    public void searchForIssuesTest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("find_link"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issues_new_search_link_lnk"))).click();
        WebElement input = driver.findElement(By.id("searcher-query"));
        input.click();
        input.sendKeys("none");
        driver.findElement(By.xpath("//*[@original-title='Search for issues']")).click();
        wait.until(ExpectedConditions.textToBe(By.id("summary-val"), "none"));
        WebElement summary = driver.findElement(By.id("summary-val"));
        Assert.assertEquals("none", summary.getText());
    }

    @Order(2)
    @ParameterizedTest
    @MethodSource("coalaUrlDataProvider")
    public void coalaProjectContainsThreeIssue(String url) {
        driver.get(url);
        WebElement summary = driver.findElement(By.id("summary-val"));
        Assert.assertNotNull(summary);
    }

    @Order(3)
    @ParameterizedTest
    @MethodSource("jetiUrlDataProvider")
    public void jetiProjectContainsThreeIssue(String url) {
        driver.get(url);
        WebElement summary = driver.findElement(By.id("summary-val"));
        Assert.assertNotNull(summary);
    }

    @Order(4)
    @ParameterizedTest
    @MethodSource("toucanUrlDataProvider")
    public void toucanProjectContainsThreeIssue(String url) {
        driver.get(url);
        WebElement summary = driver.findElement(By.id("summary-val"));
        Assert.assertNotNull(summary);
    }

    @AfterAll
    public static void tearDown(){
        util.closeWindow();
    }
}