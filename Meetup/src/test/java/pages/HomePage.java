package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;


public class HomePage extends FooterPage{
    //this is where I will store all the elements from Meetup home page

    //we create scenarios frist and based on the scenario we add elements to the pages classes
    //POM - Page Object Model - design concept(pattern) where each page of application is represented by a separate class in the framework

    //@FindBy annotation is used to find an element or a list of webelements that match provided locator

    //before we can use these elements we need to initialize them -->
    // we use page factory class  - mothod initElements();

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(xpath = "//a[@aria-label='Join Meetup']")
    public WebElement joinMeetupButton;

    @FindBy(xpath = "//a[text()='Log in' and contains(@href, 'secure')]")
    public WebElement logInButton;

    @FindBy(xpath = "//a[text()='Sign up' and contains(@href, 'secure')]")
    public WebElement signUpButton;


    @FindBy(id = "tracking--searchComponentInput")
    public WebElement searchInputField;

    @FindBy(id = "tracking--searchComponentButton")
    public WebElement searchButton;

//besides webelements you can add reusable methods in the page classes as well

    public void search(String searchCriteria){
        searchInputField.sendKeys(searchCriteria);
        searchButton.click();
    }

    //FindBys - will find elements that match all provided locators
    @FindBys( {
            @FindBy(xpath = "//a[text()='Sign up' and contains(@href, 'secure')]"),
            @FindBy(id = "tracking--searchComponentInput")
    })
    public WebElement something;


    //FindAll - will try to find one or more elements that match ANY of the provided locators
    @FindAll({
            @FindBy(xpath = "//a[text()='Sign up' and contains(@href, 'secure')]"),
            @FindBy(id = "tracking--searchComponentInput")
    })
    public List<WebElement> somethingElse;



}
