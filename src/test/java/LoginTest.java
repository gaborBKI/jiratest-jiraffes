import org.junit.Assert;
import org.junit.jupiter.api.*;
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

import java.util.stream.IntStream;
import java.util.stream.Stream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

    private static WebDriver driver;
    private static Util util;

    static Stream<Arguments> userDataProvider() {
        return Stream.of(Arguments.of("username", "123"),
                Arguments.of(System.getenv("jiraUser"), "123"));
    }

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

    @Order(1)
    @Test
    public void wrongPasswordTest(){
        util.loginToSite(System.getenv("jiraUser"), "123");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("usernameerror")));
        WebElement error = driver.findElement(By.id("usernameerror"));
        Assert.assertNotNull(error);
    }

    @Order(2)
    @Test
    public void happyPathTest(){
        util.loginToSite(System.getenv("jiraUser"), System.getenv("jiraPass"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        WebElement userButton = driver.findElement(By.id("header-details-user-fullname"));
        Assert.assertNotNull(userButton);
    }

    /*@Order(3)
    @ParameterizedTest
    @MethodSource("userDataProvider")
    public void captchaAppearTest(String name, String password){
        IntStream.range(0, 3).forEachOrdered(i -> util.loginToSite(name, password));
        WebElement captcha = driver.findElement(By.id("captchaimg"));
        Assert.assertNotNull(captcha);
    }*/

    @AfterEach
    public void tearDown(){
        util.closeWindow();
    }

}
