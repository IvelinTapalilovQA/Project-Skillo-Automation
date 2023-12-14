package herokuapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class TablesPractice {

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

        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        WebElement addButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        addButton.click();

        WebElement deleteButton = driver.findElement(By.xpath("//button[@onclick='deleteElement()']"));
        deleteButton.click();

    }

    @Test
    public void testBasicAuth() {

        driver.get("https://the-internet.herokuapp.com/basic_auth");

        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");


    }

    @Test
    public void testTableHerokuApp() {

        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        WebElement table = driver.findElement(By.tagName("table"));
        int columnIndex = 0;

        WebElement tableColumnHeader = table.findElement(By.tagName("thead"));
        List<WebElement> tableColumnHeaders = tableColumnHeader.findElements(By.tagName("th"));

        for (WebElement column : tableColumnHeaders) {
            String columnName = column.getText();
            if (columnName.equals("Amet")) {
                columnIndex = tableColumnHeaders.indexOf(column);
            }
            System.out.println(column.getText());
        }

        WebElement tbody = table.findElement(By.tagName("tbody"));
        List<WebElement> rows = tbody.findElements(By.tagName("tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String cellText = cells.get(columnIndex).getText();
            if (cellText.equals("Consequuntur4")) {
                WebElement deleteButton = row.findElement(By.xpath("//a[text()='delete']"));
                deleteButton.click();
                break;
            }
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://the-internet.herokuapp.com/challenging_dom#delete");
    }

    @Test
    public void testTableDemoQa() {

        driver.get("https://demoqa.com/webtables");

        WebElement table = driver.findElement(By.className("rt-table"));
        int emailIndex = 0;


        WebElement header = table.findElement(By.className("rt-thead"));
        List<WebElement> headerColumns = header.findElements(By.className("rt-th"));

        for (WebElement column : headerColumns) {
            String columnText = column.getText();
            if (columnText.equals("Email")) {
                emailIndex = headerColumns.indexOf(column);
            }
            System.out.println(column.getText());
        }

        WebElement tbody = table.findElement(By.className("rt-tbody"));
        List<WebElement> rows = tbody.findElements(By.className("rt-tr-group"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            String celltext = cells.get(emailIndex).getText();
            if (celltext.equals("alden@example.com")) {
                WebElement deleteButton = row.findElement(By.id("delete-record-2"));
                deleteButton.click();
                break;
            }
        }
        tbody = table.findElement(By.className("rt-tbody"));
        rows = tbody.findElements(By.className("rt-tr-group"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.className("rt-td"));
            String cellText = cells.get(emailIndex).getText();
            Assert.assertNotEquals(cellText, "alden@example.com");

        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

