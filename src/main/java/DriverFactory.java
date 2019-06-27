import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    public static WebDriver getDriver(String type) {
        switch (type) {
            case "Chrome":
                return new ChromeDriver();
            case "Firefox":
                return new FirefoxDriver();
        }
        return null;
    }

}
