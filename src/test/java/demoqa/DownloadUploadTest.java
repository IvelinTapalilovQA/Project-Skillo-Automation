package demoqa;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadUploadTest {

    WebDriver driver;

    private static final String DOWNLOAD_PATH = "src\\test\\resources\\download\\";

    @BeforeSuite
    public void setupBeforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupForTest() {
        driver = new ChromeDriver(configChromeOptions());
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void testDownload() throws InterruptedException {

        driver.get("https://demoqa.com/upload-download");
        driver.manage().window().maximize();
        WebElement downloadButton = driver.findElement(By.id("downloadButton"));
        downloadButton.click();

        String filename = "sampleFile.jpeg";
        File file = new File(DOWNLOAD_PATH.concat(filename));
        Assert.assertTrue(isFileDownloaded(file));
    }

    private boolean isFileDownloaded(File file) throws InterruptedException {
        int waitTime = 20;
        int counter = 0;

        while (counter < waitTime){
            if(file.exists()){
                return true;
            }
            Thread.sleep(1000);
            counter ++;
        }
        return false;
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @AfterSuite
    public void deleteDownloadFiles() throws IOException {
        File downloadDirectory = new File(DOWNLOAD_PATH);
        FileUtils.cleanDirectory(downloadDirectory);
        String[] fileList = downloadDirectory.list();

        if(fileList != null && fileList.length == 0) {
            System.out.println("Downloaded files are deleted!");
        }
        else{
              System.out.println("Downloaded files are not deleted!");
        }
    }

    private ChromeOptions configChromeOptions(){
        String downloadDir = Paths.get(DOWNLOAD_PATH).toAbsolutePath().toString();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        return chromeOptions;
    }
}
