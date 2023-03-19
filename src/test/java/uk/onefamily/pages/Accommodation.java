package uk.onefamily.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class Accommodation extends BasePage {

    private By doubleRoom = By.cssSelector("select[data-roomtype='Double ']");
    private By selectRooms = By.cssSelector("div.nbf_fg_pms_button_text");

    public Accommodation() {
        waitForJStoLoad();
    }

    public void selectADoubleRoom() {
        waitForJStoLoad();
        getWait().until(ExpectedConditions.presenceOfElementLocated(doubleRoom));
        //   getWait().until(ExpectedConditions.elementToBeSelected(doubleRoom));
        Select optionsToSelect = new Select(getWebelementBy(doubleRoom));
        optionsToSelect.selectByVisibleText("1");
        getWebelementBy(selectRooms).click();
    }

}
