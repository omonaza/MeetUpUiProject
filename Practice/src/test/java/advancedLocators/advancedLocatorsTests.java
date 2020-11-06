package advancedLocators;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class advancedLocatorsTests {

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();


        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
    }


    @Test
    public void testCssSelectorLocator() throws InterruptedException{
        driver.get("https://www.amazon.com/");
        WebElement searchInput = driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']"));
        searchInput.sendKeys("iphone" + Keys.ENTER);
        Thread.sleep(3000);

        List<WebElement> brands = driver.findElements(By.cssSelector("li[id^='p_89']"));

        for (WebElement brand: brands){
            System.out.println(brand.getText());
        }

    }

    @Test
    public void testXpathLocator() throws InterruptedException{
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.findElement(By.xpath("//input[@name='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath("//span[text()='Password']/preceding-sibling::input")).sendKeys("admin123");
        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        Thread.sleep(2000);
        WebElement welcomeText = driver.findElement(By.xpath("//div[@id='branding']/a[@class='panelTrigger']"));

        Assert.assertTrue(welcomeText.getText().contains("Paul"));

    }


    @After
    public void tearDown(){
        driver.close();
    }


    //task
    //go the demoqa page--> select yes --> verify yes is selected by checking the text under the radio buttons
    //then verify No is disabled - isEnabled()
    //then get all of the radio buttons into a collection--> iterate over collection-->
    // verify that webelement with text "Yes" is selected using isSelected() method

    @Test
    public void radioButtonsPractice() throws InterruptedException{
        driver.get("https://demoqa.com/radio-button");



        driver.findElement(By.xpath("//label[@for='yesRadio']")).click();


        String textUnderTheRadioButton = driver.findElement(By.xpath("//p[@class='mt-3']")).getText();

        Assert.assertTrue(textUnderTheRadioButton.endsWith("Yes"));

        WebElement noRadioButton = driver.findElement(By.xpath("//input[@id='noRadio']"));

        //1st way to perform verification
        Assert.assertTrue(!noRadioButton.isEnabled());
        //2nd way to perofrm verification is to check class attribute

        String classValue = noRadioButton.getAttribute("class"); //custom-control-input disabled
        Assert.assertTrue(classValue.endsWith("disabled"));

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio' and @name='like']/following-sibling::label"));

        for (WebElement radio: radioButtons){
            if(radio.getText().equals("Yes")){
                Assert.assertTrue(radio.findElement(By.xpath("./preceding-sibling::input")).isSelected());
            }

        }

    }

    



}
