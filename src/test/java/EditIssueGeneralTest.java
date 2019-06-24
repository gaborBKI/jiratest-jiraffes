import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class EditIssueGeneralTest {

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
        util.navigateToPage();

    }

    @Test
    public void editPageOpensTest() {

    }

    @Test
    public void editToucanGeneralTest() {

    }

    @Test
    public void editCoalaGeneralTest() {

    }

    @Test
    public void editJetiGeneralTest() {

    }

    @AfterEach
    public void tearDown() {
        util.closeWindow();
    }

}
