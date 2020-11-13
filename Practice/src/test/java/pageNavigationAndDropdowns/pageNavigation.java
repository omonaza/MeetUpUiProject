package pageNavigationAndDropdowns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class pageNavigation {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Test
    public void testNavigationMethods() throws InterruptedException{
        //this will work the same way as driver.get()
        driver.navigate().to("http://www.practiceselenium.com/welcome.html");

        WebElement letsTalkTeaLink = driver.findElement(By.xpath("//a[contains(text(),' Talk Tea')]"));

        letsTalkTeaLink.click();

        //how to verify that lets talk tea link is selected
        //isSelected() - you can only use with checkboxes and radio buttons
        //verify the title
        //we can verify the attribute class

        letsTalkTeaLink = driver.findElement(By.xpath("//a[contains(text(),' Talk Tea')]"));

        Assert.assertTrue(driver.getTitle().contains("Let's Talk Tea"));
        Assert.assertTrue(letsTalkTeaLink.findElement(By.xpath("./..")).getAttribute("class").equals("active"));

        driver.navigate().back(); // - goes to the previous page || letsTalkTeaLink becomes stale (old)

        WebElement welcomeLink = driver.findElement(By.xpath("//a[contains(text(),'Welcome')]"));

        Assert.assertTrue(welcomeLink.findElement(By.xpath("./..")).getAttribute("class").equals("active"));

        driver.navigate().forward(); //goes to next page

        //this line will break our code

        letsTalkTeaLink = driver.findElement(By.xpath("//a[contains(text(),' Talk Tea')]"));
        Assert.assertTrue(letsTalkTeaLink.findElement(By.xpath("./..")).getAttribute("class").equals("active"));

        WebElement nameField = driver.findElement(By.xpath("//input[@name='name']"));

        nameField.sendKeys("DevX");

        driver.navigate().refresh();

        nameField = driver.findElement(By.xpath("//input[@name='name']"));

        nameField.sendKeys("Chicago");


    }




}
