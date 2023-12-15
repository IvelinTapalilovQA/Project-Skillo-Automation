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

public class NestedFramesPractice {

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
    public void testButtons() {

        driver.get("https://demoqa.com/nestedframes");

        WebElement parentFrame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(parentFrame);

        String parentFrameText = driver.findElement(By.tagName("body")).getText();
        Assert.assertEquals(parentFrameText, "Parent frame");

        driver.switchTo().frame(0);

        String childFrameText = driver.findElement(By.xpath("//body/p")).getText();
        Assert.assertEquals(childFrameText, "Child Iframe");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
