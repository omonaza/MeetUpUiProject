package advancedMouseActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AdvancedMouseActions {

    WebDriver driver;

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @After
    public void tearDown(){
        driver.close();
        // we always close the driver because each test/scenario is independent of each other
        //every test starts with a fresh clean browser instance
    }

    @Test
    public void testDoubleClickAndRightClick(){

        driver.navigate().to("https://demoqa.com/buttons");

        WebElement doubleClickButton = driver.findElement(By.cssSelector("#doubleClickBtn"));

        WebElement rightClickButton = driver.findElement(By.cssSelector("#rightClickBtn"));

        //in order to perform advanced mouse actions we need to create an Actions object
        //Actions is selenium class that conyains all advance mouse interaction

        Actions actions = new Actions(driver);
        // .perform() - must use with every action  - it makes the action happen, executes the action
        actions.doubleClick(doubleClickButton).perform();
        //lets verify that the button was clicked

        WebElement doubleClickMessage = driver.findElement(By.cssSelector("#doubleClickMessage"));

        Assert.assertTrue("Double click message is not displayed",doubleClickMessage.isDisplayed());

        actions.contextClick(rightClickButton).perform();

        Assert.assertTrue("Right click message is not displayed", driver.findElement(By.cssSelector("#rightClickMessage")).isDisplayed());

    }

    @Test
    public void testHoveringTheMouse(){
        driver.navigate().to("https://demoqa.com/tool-tips");

        WebElement hoverButton = driver.findElement(By.id("toolTipButton"));

        Actions actions = new Actions(driver);

        actions.moveToElement(hoverButton).perform();

        try{
            String attribute = hoverButton.getAttribute("aria-describedby");
        }catch(Exception e){
            Assert.fail("Tooltip is not displayed");
        }

        //add code to hover over Interactions


        WebElement interactions = driver.findElement(By.xpath("//div[text()='Interactions']"));
        actions.moveToElement(interactions).perform();

        //how to move to the buttom of the page?
    }

    @Test
    public void testMovingTheSlider(){
        driver.navigate().to("https://demoqa.com/slider");

        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        String defaultValue = slider.getAttribute("value");//25

        Actions actions = new Actions(driver);

        actions.clickAndHold(slider).moveByOffset(20, 0).release().perform();

        String newValue = slider.getAttribute("value");

        Assert.assertTrue(Integer.parseInt(newValue) > Integer.parseInt(defaultValue));

    }

    @Test
    public void testDragAndDrop(){
        driver.navigate().to("https://demoqa.com/droppable");

        WebElement source = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.xpath("//div[@id='droppable'][1]"));

        Actions actions = new Actions(driver);

        actions.dragAndDrop(source,target).perform();

        //Assert.assertTrue(target.getAttribute("class").contains("ui-state-highlight"));
        Assert.assertTrue(target.getText().equals("Dropped!"));

    }






}
