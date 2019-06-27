import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public LogoutUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void reLogin(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        driver.findElement(By.id("login-form-username")).sendKeys(username);
        driver.findElement(By.id("login-form-password")).sendKeys(password);
        driver.findElement(By.id("login-form-submit")).click();
    }

}
