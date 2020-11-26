package saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.ConfigReader;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabsDriver {
    //we need to connect our code to our sauce labs account
    //username, access key, our url
    private static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    private static final String ACCESS_KEY = ConfigReader.getProperty("accessKey");
    private static final String URL = "https://"+USERNAME+":"+ACCESS_KEY+"@ondemand.us-west-1.saucelabs.com:443/wd/hub";




    public static WebDriver loadSauceLabsDriver() {
        //to set up our test browser, os, version we use Desired Capabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "macOS 10.13");
        caps.setCapability("browserName", "firefox");
        caps.setCapability("browserVersion", "latest");


        WebDriver driver = null;
        try{
            driver = new RemoteWebDriver(new URL(URL), caps);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        return driver;
    }

}
