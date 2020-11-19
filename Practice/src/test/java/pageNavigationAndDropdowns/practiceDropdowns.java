package pageNavigationAndDropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Driver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class practiceDropdowns {
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
    public void practiceSelectElement(){
        Driver.getDriver().navigate().to("https://www.devxschool.com/enrollment/");

        WebElement selectTag = Driver.getDriver().findElement(By.cssSelector("#form-field-ads"));

        Select dropdown = new Select(selectTag);

        List<WebElement> allOptions = dropdown.getOptions();

        for (WebElement option:allOptions){
            System.out.println(option.getText());
        }

        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

        Assert.assertTrue(selectedOptions.size() == 1
                && selectedOptions.get(0).getText().equals("From a friend"));

        //select Instagram
        //dropdown.selectByIndex(2);
        //dropdown.selectByVisibleText("Instagram");
        dropdown.selectByValue("Instagram");

        selectedOptions = dropdown.getAllSelectedOptions();

        Assert.assertTrue(selectedOptions.size() == 1
                && selectedOptions.get(0).getText().equals("Instagram"));


    }


    @Test
    public void testMultiSelect() throws InterruptedException{
        driver.navigate().to("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");

        WebElement selectElement = driver.findElement(By.xpath("//select[@id='option-droup-demo']"));

        Select dropdown = new Select(selectElement);
        Assert.assertTrue(dropdown.isMultiple());
        //1st way to deselect
        dropdown.deselectAll();
        //2nd way
        //dropdown.deselectByVisibleText("HTML");
        //dropdown.deselectByVisibleText("CSS");

        dropdown.selectByVisibleText("Java");
        dropdown.selectByVisibleText("Oracle");

        List<WebElement> selectedOptions = dropdown.getAllSelectedOptions();

        Assert.assertTrue(selectedOptions.size()==2);

        for (WebElement option:selectedOptions){
            Assert.assertTrue(option.getText().equals("Java") ||
                    option.getText().equals("Oracle"));
        }
    }

    @Test
    public void expediaTest() throws InterruptedException{
        driver.get("https://www.expedia.com/");

        try{
            driver.findElement(By.xpath("//a[@aria-controls='wizard-cruise-pwa']")).click();

        }catch(ElementClickInterceptedException e){
            WebDriverWait wait = new WebDriverWait(driver, 8);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[starts-with(text(),'You could be getting')]")));

            driver.findElement(By.xpath("//div[text()='Sign in']/..")).click();
        }

        driver.findElement(By.xpath("//a[@aria-controls='wizard-cruise-pwa']")).click();


        WebElement selectElement = driver.findElement(By.cssSelector("#cruise-destination"));

        Select dropdown = new Select(selectElement);

        //select Alaska by value

        dropdown.selectByValue("alaska");
        //verify Alaska is selected
        Assert.assertTrue(dropdown.getAllSelectedOptions().get(0).getText().equals("Alaska"));

        //select Africa by visible text

        dropdown.selectByVisibleText("Africa");
        Assert.assertTrue(dropdown.getAllSelectedOptions().size()==1
                && dropdown.getAllSelectedOptions().get(0).getText().equals("Africa"));

        //selct Mexico by index
        dropdown.selectByIndex(3);
        Assert.assertTrue(dropdown.getAllSelectedOptions().size()== 1
                && dropdown.getAllSelectedOptions().get(0).getText().equals("Mexico"));

        for(WebElement option: dropdown.getOptions()){
            System.out.println(option.getText());

        }


    }



}
