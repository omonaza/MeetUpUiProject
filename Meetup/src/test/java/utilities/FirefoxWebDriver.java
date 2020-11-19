package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.concurrent.TimeUnit;

public class FirefoxWebDriver {
    //fill it out
    public static WebDriver loadFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();

        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(Boolean.parseBoolean(ConfigReader.getProperty("headless")));
        //options.addArguments("-headless");
        WebDriver driver = new FirefoxDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        return driver;
    }
}
