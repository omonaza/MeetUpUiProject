package saucelabs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@RunWith(Parameterized.class)
public class SauceLabsParallelExecution {

    //we will have a collection that will represent multiple configurations
    //and we will have a method that will read those configurations and depending on how many
    //elements in the collection we have, it will create that many parallel instances

    private static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    private static final String ACCESS_KEY = ConfigReader.getProperty("accessKey");
    private static final String URL = "https://"+USERNAME+":"+ACCESS_KEY+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";


    /*if you want to run your tests in parallel or if you want to run them across multiple browsers at the same time
    (multiple browser instances will be created and running simultaneously) you need to make sure
    that every driver that is runnin is thread local
     */

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<String> sessionId = new ThreadLocal<>();

    private String browser;
    private String version;
    private String os;

    public SauceLabsParallelExecution(String browser, String version, String os){
        this.browser = browser;
        this.version = version;
        this.os = os;
    }



    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(CapabilityType.BROWSER_NAME, browser);
        caps.setCapability(CapabilityType.BROWSER_VERSION, version);
        caps.setCapability(CapabilityType.PLATFORM_NAME, os);

        driver.set(new RemoteWebDriver(new URL(URL), caps));
        String id = ((RemoteWebDriver)driver.get()).getSessionId().toString();
        sessionId.set(id);

        return driver.get();
    }

    @Parameterized.Parameters
    public static Collection sauceBrowsersDataProvider(){
        return Arrays.asList(new Object[][]{
                {"chrome", "latest", "macOS 10.14"},
                {"firefox", "latest", "Windows 10"},
                //{"edge", "latest", "Windows 10"},
                //{"safari", "latest", "macOS 10.13"}
        });
    }





    @Test
    public void testSimpleAlert() throws MalformedURLException{

        WebDriver driver = createDriver(browser, version, os);

        driver.navigate().to("https://demoqa.com/alerts");
        driver.findElement(By.id("alertButton")).click();
        //now alert should pop up on the screen

        //to handle an alert first we need to create an object of Alert class
        Alert alert = driver.switchTo().alert();

        //we are working with a simple alert, there is only one action we can take on it- to accept it - hit "ok" if manually

        alert.accept(); // - hits OK on alert

        driver.findElement(By.id("timerAlertButton")).click();
        //because alert takes 5 sec to apper we need an explicit wait

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());

        alert.accept();

    }
    @Test
    public void testConfirmationAlert() throws MalformedURLException{

        WebDriver driver = createDriver(browser, version, os);
        driver.navigate().to("https://demoqa.com/alerts");

        driver.findElement(By.id("confirmButton")).click();

        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());

        //to dismiss the alert = click CANCEL
        alert.dismiss();

        WebElement result = driver.findElement(By.id("confirmResult"));

        Assert.assertTrue(result.getText().contains("Cancel"));

    }


    @After
    public void tearDown(){
        driver.get().quit();
    }








}
