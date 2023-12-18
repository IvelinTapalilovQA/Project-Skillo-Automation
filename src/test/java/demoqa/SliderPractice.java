package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class SliderPractice {

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
    public void testSlider() {

        driver.get("https://demoqa.com/slider");

        Actions actions = new Actions(driver);
        WebElement sliderButton = driver.findElement(By.xpath("//input[@type='range']"));
        WebElement sliderValueField = driver.findElement(By.id("sliderValue"));

        //Comparing the value from the button and the starting value of 25
        String sliderValue = sliderButton.getAttribute("value");
        String expectedValue = "25";
        Assert.assertEquals(sliderValue, expectedValue);

        actions.dragAndDropBy(sliderButton, 260, 0).perform();

        //Comparing the value from the button and the value after sliding to 100
        sliderValue = sliderButton.getAttribute("value");
        expectedValue = "100";
        Assert.assertEquals(sliderValue, expectedValue);

        actions.dragAndDropBy(sliderButton, -260, 0).perform();

        //Comparing the value from the button and the value after sliding to 0
        sliderValue = sliderButton.getAttribute("value");
        expectedValue = "0";
        Assert.assertEquals(sliderValue, expectedValue);

        //Comparing the value from the button and the value from the value field
        String buttonValue = sliderButton.getAttribute("value");
        String valueField = sliderValueField.getAttribute("value");
        Assert.assertEquals(buttonValue, valueField);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}