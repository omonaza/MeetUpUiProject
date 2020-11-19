package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SearchResultsPage extends FooterPage{

    public SearchResultsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//div[starts-with(@data-testid, 'searchResults-')]//p[1]")
    public List<WebElement> searchResultsTitles;

    @FindBy(xpath = "//div[starts-with(@data-element-name, 'searchResults')][1]")
    public WebElement firstSearchResult;

    @FindBy(id = "tracking--searchComponentInput")
    public WebElement searchInputField;
}
