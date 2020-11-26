package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class SauceLabsDriver {

    public static final String USERNAME = ConfigReader.getProperty("saucelabsUsername");
    public static final String ACCESS_KEY = ConfigReader.getProperty("accessKey");
    public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

    public static WebDriver loadSauceLabsDriver(){
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "latest");
        WebDriver driver = null;

        try{
            driver = new RemoteWebDriver(new URL(URL), caps);
        }catch(Exception e){
            e.printStackTrace();
        }
        return driver;
    }
}
