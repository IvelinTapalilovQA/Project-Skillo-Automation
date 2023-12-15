package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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

public class AlertPractice {

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
    public void testAlert() {

        driver.get("https://demoqa.com/alerts");

        WebElement alertButton = driver.findElement(By.id("alertButton"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "You clicked a button");

        driver.switchTo().alert().accept();
    }

    @Test
    public void testTimerAlert() {

        driver.get("https://demoqa.com/alerts");

        WebElement timerAlertButton = driver.findElement(By.id("timerAlertButton"));
        timerAlertButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "This alert appeared after 5 seconds");

        driver.switchTo().alert().accept();
    }

    @Test
    public void testConfirmBox() {

        driver.get("https://demoqa.com/alerts");

        WebElement confirmBoxButton = driver.findElement(By.id("confirmButton"));
        confirmBoxButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Do you confirm action?");

        //First we press Cancel
        driver.switchTo().alert().dismiss();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement confirmBoxResult = driver.findElement(By.id("confirmResult"));
        wait.until(ExpectedConditions.visibilityOf(confirmBoxResult));
        String resultText = confirmBoxResult.getText();
        Assert.assertEquals(resultText, "You selected Cancel");

        confirmBoxButton.click();

        //Second we press Ok
        driver.switchTo().alert().accept();

        confirmBoxResult = driver.findElement(By.id("confirmResult"));
        wait.until(ExpectedConditions.visibilityOf(confirmBoxResult));
        resultText = confirmBoxResult.getText();
        Assert.assertEquals(resultText, "You selected Ok");
    }

    @Test
    public void testPromptBox() {

        driver.get("https://demoqa.com/alerts");

        WebElement promptBoxButton = driver.findElement(By.id("promtButton"));
        promptBoxButton.click();

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Please enter your name");

        driver.switchTo().alert().dismiss();

        promptBoxButton.click();

        driver.switchTo().alert().sendKeys("John");
        driver.switchTo().alert().accept();

        WebElement promptBoxResult = driver.findElement(By.id("promptResult"));
        String resultText = promptBoxResult.getText();
        Assert.assertEquals(resultText, "You entered John");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
