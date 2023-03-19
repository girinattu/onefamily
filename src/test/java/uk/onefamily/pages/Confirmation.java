package uk.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Confirmation extends BasePage{
    private By totalCost = By.cssSelector("tr.nbf_basket_total span.ibecurr");

    private By adultsPricing = By.cssSelector("div.nbf_pms_pricing_box .txt-end");

    public String getTotalCost(){
        waitForJStoLoad();
        getWait().until(ExpectedConditions.presenceOfElementLocated(totalCost));
        return getWebelementBy(totalCost).getText();
    }

    public String getAdultPricing(){
        waitForJStoLoad();
        getWait().until(ExpectedConditions.presenceOfElementLocated(adultsPricing));
        return getWebelementBy(adultsPricing).getText();
    }

}
