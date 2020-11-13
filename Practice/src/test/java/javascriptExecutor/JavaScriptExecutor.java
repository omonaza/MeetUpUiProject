package javascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class JavaScriptExecutor {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

    }

    @After
    public void tearDown(){
        driver.close();
    }


    @Test
    public void testJSExecutor() throws InterruptedException{

        JavascriptExecutor js = (JavascriptExecutor) driver;
        //this script will take us tp the url ( same as driver.get())
        js.executeScript("window.location='https://www.etsy.com/'");

        WebElement signInButton = driver.findElement(By.cssSelector(".select-signin"));

        //add a red border to my element before I interact with it
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", signInButton, "border: 2px solid red");
        Thread.sleep(2000);
        js.executeScript("arguments[0].click();", signInButton);
        Thread.sleep(1000);

        //highlight and type email
        WebElement emailField = driver.findElement(By.id("join_neu_email_field"));
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", emailField, "border: 2px solid red");

        Thread.sleep(2000);

        js.executeScript("arguments[0].setAttribute('value',arguments[1]);", emailField, "devxschool@gmail.com");

        Thread.sleep(2000);

        //highlight and type password
        WebElement password = driver.findElement(By.id("join_neu_password_field"));
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", password, "border: 2px solid red");

        js.executeScript("arguments[0].setAttribute('value',arguments[1]);", password, "abc123");

        //highlight and click on sign in button

        WebElement signInButton2 = driver.findElement(By.xpath("//button[@value='sign-in']"));

        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", signInButton2, "border: 2px solid yellow");

        Thread.sleep(2000);

        js.executeScript("arguments[0].click();", signInButton2);

        Thread.sleep(2000);

        Assert.assertTrue(driver.findElement(By.cssSelector(".wt-alert.wt-alert--inline.wt-alert--error-01.wt-mb-xs-3")).isDisplayed());


    }

}
