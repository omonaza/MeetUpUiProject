package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class FooterPage {
        // we can add common elements and then extend Footer by other pages that contain the same footer

    // if you have a group of WebElements that stay on every page of your application -->
    //then you create a BasePage with those common elements and extend BasePage by every page class
    public FooterPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[text()='Calendar']")
    public WebElement calendarLink;

    @FindBy(linkText = "Topics")
    public WebElement topicsLink;

}
