package herokuapp;

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

public class DragDrop {

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
    public void testDragDrop() {

        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

        WebElement squareA = driver.findElement(By.id("column-a"));
        WebElement squareB = driver.findElement(By.id("column-b"));

        String actualText = squareA.getText();
        Assert.assertEquals(actualText, "A");

        Actions actions = new Actions(driver);
        actions.dragAndDrop(squareA, squareB).build().perform();

        actualText = squareA.getText();
        Assert.assertEquals(actualText, "B");

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
