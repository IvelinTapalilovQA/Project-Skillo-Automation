package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class AutoCompletePractice {

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
    public void testAutoComplete() {

        driver.get("https://demoqa.com/auto-complete");

        WebElement autoCompleteInput = driver.findElement(By.id("autoCompleteMultipleInput"));
        autoCompleteInput.sendKeys("b");
        autoCompleteInput.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
        String actualValue = driver.findElement(By.xpath("//div[@class='css-1rhbuit-multiValue auto-complete__multi-value'][1]")).getText();
        String expectedValue = "Black";
        Assert.assertEquals(actualValue, expectedValue);

        autoCompleteInput.sendKeys("r");
        autoCompleteInput.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        actualValue = driver.findElement(By.xpath("//div[@class='css-1rhbuit-multiValue auto-complete__multi-value'][2]")).getText();
        expectedValue = "Purple";
        Assert.assertEquals(actualValue, expectedValue);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
