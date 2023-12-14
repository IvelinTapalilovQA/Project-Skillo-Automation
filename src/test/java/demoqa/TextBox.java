package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.util.List;

public class TextBox {

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
    public void testContextMenu() {

        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement targetRectangle = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(targetRectangle).build().perform();

        driver.switchTo().alert().accept();

    }

    @Test
    public void testTextForm() {

        driver.get("https://demoqa.com/text-box");

        WebElement usernameInput = driver.findElement(By.id("userName"));
        usernameInput.sendKeys("Test");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("Test@gmail.com");

        WebElement currentAddressInput = driver.findElement(By.id("currentAddress"));
        currentAddressInput.sendKeys("Test Avenue 20");

        WebElement permanentAddressInput = driver.findElement(By.id("permanentAddress"));
        permanentAddressInput.sendKeys("Test Street 34");

        WebElement submitButton = driver.findElement(By.xpath("//div/button"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        submitButton.click();

        StringBuilder outputMessageText = new StringBuilder();
        int counter = 0;
        List<WebElement> outputMessages = driver.findElements(By.xpath("//div/p"));
        for (WebElement message : outputMessages) {

            if (counter < (outputMessages.size()) - 1) {
                outputMessageText.append(message.getText()).append("\n");
                counter++;
            } else {
                outputMessageText.append(message.getText());
            }
        }
        String expectedMessageText = """
                Name:Test
                Email:Test@gmail.com
                Current Address :Test Avenue 20
                Permananet Address :Test Street 34""";

        Assert.assertEquals(outputMessageText.toString(), expectedMessageText);

    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}