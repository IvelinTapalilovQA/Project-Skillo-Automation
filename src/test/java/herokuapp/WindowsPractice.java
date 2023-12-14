package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public void testWindows() {

        driver.get("https://demoqa.com/browser-windows");

        WebElement tabButton = driver.findElement(By.id("tabButton"));
        tabButton.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindowName = windows.get(1);
        //Switch the driver focus to second tab
        driver.switchTo().window(secondWindowName);

        String expectedUrl = "https://demoqa.com/sample";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String newWindowText = driver.findElement(By.id("sampleHeading")).getText();
        Assert.assertEquals(newWindowText, "This is a sample page");

        //Switch the driver focus to first tab
        String firstWindowName = windows.get(0);
        driver.switchTo().window(firstWindowName);

        expectedUrl = "https://demoqa.com/browser-windows";
        actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

    }
    @Test
    public void testWindowMessage(){

        driver.get("https://demoqa.com/browser-windows");

        WebElement newWindowMessage = driver.findElement(By.id("messageWindowButton"));
        newWindowMessage.click();

        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        String secondWindowName = windows.get(1);
        //Switch the driver focus to second tab
        driver.switchTo().window(secondWindowName);

        String newWindowMessageText = driver.findElement(By.xpath("/html/body")).getText();
        Assert.assertEquals(newWindowMessageText, "Knowledge increases by sharing but not by saving. " +
                "Please share this website with your friends and in your organization.");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

}
