package multipleWindowsAndIframes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Driver;

import java.util.Set;

public class MultipleWindows {
    WebDriver driver;

    @Before
    public void setUp(){
         driver = Driver.getDriver();
    }


    @After
    public void tearDown(){

        driver.close();//--> closing only tab. If you have only one tab open - it will close the tab and the browser
        driver.quit(); //--> will close the browser and all open tabs and windows
    }

    @Test
    public void uniqueId(){
        //window handle - a unique id of a currently open page(tab, window)

        driver.navigate().to("https://www.google.com/");

        String currentWindowHandle = driver.getWindowHandle();

        System.out.println(currentWindowHandle); //4294967297
        //CDwindow-7A93BC40F9293C671C802D3D1B83A19F
        //CDwindow-430B48E4A0F454B34B61562DCDB79060

    }

    @Test
    public void multipleWindowsTest(){
        driver.navigate().to("http://www.practiceselenium.com/");//main window
        driver.findElement(By.linkText("seleniumframework.com")).click();
        //new window has opened but selenium is still looking at out main window

        //in order to switch to a new window I need to know what is the id of my current window
        String mainWindow = driver.getWindowHandle();

        //I also need to get the ids of all open windows - currently 2
        Set<String> allWindowHandles = driver.getWindowHandles();//2 uniques ids

        for(String windowHandle: allWindowHandles){
            //check if the window handle is not a current window - then switch to it
            if(!windowHandle.equals(mainWindow)){
                driver.switchTo().window(windowHandle);
            }
        }

        driver.findElement(By.linkText("Choosing an Automation Solution")).click();

        WebElement agenda = driver.findElement(By.xpath("//h3/strong[text()='Agenda:']"));

        Assert.assertTrue(agenda.isDisplayed());

        //go back to your main page
        driver.switchTo().window(mainWindow);

        Assert.assertTrue(driver.findElement(By.linkText("Check Out")).isDisplayed());

    }


    @Test
    public void threeWindows(){
        driver.navigate().to("https://demoqa.com/links");
        driver.findElement(By.linkText("Home")).click();
        //the second page has opened
        String mainWindow = driver.getWindowHandle();

        Set<String> twoWindowHandles = driver.getWindowHandles();

        for(String windowHandle: twoWindowHandles){
            if(!windowHandle.equals(mainWindow)){
                driver.switchTo().window(windowHandle);
            }
        }
        //now I'm ready to click join now button
        driver.findElement(By.className("banner-image")).click();

        String secondWindowHandle = driver.getWindowHandle();

        //in order to interact with the elements on the third window I need to switch to that window

        Set<String> threeWindowHandles = driver.getWindowHandles();

        for (String windowHandle: threeWindowHandles){
            if(!windowHandle.equals(mainWindow) && !windowHandle.equals(secondWindowHandle)){
                driver.switchTo().window(windowHandle);
            }
        }

        Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Selenium Training']")).isDisplayed());

    }


}
