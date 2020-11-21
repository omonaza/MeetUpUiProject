package multipleWindowsAndIframes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;

public class MultipleIframes {

    WebDriver driver;

    @Before
    public void setUp(){
        driver = Driver.getDriver();
    }

    @After
    public void tearDown(){
        Driver.closeDriver();
    }

    @Test
    public void multipleIframes(){
        driver.navigate().to("https://demoqa.com/frames");
        //first we need to switch to the iframe
        //1. Find your iframe
        //WebElement iframeOne = driver.findElement(By.id("frame1"));

        //switch to the iframe
        //driver.switchTo().frame(iframeOne);
        //2nd way
        driver.switchTo().frame("frame1");
        //3rd way
        //driver.switchTo().frame(0);
        WebElement textInsideFirstIframe = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(textInsideFirstIframe.isDisplayed());

        //now I need to verify the same for the second iframe text

        driver.switchTo().defaultContent();

        driver.switchTo().frame("frame2");
        WebElement textInsideSecondIframe = driver.findElement(By.id("sampleHeading"));
        Assert.assertTrue(textInsideSecondIframe.isDisplayed());


    }

    @Test
    public void nestedFrames(){
        driver.navigate().to("https://demoqa.com/nestedframes");
        //now I need to verify child frame but becaus eit is nested inside of parent frame my main html doc cannot see it
        //so I need to switch to parent frame first
        driver.switchTo().frame("frame1");
        //now when I'm inside parent frame I can see iframes nested in it directly
        driver.switchTo().frame(0);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Child Iframe']")).isDisplayed());
        //now I need to verify parent frame text

        driver.switchTo().parentFrame();

        Assert.assertTrue(driver.findElement(By.xpath("//body[text()='Parent frame']")).isDisplayed());


    }


}
