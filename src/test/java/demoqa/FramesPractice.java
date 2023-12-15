package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesPractice {

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
    public void testFrames() {

        driver.get("https://demoqa.com/frames");

        WebElement frameOne = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frameOne);

        WebElement frameTitle = driver.findElement(By.id("sampleHeading"));
        String frameTitleText = frameTitle.getText();
        Assert.assertEquals(frameTitleText, "This is a sample page");

        driver.switchTo().defaultContent();

        WebElement frameTwo = driver.findElement(By.id("frame2"));
        driver.switchTo().frame(frameTwo);

        frameTitle = driver.findElement(By.id("sampleHeading"));
        frameTitleText = frameTitle.getText();
        Assert.assertEquals(frameTitleText, "This is a sample page");

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
