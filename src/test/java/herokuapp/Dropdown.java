package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class Dropdown {

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
    public void testDropdown() {

        driver.get("https://the-internet.herokuapp.com/dropdown");

        WebElement dropdownMenu = driver.findElement(By.id("dropdown"));

        Select select = new Select(dropdownMenu);
        select.selectByVisibleText("Option 2");

        WebElement optionTwo = driver.findElement(By.xpath("//*[@id='dropdown']/option[3]"));
        Assert.assertTrue(optionTwo.isSelected());

        select.selectByIndex(1);

        WebElement optionOne = driver.findElement(By.xpath("//*[@id='dropdown']/option[2]"));
        Assert.assertTrue(optionOne.isSelected());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
