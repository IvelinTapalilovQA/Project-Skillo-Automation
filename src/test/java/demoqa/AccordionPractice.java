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

public class AccordionPractice {

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
    public void testAccordionCards() {

        driver.get("https://demoqa.com/accordian");

        WebElement firstCard = driver.findElement(By.id("section1Heading"));
        //First to close card
        firstCard.click();
        //Second to open again
        firstCard.click();

        String cardOneText = driver.findElement(By.xpath("//div[@id='section1Content']")).getText();
        Assert.assertEquals(cardOneText, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took" +
                " a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but" +
                " also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with" +
                " the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software" +
                " like Aldus PageMaker including versions of Lorem Ipsum.");
        //Close the first card
        firstCard.click();

        WebElement cardTwo = driver.findElement(By.id("section2Heading"));
        cardTwo.click();

        String cartTwoText = driver.findElement(By.xpath("//div[@id='section2Content']/p")).getText();
        Assert.assertEquals(cartTwoText, "Contrary to popular belief, Lorem Ipsum is not simply random text." +
                " It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard" +
                " McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin " +
                "words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, " +
                "discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et " +
                "Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics," +
                " very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line" +
                " in section 1.10.32.");

        cardTwo.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("section3Heading")));
        WebElement cartThree = driver.findElement(By.id("section3Heading"));
        cartThree.click();

        String cartThreeText = driver.findElement(By.xpath("//div[@id='section3Content']/p")).getText();
        Assert.assertEquals(cartThreeText, "It is a long established fact that a reader will be distracted by the" +
                " readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a " +
                "more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it " +
                "look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their" +
                " default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various " +
                "versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).");

        cartThree.click();
    }
    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
