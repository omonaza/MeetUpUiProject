package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import pages.HomePage;
import utilities.Driver;

public class BasicValidationSteps {

    HomePage homePage = new HomePage();

    //paste you generated steps here
    @Given("^the user is on the meetup homepage$")
    public void the_user_is_on_the_meetup_homepage() throws Throwable {
        Driver.getDriver().get("https://www.meetup.com/");
    }

    @Then("^verify the title contains \"([^\"]*)\"$")
    public void verify_the_title_contains(String expectedTitle) throws Throwable {
        Assert.assertTrue("Title verification failed",Driver.getDriver().getTitle().contains(expectedTitle));

    }

    @Then("^verify join meetup button is displayed$")
    public void verify_join_meetup_button_is_displayed() throws Throwable {
        Assert.assertTrue(homePage.joinMeetupButton.isDisplayed());
    }

}
