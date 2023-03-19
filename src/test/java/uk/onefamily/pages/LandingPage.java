package uk.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LandingPage extends BasePage {


    private String searchText_id = "searchtext_freetext_search_form";

    private String searchButton_id = ".nbf_button";


    public void searchByDestination(String searchStr) {
        waitForJStoLoad();
        acceptCookies();
        getWait().until(ExpectedConditions.elementToBeClickable(By.id(searchText_id)));
        getWebelementBy(By.id(searchText_id)).sendKeys(searchStr);
        getWebelementBy(By.cssSelector(searchButton_id)).click();
    }

    public String getTitle() {
        return getDriver().getTitle().trim();
    }


}
