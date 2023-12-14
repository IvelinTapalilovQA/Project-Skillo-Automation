package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class DynamicContent {

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
    public void testDynamicContent() {

        driver.get("https://the-internet.herokuapp.com/dynamic_content");

        WebElement firstArticle = driver.findElement(By.xpath("//div[1]/div[@class='large-10 columns']"));
        String firstArticleText = firstArticle.getText();

        WebElement secondArticle = driver.findElement(By.xpath("//div[2]/div[@class='large-10 columns']"));
        String secondArticleText = secondArticle.getText();

        WebElement thirdArticle = driver.findElement(By.xpath("//div[3]/div[@class='large-10 columns']"));
        String thirdArticleText = thirdArticle.getText();

        WebElement button = driver.findElement(By.linkText("click here"));
        button.click();

        firstArticle = driver.findElement(By.xpath("//div[1]/div[@class='large-10 columns']"));
        String currentTextArticleOne = firstArticle.getText();

        secondArticle = driver.findElement(By.xpath("//div[2]/div[@class='large-10 columns']"));
        String currentTextArticleTwo = secondArticle.getText();

        thirdArticle = driver.findElement(By.xpath("//div[3]/div[@class='large-10 columns']"));
        String currentTextArticleThree = thirdArticle.getText();

        Assert.assertNotEquals(firstArticleText, currentTextArticleOne);

        Assert.assertNotEquals(secondArticleText, currentTextArticleTwo);

        Assert.assertNotEquals(thirdArticleText, currentTextArticleThree);
    }

    @Test
    public void testDynamicControls() {

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());

        WebElement buttonRemove = driver.findElement(By.xpath("//button[text()='Remove']"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonRemove));
        buttonRemove.click();

        WebElement messageElement = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.visibilityOf(messageElement));
        String messageText = messageElement.getText();
        Assert.assertEquals(messageText, "It's gone!");
        System.out.println(messageText);


        WebElement buttonAdd = driver.findElement(By.xpath("//button[text()='Add']"));
        wait.until(ExpectedConditions.elementToBeClickable(buttonRemove));
        buttonAdd.click();

        messageElement = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.visibilityOf(messageElement));
        messageText = messageElement.getText();
        Assert.assertEquals(messageText, "It's back!");
        System.out.println(messageText);


        checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();
        Assert.assertTrue(checkbox.isSelected());

    }
    @Test
    public void testDynamicInput(){

        driver.get("https://the-internet.herokuapp.com/dynamic_controls");

        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        Assert.assertFalse(inputField.isEnabled());

        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        Assert.assertTrue(inputField.isEnabled());

        inputField.sendKeys("Test");

        WebElement disableButton = driver.findElement(By.xpath("//button[text()='Disable']"));
        wait.until(ExpectedConditions.elementToBeClickable(disableButton));
        disableButton.click();

        WebElement message = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.visibilityOf(message));
        String messageText = message.getText();
        Assert.assertEquals(messageText, "It's disabled!");
        System.out.println(messageText);

        enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        wait.until(ExpectedConditions.elementToBeClickable(enableButton));
        enableButton.click();

        message = driver.findElement(By.id("message"));
        wait.until(ExpectedConditions.visibilityOf(message));
        messageText = message.getText();
        Assert.assertEquals(messageText, "It's enabled!");
        System.out.println(messageText);

        inputField = driver.findElement(By.xpath("//input[@type='text']"));
        wait.until(ExpectedConditions.elementToBeClickable(inputField));
        Assert.assertTrue(inputField.isEnabled());

        inputField.clear();
        inputField.sendKeys("Test2");

    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
