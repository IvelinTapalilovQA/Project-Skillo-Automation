package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

public class AlertAndDisappearElements {

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
    public void testMouseClickAndAlert() {

        driver.get("https://the-internet.herokuapp.com/context_menu");

        WebElement targetRectangle = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(targetRectangle).build().perform();

        driver.switchTo().alert().accept();

    }
    @Test
    public void testDisappearingElements(){

        driver.get("https://the-internet.herokuapp.com/disappearing_elements");

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Gallery']")));
            WebElement galleryButton = driver.findElement(By.xpath("//a[text()='Gallery']"));
            galleryButton.click();
        }catch (NoSuchElementException e){
            System.out.println("The element is not found " + e);
        }

        String titleNewPage = driver.findElement(By.xpath("//h1[text()='Not Found']")).getText();
        Assert.assertEquals(titleNewPage, "Not Found");

        driver.navigate().back();

        WebElement homeButton = driver.findElement(By.xpath("//a[text()='Home']"));
        homeButton.click();

        WebElement disappearElementsLink = driver.findElement(By.xpath("//a[text()='Disappearing Elements']"));
        disappearElementsLink.click();

        WebElement aboutButton = driver.findElement(By.xpath("//a[text()='About']"));
        aboutButton.click();

        titleNewPage = driver.findElement(By.xpath("//h1[text()='Not Found']")).getText();
        Assert.assertEquals(titleNewPage, "Not Found");

        driver.navigate().back();

        WebElement contactUsButton = driver.findElement(By.xpath("//a[text()='Contact Us']"));
        contactUsButton.click();

        titleNewPage = driver.findElement(By.xpath("//h1[text()='Not Found']")).getText();
        Assert.assertEquals(titleNewPage, "Not Found");

        driver.navigate().back();

        WebElement portfolio = driver.findElement(By.xpath("//a[text()='Portfolio']"));
        portfolio.click();

        titleNewPage = driver.findElement(By.xpath("//h1[text()='Not Found']")).getText();
        Assert.assertEquals(titleNewPage, "Not Found");

        driver.navigate().back();

        String mainTitle = driver.findElement(By.tagName("h3")).getText();
        Assert.assertEquals(mainTitle, "Disappearing Elements");
    }


    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

