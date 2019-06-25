import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EditIssueSpecificTest {

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

    @Test
    public void deleteIssueSummary() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement elementMainPage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("header-details-user-fullname")));
        util.navigateToPage("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        WebElement issueField = driver.findElement(By.id("summary-val"));
        issueField.click();
        WebElement waitForEditable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#summary")));
        WebElement issueFieldEditable = driver.findElement(By.cssSelector("#summary"));
        issueFieldEditable.clear();
        WebElement waitForError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
        WebElement error = driver.findElement(By.className("error"));
        Assert.assertNotNull(error);
    }

    @Test
    public void editDescription() {
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
        WebElement description = driver.findElement(By.id("description-wiki-edit"));
        description.click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        description.clear();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        description.sendKeys("Test");
        WebElement editIssueSubmit = driver.findElement(By.id("edit-issue-submit"));
        editIssueSubmit.click();
    }

    @AfterEach
    public void tearDown(){
        util.closeWindow();
    }

}
