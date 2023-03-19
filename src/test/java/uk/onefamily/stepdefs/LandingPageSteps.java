package uk.onefamily.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import uk.onefamily.pages.LandingPage;

public class LandingPageSteps {
    LandingPage landingPage = new LandingPage();

    @Given("^a customer is on the mail travel webpage$")
    public void landingPage() {
        landingPage.getTitle();
        Assert.assertTrue("Title does not contain Mail Travel",
                landingPage.getTitle().contains("Mail Travel"));
    }

    @And("^the customer searches for (.*)$")
    public void searchTerm(String searchTerm) {
        landingPage.searchByDestination(searchTerm);
    }


}
