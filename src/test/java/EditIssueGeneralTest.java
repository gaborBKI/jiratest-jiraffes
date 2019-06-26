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

public class EditIssueGeneralTest {

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

    /*
    @Test
    public void editPageOpensTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        driver.manage().window().maximize();
        System.out.println("Page opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editButton.click();
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    @Test
    public void editToucanGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/TOUCAN-51");
        driver.manage().window().maximize();
        System.out.println("Toucan project opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editButton.click();
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));

    }

    @Test
    public void editCoalaGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/COALA-185");
        driver.manage().window().maximize();
        System.out.println("Jira project opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editButton.click();
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

    @Test
    public void editJetiGeneralTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/MTP-49");
        driver.manage().window().maximize();
        System.out.println("Jeti project opened");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement editButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue")));
        editButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("edit-issue-dialog")));
        Assert.assertNotNull(driver.findElement(By.id("edit-issue-dialog")));
    }

     */


    @Test
    public void quickEditWithGreyButtonsTest() {
        driver.navigate().to("https://jira.codecool.codecanvas.hu/browse/SAND-40");
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement commentButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment-issue")));
        commentButton.click();
        WebElement commentTextArea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("comment")));
        commentTextArea.sendKeys("Test issue new comment");
        driver.findElement(By.id("issue-comment-add-submit")).click();
        System.out.println(driver.findElement(By.xpath("//*[@id=\"issue_actions_container\"][last()]")).getText());

        Assert.assertEquals("Test issue new comment", commentTextArea.getText());

    }


    @AfterEach
    public void tearDown() {
        util.closeWindow();
    }

}
