package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.time.Duration;

public class PracticeForm {

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
    public void testBoxMenu() {

        driver.get("https://demoqa.com/automation-practice-form");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h5")));

        WebElement usernameInput = driver.findElement(By.id("firstName"));
        usernameInput.sendKeys("John");

        WebElement lastnameInput = driver.findElement(By.id("lastName"));
        lastnameInput.sendKeys("Smith");

        WebElement emailInput = driver.findElement(By.id("userEmail"));
        emailInput.sendKeys("johnsmith@gmail.comm");

        WebElement radioButtonMaleLabel = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
        radioButtonMaleLabel.click();

        WebElement radioButtonMaleInput = driver.findElement(By.id("gender-radio-1"));
        Assert.assertTrue(radioButtonMaleInput.isSelected());

        WebElement numberInput = driver.findElement(By.id("userNumber"));
        numberInput.sendKeys("1234567890");

        WebElement dateOfBirthInput = driver.findElement(By.id("dateOfBirthInput"));
        dateOfBirthInput.click();

        Select select = new Select(driver.findElement(By.xpath("//*[@id=\"dateOfBirth\"]/div[2]/div[2]/div/div/div[2]/div[1]" +
                "/div[2]/div[1]/select")));
        select.selectByVisibleText("March");

        select = new Select(driver.findElement(By.xpath("//div[@class=\"react-datepicker__year-dropdown-container " +
                "react-datepicker__year-dropdown-container--select\"]/select")));
        select.selectByVisibleText("1987");

        WebElement date = driver.findElement(By.xpath("//div[text()='17']"));
        date.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        WebElement subjectInput = driver.findElement(By.id("subjectsInput"));
        wait.until(ExpectedConditions.elementToBeClickable(subjectInput));

        subjectInput.sendKeys("Ma");
        subjectInput.sendKeys(Keys.ENTER);

        subjectInput.sendKeys("En");
        subjectInput.sendKeys(Keys.ENTER);

        WebElement checkboxHobbiesLabel = driver.findElement(By.xpath("//label[@for='hobbies-checkbox-3']"));
        checkboxHobbiesLabel.click();

        WebElement checkboxHobbiesInput = driver.findElement(By.id("hobbies-checkbox-3"));
        Assert.assertTrue(checkboxHobbiesInput.isSelected());

        //Upload picture
//        WebElement uploadPicture = driver.findElement(By.id("uploadPicture"));
//        Actions actions = new Actions(driver);
//        actions.click(uploadPicture).perform();
//
//        uploadPicture.sendKeys("");

        WebElement currentAddress = driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));
        currentAddress.sendKeys("Test Street 17");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
