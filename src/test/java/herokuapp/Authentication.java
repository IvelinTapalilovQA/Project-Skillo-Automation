package herokuapp;

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

public class Authentication {

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
    public void testBasicAuth() {

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        WebElement confirmMessage = driver.findElement(By.xpath("//div/p"));
        String confirmMessageText = confirmMessage.getText();
        Assert.assertEquals(confirmMessageText, "Congratulations! You must have the proper credentials.");
    }
    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
}