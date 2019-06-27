import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

    private WebDriver driver;
    private WebDriverWait wait;

    public Util(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void navigateToPage(){
        System.setProperty(System.getenv("webDriver"), System.getenv("driverLocation"));
        driver.get("https://jira.codecool.codecanvas.hu/secure/Dashboard.jspa");
        driver.manage().window().maximize();
    }

    public void loginToSite(String username, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-form-username")));
        WebElement userField = driver.findElement(By.id("login-form-username"));
        WebElement passwordField = driver.findElement(By.id("login-form-password"));
        WebElement loginButton = driver.findElement(By.id("login"));

        userField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }

    public void logoutOfSite(){
        WebElement userMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        userMenu.click();
        WebElement logOutButton = driver.findElement(By.id("log_out"));
        logOutButton.click();
    }

    public void navigateToPage(String url){
        driver.get(url);
    }

    public void closeWindow(){
        driver.close();
    }

    public void getToCreateIssue(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_link")));
        driver.findElement(By.id("create_link")).click();
    }

    public void selectProject(String project) {
        WebElement projectInputBox = driver.findElement(By.id("project-field"));
        projectInputBox.click();
        projectInputBox.sendKeys(project + Keys.TAB);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("issuetype-field")));
    }

    public void selectIssue(String issue) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement issueInputBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
        issueInputBox.click();
        issueInputBox.sendKeys(issue + Keys.TAB);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("issuetype-field")));
    }

}
