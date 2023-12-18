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

public class ProgressBarPractice {

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
    public void testProgressBar() {

        driver.get("https://demoqa.com/progress-bar");

        WebElement startStopButton = driver.findElement(By.id("startStopButton"));
        startStopButton.click();

        String startStopButtonText = driver.findElement(By.id("startStopButton")).getText();
        Assert.assertEquals(startStopButtonText, "Stop");

        WebElement progressBarInfo = driver.findElement(By.xpath("//div[@class='progress-bar bg-info']"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(progressBarInfo, "aria-valuenow"));

        startStopButton.click();

        startStopButtonText = driver.findElement(By.id("startStopButton")).getText();
        Assert.assertEquals(startStopButtonText, "Start");

        String currentValue = progressBarInfo.getAttribute("aria-valuenow");
        int currentValueInt = Integer.parseInt(currentValue);
        boolean isStopButtonWorking = 0 < currentValueInt && currentValueInt < 30;
        Assert.assertTrue(isStopButtonWorking);

        startStopButton.click();

        wait.until(ExpectedConditions.attributeToBe(progressBarInfo, "aria-valuenow", "100"));

        String progressBarValue = progressBarInfo.getAttribute("aria-valuenow");
        String expectedBarValue = "100";
        Assert.assertEquals(progressBarValue, expectedBarValue);

        WebElement resetButton = driver.findElement(By.id("resetButton"));
        resetButton.click();

        progressBarValue = progressBarInfo.getAttribute("aria-valuenow");
        expectedBarValue = "0";
        Assert.assertEquals(progressBarValue, expectedBarValue);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
