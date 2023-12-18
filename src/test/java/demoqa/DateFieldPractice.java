package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class DateFieldPractice {

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
    public void testDateFieldOne() {

        //Target = 10/28/1980

        driver.get("https://demoqa.com/date-picker");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");

        WebElement dateFieldInput = driver.findElement(By.id("datePickerMonthYearInput"));
        dateFieldInput.click();

        Select month = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']")));
        month.selectByVisibleText("October");

        Select year = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']")));
        year.selectByVisibleText("1980");

        WebElement date = driver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--028']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(date));
        date.click();

        String actualValue = dateFieldInput.getAttribute("value");
        String expectedValue = "10/28/1980";
        Assert.assertEquals(actualValue, expectedValue);

    }

    @Test
    public void testDateFieldTwo() {

        //Target -October 28, 1980 12:15 PM

        driver.get("https://demoqa.com/date-picker");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)", "");

        WebElement dateField = driver.findElement(By.id("dateAndTimePickerInput"));
        dateField.click();


        WebElement monthArrow = driver.findElement(By.xpath("//div[@class='react-datepicker__month-read-view']"));
        monthArrow.click();

        WebElement month = driver.findElement(By.xpath("//div[@class='react-datepicker__month-option'][text()='October']"));
        Actions actions = new Actions(driver);
        actions.click(month).perform();

        WebElement yearArrowOpen = driver.findElement(By.xpath("//span[@class='react-datepicker__year-read-view--down-arrow']"));
        yearArrowOpen.click();


        while (true) {
            WebElement arrowDown = driver.findElement(By.xpath("//a[@class='react-datepicker__navigation react-datepicker__navigation--years react-datepicker__navigation--years-previous']"));
            arrowDown.click();
            WebElement yearOption = driver.findElement(By.xpath("//div[@class='react-datepicker__year-option'][7]"));
            if (yearOption.getText().equals("1980")) {
                yearOption.click();
                break;
            }
        }

        WebElement date = driver.findElement(By.xpath("//div[@aria-label='Choose Tuesday, October 28th, 1980']"));
        date.click();

        List<WebElement> hours = driver.findElements(By.xpath("//li[@class='react-datepicker__time-list-item ']"));

        for (WebElement hour : hours) {
            if (hour.getText().equals("12:15")) {
                hour.click();
                break;
            }
        }

        String actualValue = dateField.getAttribute("value");
        String expectedValue = "October 28, 1980 12:15 PM";
        Assert.assertEquals(actualValue, expectedValue);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
