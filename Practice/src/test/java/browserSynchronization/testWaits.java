package browserSynchronization;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class testWaits {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        //driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public  void pageLoadTimeTest(){
        driver.get("https://www.amazon.com/");
    }




    @Test
    public void etsyExplicitWaitTest(){
        driver.get("https://www.etsy.com/");
        driver.findElement(By.cssSelector(".select-signin")).click();
        //this is our explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 4);

        WebElement registerButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".select-register")));
        Assert.assertTrue(registerButton.isDisplayed());

    }


    @Test
    public void disabledButtonTest(){
        driver.get("https://demoqa.com/dynamic-properties");
        WebElement button = driver.findElement(By.xpath("//button[@id='enableAfter']"));
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.elementToBeClickable(button));

        Assert.assertTrue(button.isEnabled());

    }

    @Test
    public void fileUploadTest(){
        driver.get("https://demoqa.com/upload-download");
        WebElement fileUploadButton = driver.findElement(By.xpath("//input[@id='uploadFile']"));

        String pathToTheFile = "/Users/rudenka93/Desktop/kitten-510651.jpg";

        fileUploadButton.sendKeys(pathToTheFile);

    }

}
