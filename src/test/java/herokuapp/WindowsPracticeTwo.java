package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.rmi.server.ExportException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowsPracticeTwo {


    WebDriver driver;

    @BeforeSuite
    public void setupBeforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupForTest() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("Start-Maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void testWindows() {

        driver.get("https://the-internet.herokuapp.com/windows");

        WebElement newWindowLink = driver.findElement(By.linkText("Click Here"));
        newWindowLink.click();


        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.urlToBe("https://the-internet.herokuapp.com/windows/new"));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h3")));
        String newWindowTitle = driver.findElement(By.xpath("//div/h3")).getText();
        Assert.assertEquals(newWindowTitle, "New Window");

    }
}
