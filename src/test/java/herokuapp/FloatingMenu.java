package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class FloatingMenu {

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
    public void testCheckBox(){

        driver.get("https://the-internet.herokuapp.com/floating_menu");


        WebElement homeLinkButton = driver.findElement(By.linkText("Home"));
        homeLinkButton.click();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://the-internet.herokuapp.com/floating_menu#home";
        Assert.assertEquals(actualUrl, expectedUrl);

        WebElement newsLinkButton = driver.findElement(By.linkText("News"));
        newsLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#news";
        Assert.assertEquals(actualUrl, expectedUrl);


        WebElement contactLinkButton = driver.findElement(By.linkText("Contact"));
        contactLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#contact";
        Assert.assertEquals(actualUrl, expectedUrl);


        WebElement aboutLinkButton = driver.findElement(By.linkText("About"));
        aboutLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#about";
        Assert.assertEquals(actualUrl, expectedUrl);

        JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,2750)", "");

        homeLinkButton = driver.findElement(By.linkText("Home"));
        homeLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#home";
        Assert.assertEquals(actualUrl, expectedUrl);

        newsLinkButton = driver.findElement(By.linkText("News"));
        newsLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#news";
        Assert.assertEquals(actualUrl, expectedUrl);


        contactLinkButton = driver.findElement(By.linkText("Contact"));
        contactLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#contact";
        Assert.assertEquals(actualUrl, expectedUrl);


        aboutLinkButton = driver.findElement(By.linkText("About"));
        aboutLinkButton.click();

        actualUrl = driver.getCurrentUrl();
        expectedUrl = "https://the-internet.herokuapp.com/floating_menu#about";
        Assert.assertEquals(actualUrl, expectedUrl);


    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
