/**
 * @author  : youvegotnigel
 * created on 2021/01/30
 */
package base;

import com.extentManager.ExtentManager;
import com.utility.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import pages.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTests {

    private static final String fileSeparator = File.separator;

    public static WebDriver driver;
    protected LoginPage loginPage;


    @BeforeSuite
    public void runBeforeSuite(){

        DOMConfigurator.configure("log4j.xml");
        Log.info("######################################### START OF TEST SUITE #########################################");
        ExtentManager.setExtent();
    }

    @AfterSuite
    public void runAfterSuite(){
        Log.info("######################################### END OF TEST SUITE #########################################");
        ExtentManager.endReport();
    }

    @BeforeClass
    public void setup() {

        Properties props = System.getProperties();
        try {
            props.load(new FileInputStream(new File("resources\\test.properties")));
        } catch(Exception e) {
            Log.debug("Property File Not Found !");
            Log.debug("ERROR! : " + e);
            System.exit(-1);
        }

        //System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        //System.setProperty("webdriver.chrome.driver", props.getProperty("webdriver.chrome.driver"));

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(getChromeOptions());

//        WebDriverManager.firefoxdriver().setup();
//        driver = new FirefoxDriver();

        //get url from property file
        driver.get(props.getProperty("site.nqa_fa_app.url"));
        goHome();
    }

    private ChromeOptions getChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("disable-infobars"); // this is now disable by chrome latest versions
        options.setHeadless(false);
        return options;
    }

    public void goHome(){

        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


    //Take screenshots
    public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        String destination = System.getProperty("user.dir") + fileSeparator + "test-output" + fileSeparator + "html-report" + fileSeparator + "screenshots" +fileSeparator+ screenshotName + " - " + getTimestamp() + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public static String getTimestamp() {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        return currentDate;
    }

    public static String getExtentReportDirectory() {
        String extentReportDirectory = System.getProperty("user.dir") + fileSeparator + "test-output" + fileSeparator + "html-report";
        return extentReportDirectory;
    }

    public static String getProjectDirectory() {
        String projectDirectory = System.getProperty("user.dir");
        return projectDirectory;
    }
}
