import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditIssueSpecificTest {

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
    public void deleteRequiredFieldsTest() {
        WebDriverWait waitForMainPage = new WebDriverWait(driver, 10);
        WebElement elementMainPage = waitForMainPage.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        util.navigateToPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        WebElement editButton = driver.findElement(By.id("edit-issue"));
        editButton.click();
        WebElement editIssueDialogWait = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
        WebElement summary = driver.findElement(By.id("summary"));
        summary.click();
        summary.clear();
        WebElement editIssueSubmit = driver.findElement(By.id("edit-issue-submit"));
        editIssueSubmit.click();
        WebElement waitForError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        WebElement error = driver.findElement(By.className("error"));
        WebElement issueType = driver.findElement(By.id("issuetype-field"));
        issueType.click();
        issueType.clear();
        Assert.assertNotNull(error);
        Assert.assertNotNull(issueType);
    }

}
