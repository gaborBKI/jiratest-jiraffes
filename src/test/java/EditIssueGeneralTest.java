import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssueGeneralTest {

    private static WebDriver driver;
    private static Util util;

    @BeforeAll
    public static void setUp(){
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

    @Test
    public void editPageOpensTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        driver.manage().window().maximize();
        System.out.println("page opened");
        driver.findElement(By.linkText("Edit")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    /*
    @Test
    public void editToucanGeneralTest() {

    }

    @Test
    public void editCoalaGeneralTest() {

    }

    @Test
    public void editJetiGeneralTest() {

    }
     */

    @AfterEach
    public void tearDown() {
        util.closeWindow();
    }

}
