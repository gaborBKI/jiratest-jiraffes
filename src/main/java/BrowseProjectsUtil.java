import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowseProjectsUtil {

    private WebDriver driver;
    private static WebDriverWait wait;

    public BrowseProjectsUtil(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }


    public void navigateToAllProjects(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"header\"]/nav")));
        WebElement projectsDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("browse_link")));
        projectsDropdown.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("project_view_all_link_lnk")));
        WebElement viewAllButton = driver.findElement(By.id("project_view_all_link_lnk"));
        viewAllButton.click();

    }


    public void checkForProjectInList(String projectTitle){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@original-title=" + "'" + projectTitle + "'" + "]")));
        WebElement mainTestingProject = driver.findElement(By.xpath("//*[@original-title=" + "'" + projectTitle + "'" + "]"));
        mainTestingProject.click();
    }


}
