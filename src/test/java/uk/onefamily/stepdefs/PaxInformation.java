package uk.onefamily.stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import uk.onefamily.pages.Accommodation;
import uk.onefamily.pages.Confirmation;
import uk.onefamily.pages.DatesPrices;
import uk.onefamily.pages.PsngersDetails;

public class PaxInformation {

    DatesPrices datesPrices;
    Accommodation accommodation;
    PsngersDetails psngersDetails;
    Confirmation cnfmation;
    int pssngers = 0;

    @Then("^the customer is able to select (\\d) passengers$")
    public void detailsForPax(int numOfPax) {
        datesPrices = new DatesPrices();
        datesPrices.selectAvailableDate();
        datesPrices.selectNumOfPassengers(numOfPax);
        pssngers = numOfPax;
    }

    @And("^the price is £(.*) per passenger and the customer can fill the passenger details$")
    public void priceCheckFillPaxDetails(String price) throws Exception {
        String totalActualPrice = datesPrices.getSelectedTourPrice().replace("£", "").replace(",", "");
        System.out.println(totalActualPrice);
        int actualPrice = Integer.valueOf(totalActualPrice);
        int expectedPrice = Integer.valueOf(price) * 2;
        Assert.assertTrue("Expected Price: £" + String.valueOf(price) + "\n" +
                        "Actual Price: " + datesPrices.getSelectedTourPrice(),
                actualPrice == expectedPrice);
        datesPrices.bookOnline();
        accommodation = new Accommodation();
        accommodation.selectADoubleRoom();
        psngersDetails = new PsngersDetails();
        psngersDetails.enterPaxDetails(pssngers);
        cnfmation = new Confirmation();
        Assert.assertEquals("£3,698", cnfmation.getTotalCost());
        Assert.assertEquals("£1,849 x 2", cnfmation.getAdultPricing());
    }
}