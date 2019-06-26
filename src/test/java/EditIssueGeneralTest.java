import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class EditIssueGeneralTest {

    private static WebDriver driver;
    private static Util util;

    /*
    Map<String, String> urlMap = new HashMap<>();
    urlMap.put("basic", "https://jira.codecool.codecanvas.hu/browse/SAND-40");
    urlMap.put("jeti", "https://jira.codecool.codecanvas.hu/browse/MTP-49");
    urlMap.put("coala", "https://jira.codecool.codecanvas.hu/browse/COALA-185");
    urlMap.put("toucan", "https://jira.codecool.codecanvas.hu/browse/TOUCAN-51");
    */

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
    public void editPageOpensTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        driver.manage().window().maximize();
        System.out.println("Page opened");
        driver.findElement(By.linkText("Edit")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    @Test
    public void editToucanGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/TOUCAN-51");
        driver.manage().window().maximize();
        System.out.println("Toucan project opened");
        driver.findElement(By.id("Edit")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));

    }

    @Test
    public void editCoalaGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/COALA-185");
        driver.manage().window().maximize();
        System.out.println("Jira project opened");
        driver.findElement(By.id("opsbar-edit-issue_container")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    @Test
    public void editJetiGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-49");
        driver.manage().window().maximize();
        System.out.println("Jeti project opened");
        driver.findElement(By.id("edit-issue")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

     /*

    @Test
    public void quickEditWithGreyButtonsTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        driver.manage().window().maximize();
        driver.findElement(By.id("comment-issue")).click();
        //WebElement commentTextarea = driver.findElement(By.id("tinymce"));
        WebElement commentTextarea = driver.findElement(By.cssSelector("#tinymce > p"));
        //commentTextarea.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        commentTextarea.sendKeys("Test issue new comment");
        driver.findElement(By.id("issue-comment-add-submit")).click();
        WebDriverWait wait2 = new WebDriverWait(driver, 15);

        Assert.assertEquals("Test issue new comment", commentTextarea.getText());
    }
     */

    @AfterEach
    public void tearDown() {
        util.closeWindow();
    }

}
