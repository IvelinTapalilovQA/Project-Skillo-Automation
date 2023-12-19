package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SelectPractice {

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
    public void testSelect() {

        driver.get("https://demoqa.com/select-menu");

        Select select = new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByVisibleText("White");
        select.selectByIndex(5);
        select.selectByValue("red");

        WebElement redOption = driver.findElement(By.xpath("//select[@id='oldSelectMenu']/option[@value='red']"));
        Assert.assertTrue(redOption.isSelected());

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)", "");

        Select select1 = new Select(driver.findElement(By.id("cars")));

        if (select1.isMultiple()) {

            List<WebElement> cars = select1.getOptions();

            for (WebElement car : cars) {
                select1.selectByVisibleText(car.getText());

            }

            List<WebElement> selectedCars = select1.getAllSelectedOptions();
            List<String> stringCars = new ArrayList<>();
            for (WebElement car : selectedCars) {
                stringCars.add(car.getText());
            }

            String result = String.join(" ", stringCars);
            Assert.assertEquals(result, "Volvo Saab Opel Audi");

            select1.deselectByVisibleText("Audi");

            WebElement audiOption = driver.findElement(By.xpath("//select[@id='cars']/option[4]"));
            Assert.assertFalse(audiOption.isSelected());
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}