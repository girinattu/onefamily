package uk.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import uk.onefamily.utils.Utils;

import java.util.Random;

public class PsngersDetails extends BasePage {


    String paxFName, paxLName = null;
    String contactFLName = paxFName + " " + paxLName;
    private String paxTitle = "pax-a-title-num_to_replace";
    private String paxFirstName = "pax-a-first-num_to_replace";
    private String paxLastName = "pax-a-last-num_to_replace";
    private String paxdobd = "pax-a-dobd-num_to_replace";
    private String paxdobm = "pax-a-dobm-num_to_replace";
    private String paxdoby = "pax-a-doby-num_to_replace";
    private By contactName = By.id("contact-name");
    private By contactMobName = By.id("contact-mobile");
    private By email = By.id("contact-email");
    private By address1 = By.id("contact-address1");
    private By address2 = By.id("contact-address2");
    private By city = By.id("contact-city");
    private By postcode = By.id("contact-postcode");
    private String hearAbout = "contact-hearabout";
    private By continue_button = By.cssSelector("div.nbf_fancy_paxButton");

    private WebElement getContinueButton() {
        return getWebelementBy(continue_button);
    }

    private void reset_pax_details() {
        paxTitle = "pax-a-title-num_to_replace";
        paxFirstName = "pax-a-first-num_to_replace";
        paxLastName = "pax-a-last-num_to_replace";
        paxdobd = "pax-a-dobd-num_to_replace";
        paxdobm = "pax-a-dobm-num_to_replace";
        paxdoby = "pax-a-doby-num_to_replace";
    }

    private void selectPaxDetails(String paxDetails) {
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.id(paxDetails)));
        Select pax = new Select(getDriver().findElement(By.id(paxDetails)));
        pax.selectByIndex(new Random().nextInt(pax.getOptions().size() - 1));
        try {
            getWait().wait(100);
        } catch (Exception ee) {
        }
        waitForJStoLoad();
    }

    private void setPsnIDs(String strToRepl) {
        paxTitle = paxTitle.replace("num_to_replace", strToRepl);
        paxFirstName = paxFirstName.replace("num_to_replace", strToRepl);
        paxLastName = paxLastName.replace("num_to_replace", strToRepl);
        paxdobd = paxdobd.replace("num_to_replace", strToRepl);
        paxdobm = paxdobm.replace("num_to_replace", strToRepl);
        paxdoby = paxdoby.replace("num_to_replace", strToRepl);
    }

    public void enterPaxDetails(int numOfPax) throws Exception {
        waitForJStoLoad();
        for (int pax = 0; pax < numOfPax; pax++) {
            String strToRepl = String.valueOf(pax + 1);
            setPsnIDs(strToRepl);
            paxFName = Utils.getStringOf(5);
            paxLName = Utils.getStringOf(8);
            getWait().until(ExpectedConditions.presenceOfElementLocated(By.id(paxFirstName)));
            getWebelementBy(By.id(paxFirstName))
                    .sendKeys(paxFName);
            getWebelementBy(By.id(paxLastName))
                    .sendKeys(paxLName);
            selectDetails();
            reset_pax_details();
        }
        enterContactDetails();
        waitForJStoLoad();
    }

    private void selectDetails() {
        waitForJStoLoad();
        System.out.println(paxTitle);
        selectPaxDetails(paxTitle);
        waitForJStoLoad();
        selectPaxDetails(paxdobd);
        waitForJStoLoad();
        System.out.println(paxdobm);
        selectPaxDetails(paxdobm);
        waitForJStoLoad();
        selectPaxDetails(paxdoby);
    }

    private void enterContactDetails() {
        getWebelementBy(contactName).sendKeys(contactFLName);
        getWebelementBy(contactMobName).sendKeys("07" + Utils.getNumbersOf(8));
        getWebelementBy(email).sendKeys(paxFName + "@email.com");
        getWebelementBy(address1).sendKeys("Victoria Place");
        getWebelementBy(address2).sendKeys("115 Buckingham Palace Road");
        getWebelementBy(city).sendKeys("London");
        getWebelementBy(postcode).sendKeys("SW1W 9SJ");
        selectPaxDetails(hearAbout);
        getContinueButton().click();
    }


}
