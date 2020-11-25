package utilities;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;



@RunWith(Parameterized.class)
public class SaucelabsParallel {

    public static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    public static final String ACCESS_KEY = ConfigReader.getProperty("accessKey");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private ThreadLocal<String> sessionId = new ThreadLocal<String>();

    private String browser;
    private String version;
    private String os;

    private WebDriver createDriver(String browser, String version, String os) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if(version != null){
            capabilities.setCapability(CapabilityType.BROWSER_VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, os);

        driver.set(new RemoteWebDriver(new URL(URL), capabilities));
        String id = ((RemoteWebDriver) driver.get()).getSessionId().toString();
        sessionId.set(id);

        return driver.get();
    }

    public SaucelabsParallel(String browser, String version, String os) throws Exception{
        this.browser = browser;
        this.version = version;
        this.os = os;
    }

    @Parameterized.Parameters
    public static Collection sauceBrowserDataProvider() {
         return Arrays.asList(new Object[][]{
                 {"chrome", "latest", "macOS 10.14"},
                 {"firefox", "latest", "Windows 7"},
        });

    }


    @After
    public void tearDown(){
        driver.get().quit();
    }



    @Test
    public void testPromptAlert()throws Exception{
        //asks you to provide information
        WebDriver driver = createDriver(browser, version, os);
        driver.get("https://demoqa.com/alerts");

        driver.findElement(By.id("promtButton")).click();
        //now I have a prompt alert on the screen

        Alert alert = driver.switchTo().alert();

        String info = "DevXSchool";

        alert.sendKeys(info);
        alert.accept();

        WebElement result = driver.findElement(By.id("promptResult"));

        Assert.assertTrue(result.getText().contains(info));

    }

    @Test
    public void testConfirmationAlert() throws Exception{
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









}
