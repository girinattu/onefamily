package uk.onefamily.stepdefs;

import io.cucumber.java.en.And;
import org.junit.Assert;
import uk.onefamily.pages.Itinerary;
import uk.onefamily.pages.SearchResults;

public class SearchPageStepDefs {

    SearchResults searchResults = new SearchResults();
    Itinerary productPage = new Itinerary();

    @And("^the customer selects (.*)$")
    public void customerSelectsHoliday(String holidaySelected) {
        searchResults.clickMoreInfoOnAProduct(holidaySelected);
        searchResults.waitForJStoLoad();
        searchResults.goToLinks("itinerary");
        productPage = new Itinerary();
        Assert.assertTrue("Expected itinerary days : 11 \n " +
                        "Actual itinerary days: " + productPage.getNumberOfDaysItinerary(),
                productPage.getNumberOfDaysItinerary() == 11);
        Assert.assertTrue("Supplier phone number days is not displayed ",
                !productPage.getSupplierPhoneText().isEmpty()
                        && productPage.getSupplierPhoneText().length() > 10);
    }


}
