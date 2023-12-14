package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesPractice {

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

        driver.get("https://the-internet.herokuapp.com/frames");

        WebElement iFrameLink = driver.findElement(By.xpath("//a[@href='/iframe']"));
        iFrameLink.click();

        //Element outside of the frame
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        WebElement buttonBold = driver.findElement(By.xpath("//button[@title=\"Bold\"]/span"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonBold));
        buttonBold.click();

        //Switch to the frame
        WebElement frame = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame(frame);

        //The element inside the frame
        WebElement textField = driver.findElement(By.id("tinymce"));
        textField.clear();
        textField.sendKeys("Writing some text for testing purposes!");
    }

    @Test
    public void nestedFrames(){

        driver.get("https://the-internet.herokuapp.com/frames");

        WebElement nestedFramesLink = driver.findElement(By.xpath("//a[@href='/nested_frames']"));
        nestedFramesLink.click();

        WebElement topFrames = driver.findElement(By.xpath("//frame[@src='/frame_top']"));
        driver.switchTo().frame(topFrames);
        driver.switchTo().frame(0);

        String leftFrameText = driver.findElement(By.xpath("//body")).getText();
        Assert.assertEquals(leftFrameText, "LEFT");
        System.out.println(leftFrameText);

        driver.switchTo().parentFrame();
        driver.switchTo().frame(1);

        String middleFrameText = driver.findElement(By.xpath("//body")).getText();
        Assert.assertEquals(middleFrameText, "MIDDLE");
        System.out.println(middleFrameText);

        driver.switchTo().parentFrame();
        driver.switchTo().frame(2);

        String rightFrameText = driver.findElement(By.xpath("//body")).getText();
        Assert.assertEquals(rightFrameText, "RIGHT");
        System.out.println(rightFrameText);

        driver.switchTo().defaultContent();
        WebElement bottomFrame = driver.findElement(By.xpath("//frame[@src='/frame_bottom']"));
        driver.switchTo().frame(bottomFrame);

        String bottomFrameText = driver.findElement(By.xpath("//body")).getText();
        Assert.assertEquals(bottomFrameText, "BOTTOM");
        System.out.println(bottomFrameText);

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
