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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;

public class LoginPage {

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
    public void testLogin() {

        driver.get("https://the-internet.herokuapp.com/login");

        WebElement title = driver.findElement(By.xpath("//div/h2"));
        String titleText = title.getText();
        Assert.assertEquals(titleText, "Login Page");

        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("SuperSecretPassword!");

        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        WebElement confirmMessageLogin = driver.findElement(By.id("flash"));
        String confirmMessageLoginText = confirmMessageLogin.getText();
        Assert.assertEquals(confirmMessageLoginText, "You logged into a secure area!\n" +
                "×");

        WebElement logoutButton = driver.findElement(By.xpath("//a[@href='/logout']"));
        logoutButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        WebElement confirmMessageLogout = driver.findElement(By.id("flash"));
        String confirmMessageLogoutText = confirmMessageLogout.getText();
        Assert.assertEquals(confirmMessageLogoutText, "You logged out of the secure area!\n" +
                "×");
    }
    @AfterMethod
    public void teardown()
    {
        driver.quit();
    }
}
