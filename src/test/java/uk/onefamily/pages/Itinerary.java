package uk.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class Itinerary extends BasePage {

    private By supplierPhone = By.id("supplier-phone");

    private By viewFullItinerary = By.id("display-more");

    private By dayButtons = By.cssSelector("button.day-button span");

    public String getSupplierPhoneText(){
       return getWebelementBy(supplierPhone).getText().trim();
    }

    public int getNumberOfDaysItinerary(){
        waitForJStoLoad();
        getWait().until(ExpectedConditions.elementToBeClickable(viewFullItinerary));
        getWebelementBy(viewFullItinerary).click();
        return getDriver().findElements(dayButtons).size();
    }

}
