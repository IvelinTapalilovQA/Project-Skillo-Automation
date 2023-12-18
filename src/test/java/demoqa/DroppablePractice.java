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

public class DroppablePractice {

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
    public void testDroppable() {

        driver.get("https://demoqa.com/droppable");

        WebElement draggable = driver.findElement(By.id("draggable"));

        String draggableText = draggable.getText();
        Assert.assertEquals(draggableText, "Drag me");

        WebElement droppable = driver.findElement(By.id("droppable"));

        String droppableText = droppable.getText();
        Assert.assertEquals(droppableText, "Drop here");

        Actions actions = new Actions(driver);
        actions.dragAndDropBy(draggable, 300, 0).perform();

        droppableText = droppable.getText();
        Assert.assertEquals(droppableText, "Dropped!");

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
