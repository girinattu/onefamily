package uk.onefamily.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class DatesPrices extends BasePage {

    private String numOfPassengers_css = "[name='numAdults']";
    private String tourPriceSelected = "#tour-price span.ibecurr";
    private By availableDate = By.cssSelector("div.nbf_tpl_pms_calendar_day_available span.ibecurr[data-amt='1849.00']");
    private By calendarNext_id = By.cssSelector("div.nbf_tpl_pms_calendar_next");
    private By calendarBefore_css = By.cssSelector("div.nbf_tpl_pms_calendar_previous");
    private By bookOnline_button = By.cssSelector("#book-button div.nbf_tpl_pms_book_button div");

    public DatesPrices() {
        goToLinks("datesAndPrice");
    }

    public void selectNumOfPassengers(int num_of_passngrs) {
        goToLinks("datesAndPrice");
        waitForJStoLoad();
        getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(numOfPassengers_css)));
        Select passengers = new Select(getWebelementBy(By.cssSelector(numOfPassengers_css)));
        passengers.selectByVisibleText(String.valueOf(num_of_passngrs));
    }

    public void selectAvailableDate() {
        goToLinks("accommodation");
        waitForJStoLoad();
        goToLinks("datesAndPrice");
        if (getWebelementBy(calendarBefore_css).isDisplayed()) {
            getWebelementBy(calendarBefore_css).click();
            waitForJStoLoad();
        }
        boolean availableDatesToSelect = findAvailableDates();
        System.out.println(availableDatesToSelect);
        if (availableDatesToSelect) {
            getWait().until(ExpectedConditions.presenceOfElementLocated(availableDate));
            getWait().until(ExpectedConditions.elementToBeClickable(availableDate));
            try {
                getWebelementBy(availableDate).click();
            } catch (ElementClickInterceptedException ee) {
                getWebelementBy(availableDate).click();
            }
        } else {
            Assert.fail();
        }
    }

    private boolean findAvailableDates() {
        boolean availableDatePresent = getDriver().findElements(availableDate).size() > 0;
        while (getDriver().findElements(availableDate).size() == 0 && !availableDatePresent) {
            getWait().until(ExpectedConditions.elementToBeClickable(calendarNext_id));
            try {
                getWebelementBy(calendarNext_id).click();
            } catch (StaleElementReferenceException ee) {
                getWebelementBy(calendarNext_id).click();
            } catch (ElementClickInterceptedException ee) {
                waitForJStoLoad();
                getWebelementBy(calendarNext_id).click();
            }
            availableDatePresent = getDriver().findElements(availableDate).size() > 0;
            waitForJStoLoad();
        }
        return availableDatePresent;
    }

    public String getSelectedTourPrice() {
        waitForJStoLoad();
        return getWebelementBy(By.cssSelector(tourPriceSelected)).getText();
    }

    public void bookOnline() {
        scrollDown(100);
        getWait().until(ExpectedConditions.elementToBeClickable(bookOnline_button));
        getWebelementBy(bookOnline_button).click();
    }
}
