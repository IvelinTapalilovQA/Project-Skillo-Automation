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

public class CheckboxesRadioButtons {

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
    public void testCheckBox(){

        driver.get("https://demoqa.com/checkbox");

        WebElement labelCheckbox = driver.findElement(By.xpath("//label[@for=\"tree-node-home\"]"));
        labelCheckbox.click();

        WebElement checkbox = driver.findElement(By.id("tree-node-home"));
        Assert.assertTrue(checkbox.isSelected());

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(),
                "You have selected :\n" + "home\n" + "desktop\n" + "notes\n" + "commands\n" + "documents\n" +
                        "workspace\n" + "react\n" + "angular\n" +
                        "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" +
                        "wordFile\n" + "excelFile");

    }

    @Test
    public void testCheckboxes(){

        driver.get("https://demoqa.com/checkbox");

        WebElement expandArrow = driver.findElement(By.cssSelector("button.rct-collapse.rct-collapse-btn"));
        expandArrow.click();

        //Select first checkbox
        WebElement labelCheckboxDocuments = driver.findElement(By.xpath("//label[@for=\"tree-node-documents\"]"));
        labelCheckboxDocuments.click();

        WebElement checkboxDocuments = driver.findElement(By.id("tree-node-documents"));
        Assert.assertTrue(checkboxDocuments.isSelected());

        //Select second checkbox

        WebElement labelCheckboxDownloads = driver.findElement(By.xpath("//label[@for=\"tree-node-downloads\"]"));
        labelCheckboxDownloads.click();

        WebElement checkboxDownloads = driver.findElement(By.id("tree-node-downloads"));
        Assert.assertTrue(checkboxDownloads.isSelected());

        //Verify 1st checkbox is still selected
        Assert.assertTrue(checkboxDocuments.isSelected());

        WebElement result = driver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You have selected :\n" + "documents\n" + "workspace\n" + "react\n" +
                "angular\n" + "veu\n" + "office\n" + "public\n" + "private\n" + "classified\n" + "general\n" + "downloads\n" +
                "wordFile\n" + "excelFile");
    }

    @Test
    public void testRadioButtons(){

        driver.get("https://demoqa.com/radio-button");

        WebElement radioYesLabel = driver.findElement(By.cssSelector("[for='yesRadio']"));
        radioYesLabel.click();

        WebElement radioYesInput = driver.findElement(By.id("yesRadio"));
        Assert.assertTrue(radioYesInput.isSelected());

        String actualResultText = driver.findElement(By.cssSelector("p.mt-3")).getText();
        Assert.assertEquals(actualResultText, "You have selected Yes");

        //You can select just one radio button
        WebElement radioImpressiveLabel = driver.findElement(By.cssSelector("[for='impressiveRadio']"));
        radioImpressiveLabel.click();

        WebElement radioImpressiveInput = driver.findElement(By.id("impressiveRadio"));
        Assert.assertTrue(radioImpressiveInput.isSelected());

        // The first radio button "Yes" should be unselected
        Assert.assertFalse(radioYesInput.isSelected());


        //Click cannot select radio button that is disabled
        WebElement noRadioButtonLabel = driver.findElement(By.cssSelector("[for='noRadio']"));
        noRadioButtonLabel.click();

        WebElement noRadioButtonInput = driver.findElement(By.id("noRadio"));

        if (noRadioButtonInput.isEnabled()) {
            Assert.assertTrue(noRadioButtonInput.isSelected());
        }
        else {
            System.out.println("No radio button is disabled!");
        }
    }
    @Test
    public void testCheckboxHeroku(){

        driver.get("https://the-internet.herokuapp.com/checkboxes");

        WebElement checkboxOne = driver.findElement(By.xpath("//form/input[1]"));
        Assert.assertFalse(checkboxOne.isSelected());
        checkboxOne.click();
        Assert.assertTrue(checkboxOne.isSelected());

        WebElement checkboxTwo = driver.findElement(By.xpath("//form/input[2]"));
        Assert.assertTrue(checkboxTwo.isSelected());
        checkboxTwo.click();
        Assert.assertFalse(checkboxTwo.isSelected());
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
