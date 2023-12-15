package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
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

public class ModalDialogsPractice {

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
    public void testSmallModal() {

        driver.get("https://demoqa.com/modal-dialogs");

        WebElement smallModalButton = driver.findElement(By.id("showSmallModal"));
        smallModalButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-dialog modal-sm']")));
        WebElement modalDialog = driver.findElement(By.xpath("//div[@class='modal-dialog modal-sm']"));

        WebElement modalBody = modalDialog.findElement(By.className("modal-body"));
        String modalTextContent = modalBody.getText();
        Assert.assertEquals(modalTextContent, "This is a small modal. It has very less content");

        WebElement modalButton = modalDialog.findElement(By.id("closeSmallModal"));
        modalButton.click();
    }

    @Test
    public void testLargeModal() {

        driver.get("https://demoqa.com/modal-dialogs");

        WebElement largeModalButton = driver.findElement(By.id("showLargeModal"));
        largeModalButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-dialog modal-lg']")));
        WebElement largeModalDialog = driver.findElement(By.xpath("//div[@class='modal-dialog modal-lg']"));

        WebElement largeModalBody = largeModalDialog.findElement(By.className("modal-body"));
        String largeModalTextContent = largeModalBody.getText();
        Assert.assertEquals(largeModalTextContent, "Lorem Ipsum is simply dummy text of the printing and " +
                "typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                " when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has " +
                "survived not only five centuries, but also the leap into electronic typesetting, remaining essentially " +
                "unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages," +
                " and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");

        WebElement modalButton = largeModalDialog.findElement(By.id("closeLargeModal"));
        modalButton.click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}