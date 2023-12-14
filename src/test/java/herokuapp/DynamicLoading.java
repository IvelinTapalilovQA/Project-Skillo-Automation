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

import java.time.Duration;

public class DynamicLoading {

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
    public void testDynamicLoading() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//div/button"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement title = driver.findElement(By.xpath("//div/h4[text()='Hello World!']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        String titleText = title.getText();
        Assert.assertEquals(titleText, "Hello World!");

    }

    @Test
    public void testDynamicLoadingTwo() {

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.xpath("//div/button"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement title = driver.findElement(By.xpath("//div/h4[text()='Hello World!']"));
        wait.until(ExpectedConditions.visibilityOf(title));
        String titleText = title.getText();
        Assert.assertEquals(titleText, "Hello World!");

    }



}
