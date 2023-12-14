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

public class ButtonsPractice {

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

        driver.get("https://demoqa.com/buttons");

        Actions actions = new Actions(driver);

        WebElement doubleClickButton = driver.findElement(By.id("doubleClickBtn"));
        actions.doubleClick(doubleClickButton).perform();

        String doubleClickMessage = driver.findElement(By.id("doubleClickMessage")).getText();
        Assert.assertEquals(doubleClickMessage, "You have done a double click");

        WebElement rightClickButton = driver.findElement(By.id("rightClickBtn"));
        actions.contextClick(rightClickButton).perform();

        String rightClickMessage = driver.findElement(By.id("rightClickMessage")).getText();
        Assert.assertEquals(rightClickMessage, "You have done a right click");

        WebElement clickMeButton = driver.findElement(By.xpath("//button[text()='Click Me']"));
        clickMeButton.click();

        String clickMeMessage = driver.findElement(By.id("dynamicClickMessage")).getText();
        Assert.assertEquals(clickMeMessage, "You have done a dynamic click");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
