package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WindowsPractice {

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
    public void testNewTab() {

        driver.get("https://demoqa.com/browser-windows");

        WebElement newTabButton = driver.findElement(By.id("tabButton"));
        newTabButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/sample"));

        WebElement newTabTitle = driver.findElement(By.id("sampleHeading"));
        String titleText = newTabTitle.getText();
        Assert.assertEquals(titleText, "This is a sample page");
    }

    @Test
    public void testNewWindow() {

        driver.get("https://demoqa.com/browser-windows");

        WebElement newWindowButton = driver.findElement(By.id("windowButton"));
        newWindowButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(1));
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://demoqa.com/sample"));

        WebElement newWindowTitle = driver.findElement(By.id("sampleHeading"));
        String titleText = newWindowTitle.getText();
        Assert.assertEquals(titleText, "This is a sample page");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
