package steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.SearchResultsPage;
import utilities.Driver;

public class SearchFunctionalitySteps {
    HomePage homePage = new HomePage();
    SearchResultsPage searchResultsPage = new SearchResultsPage();


    @When("^the user types \"([^\"]*)\" in the search field$")
    public void the_user_types_in_the_search_field(String searchCriteria) throws Throwable {
        homePage.searchInputField.sendKeys(searchCriteria);
    }

    @When("^the user hits Search button$")
    public void the_user_hits_Search_button() throws Throwable {
        homePage.searchButton.click();
    }

    @Then("^verify all search results contain \"([^\"]*)\" in the title$")
    public void verify_all_search_results_contain_in_the_title(String searchCriteria) throws Throwable {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 7);
        wait.until(ExpectedConditions.visibilityOf(searchResultsPage.firstSearchResult));

        for(WebElement resultTile: searchResultsPage.searchResultsTitles){
            Assert.assertTrue(resultTile.getText().toLowerCase().contains(searchCriteria));
        }

    }
}
