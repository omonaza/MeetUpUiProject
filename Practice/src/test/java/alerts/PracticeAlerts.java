package alerts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

public class PracticeAlerts {
    WebDriver driver;

    @Before
    public void setUp() {
        driver=Driver.getDriver();
    }

    @After
    public void tearDown(){
        Driver.closeDriver();
    }


    @Test
    public void testSimpleAlert(){
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
    public void testConfirmationAlert(){
        driver.navigate().to("https://demoqa.com/alerts");

        driver.findElement(By.id("confirmButton")).click();

        Alert alert = driver.switchTo().alert();

        System.out.println(alert.getText());

        //to dismiss the alert = click CANCEL
        alert.dismiss();

        WebElement result = driver.findElement(By.id("confirmResult"));

        Assert.assertTrue(result.getText().contains("Cancel"));

    }

    @Test
    public void testPromptAlert(){
        //asks you to provide information
        driver.navigate().to("https://demoqa.com/alerts");

        driver.findElement(By.id("promtButton")).click();
        //now I have a prompt alert on the screen

        Alert alert = driver.switchTo().alert();

        String info = "DevXSchool";

        alert.sendKeys(info);
        alert.accept();

        WebElement result = driver.findElement(By.id("promptResult"));

        Assert.assertTrue(result.getText().contains(info));

    }

}
