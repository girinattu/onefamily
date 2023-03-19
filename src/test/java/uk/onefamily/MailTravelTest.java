package uk.onefamily;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import uk.onefamily.pages.*;

public class MailTravelTest {
    LandingPage landingPage;
    SearchResults searchResults;
    Itinerary productPage;
    DatesPrices datesPrices;
    Accommodation accommodation;
    PsngersDetails psngersDetails;
    Confirmation cnfmation;


    @Test(groups = "landingpage")
    public void openlandingPage(){
        landingPage = new LandingPage();
        landingPage.searchByDestination("India");
        assertTrue(landingPage.getTitle().contains("Mail Travel"),"Title does not contain Mail Travel");

    }
    @Test(groups = "search", dependsOnGroups = "landingpage")
    public void searchResults(){
        searchResults = new SearchResults();
        searchResults.clickMoreInfoOnAProduct("11 Days - Classic Escorted Tours");
        searchResults.waitForJStoLoad();
        searchResults.goToLinks("itinerary");
    }

    @Test(groups = "itinerary", dependsOnGroups = "search")
    public void itinerary() {
        productPage = new Itinerary();
        assertTrue(productPage.getNumberOfDaysItinerary()==11,
                "Expected itinerary days : 11 \n " +
                        "Actual itinerary days: "+productPage.getNumberOfDaysItinerary());
        assertTrue(!productPage.getSupplierPhoneText().isEmpty()
                && productPage.getSupplierPhoneText().length()>10,
                "Supplier phone number days is not displayed or incorrect");

    }

    @Test(groups = "datesselection", dependsOnGroups = "itinerary")
    public void datesSelection() {
        datesPrices = new DatesPrices();
      //  datesPrices.goToLinks("datesAndPrice");
        datesPrices.selectAvailableDate();
        datesPrices.selectNumOfPassengers(2);

        assertTrue(datesPrices.getSelectedTourPrice().trim().equalsIgnoreCase("£3,698")
        ,"Expected Price: £3,698\n" +
                        "Actual Price: "+ datesPrices.getSelectedTourPrice());
        datesPrices.bookOnline();
    }

    @Test(groups = "accommodation", dependsOnGroups = "datesselection")
    public void accommodation(){
        accommodation = new Accommodation();
        accommodation.selectADoubleRoom();
    }
    @Test(groups = "paxdetail", dependsOnGroups = "accommodation")
    public void paxDetails() throws Exception{
        psngersDetails = new PsngersDetails();
        psngersDetails.enterPaxDetails(2);

    }

    @Test(groups = "confirmation", dependsOnGroups = "paxdetail")
    public void confirmationPage() throws Exception{
        cnfmation = new Confirmation();
        assertEquals(cnfmation.getTotalCost(),"£3,698");
        assertEquals(cnfmation.getAdultPricing(),"£1,849 x 2");
    }

    @AfterClass
    public void closeBrowser(){
        landingPage.takeScreenshots();
        landingPage.closeBrowsers();
    }

}
