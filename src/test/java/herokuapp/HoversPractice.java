package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class HoversPractice {

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

        driver.get("https://the-internet.herokuapp.com/hovers");

        WebElement firstPicture = driver.findElement(By.xpath("(//img[@alt='User Avatar'])[1]"));
        Actions actions = new Actions(driver);

        actions.moveToElement(firstPicture).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='/users/1']")));
        WebElement profileOneLink = driver.findElement(By.xpath("//a[@href='/users/1']"));
        profileOneLink.click();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://the-internet.herokuapp.com/users/1";
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
