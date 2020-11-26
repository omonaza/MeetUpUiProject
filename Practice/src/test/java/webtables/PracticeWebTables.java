package webtables;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Driver;

import java.util.List;

public class PracticeWebTables {

  @After
  public void tearDown(){
      Driver.closeDriver();
  }

  @Test
    public void testOneRowMultipleColumns(){
        Driver.getDriver().navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/");

        Actions actions = new Actions(Driver.getDriver());
        WebElement button = Driver.getDriver().findElement(By.id("win1"));
        actions.moveToElement(button).perform();

        //we need to print out all the books written by Dan Brown
        int columns = Driver.getDriver().findElements(By.xpath("//table[@id='BooksAuthorsTable']/tbody/tr[4]/td")).size();

        for(int i = 2; i< columns; i++){
          String xpath = "//table[@id='BooksAuthorsTable']/tbody/tr[4]/td["+i+"]";
          WebElement book = Driver.getDriver().findElement(By.xpath(xpath));
          System.out.println(book.getText());
      }
  }

  //Task: print out the names of all authors - same table

    @Test
    public void testMultipleRowsOneColumn(){
        Driver.getDriver().navigate().to("https://chandanachaitanya.github.io/selenium-practice-site/");

        Actions actions = new Actions(Driver.getDriver());
        WebElement button = Driver.getDriver().findElement(By.id("win1"));
        actions.moveToElement(button).perform();

        int rows = Driver.getDriver().findElements(By.xpath("//table[@id='BooksAuthorsTable']/tbody/tr")).size();//7

        for(int i = 2; i<= rows; i++){
            String xpath = "//table[@id='BooksAuthorsTable']/tbody/tr["+i+"]/td[1]";
            System.out.println(Driver.getDriver().findElement(By.xpath(xpath)).getText());
        }

        System.out.println("*****************************");

        List<WebElement> authors = Driver.getDriver().findElements(By.xpath("//table[@id='BooksAuthorsTable']/tbody/tr/td[1]"));

        for(WebElement a: authors){
            System.out.println(a.getText());
        }

    }

    @Test
    public void findValueInTable(){
      Driver.getDriver().get("https://www.w3schools.com/html/html_tables.asp");

    int rows = Driver.getDriver().findElements(By.xpath("//table[@id='customers']/tbody/tr/td[3]")).size();

    for(int i = 2; i<=rows; i++){
        String xpath = "//table[@id='customers']/tbody/tr["+i+"]/td[3]";
        WebElement country = Driver.getDriver().findElement(By.xpath(xpath));
        if(country.getText().contains("Mexico")){
            //here I need to get a company name
            //extension xpath - use existing webelement to find another webelement
            System.out.println(country.findElement(By.xpath("./preceding-sibling::td[2]")).getText());
        }
        }

    }



}
