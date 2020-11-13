package basicLocators;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class basicLocatorsTests {

    @Test
    public void testIdLocator() throws InterruptedException{
        //to set uo the driver instead of system.setProperty we will use methods from bonigarcia dependency
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.amazon.com/");

        WebElement hamburgerMenuLink = driver.findElement(By.id("nav-hamburger-menu"));

        hamburgerMenuLink.click(); //to click on a webelement

        Thread.sleep(2000);

        WebElement helloTextOnSideMenu = driver.findElement(By.id("hmenu-customer-name"));

        //isDisplayed() - method in selenium that returns boolean - true if weblelemnt id displayed on the screen,
        //false - if it is not
        Assert.assertTrue(helloTextOnSideMenu.isDisplayed());

        driver.close();

    }

    @Test
    public void testNameLocator() throws InterruptedException{
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        //this line will maximize your chrome window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");

        WebElement searchInputField = driver.findElement(By.name("q"));

        //sendKeys() - method used to type into an input field
        String searchCriteria = "apple";
        searchInputField.sendKeys(searchCriteria);

        driver.findElement(By.name("btnK")).click();

        Thread.sleep(2000);

        Assert.assertTrue(driver.getTitle().contains(searchCriteria));

        driver.close();


    }

    @Test
    public void testLinkAndPartialLinkTextLocators() throws InterruptedException{
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();
        //this line will maximize your chrome window
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");

        driver.findElement(By.linkText("Gmail")).click();

        Thread.sleep(2000);

        //driver.findElement(By.linkText("Create an account")).click();
        driver.findElement(By.partialLinkText(" an ")).click();

    }

    @Test
    public void testClassNameLocator(){
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.google.com/");

        WebElement electionLink = driver.findElement(By.className("NKcBbd"));

        //getText() - return a strings - the visible text of the webelement
        String textOfLink = electionLink.getText(); //Find out how to vote this election

        Assert.assertEquals("Find out how to vote this election", textOfLink);

        driver.close();

    }

    @Test
    public void testTagNameLocator(){

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://www.practiceselenium.com/");

        WebElement element = driver.findElement(By.tagName("h1"));

        Assert.assertTrue(element.getText().contains("tea"));

        driver.close();

    }






    @Test
    public void testingWorkingWithMultipleElements() throws InterruptedException{
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("devxschool" + Keys.ENTER);

        Thread.sleep(3000);

        //first we will find a first link and print its text
        WebElement firstLink = driver.findElement(By.tagName("a"));

        System.out.println("This is the first link on the page: "+firstLink.getText());

        System.out.println("__________________________________________");

        List<WebElement> allLinksOnThePage = driver.findElements(By.tagName("a"));
        for(WebElement link: allLinksOnThePage){
            System.out.println(link.getText());
        }

        driver.close();


    }




}
