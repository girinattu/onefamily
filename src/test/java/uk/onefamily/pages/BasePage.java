package uk.onefamily.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.onefamily.core.BrowserSetup;

import java.io.File;
import java.time.Duration;

public class BasePage {
    static BrowserSetup browserSetup = new BrowserSetup();


    private By acceptCookies = By.cssSelector("div.onetrust-banner-options #onetrust-accept-btn-handler");

    private By datesPricesLink = By.cssSelector("a[href*=dates_and_prices_title]");

    private By itinerarylink = By.cssSelector("a[href*=itinerary_title]");
    private By accommodationlink = By.cssSelector("a[href*=accommodation_title]");

    public static WebDriver getDriver() {
        return browserSetup.getDriver();
    }

    public static WebDriverWait getWait() {
        return browserSetup.getWait();
    }

    public void acceptCookies() {
        getWait().until(ExpectedConditions.presenceOfElementLocated(acceptCookies));
        if (getDriver().findElement(acceptCookies).isEnabled() && getDriver().findElement(acceptCookies).isDisplayed()) {
            getWait().until(ExpectedConditions.elementToBeClickable(acceptCookies));
            try {
                Thread.sleep(2000);
            } catch (Exception ee) {
            }
            getDriver().findElement(acceptCookies).click();
        }
    }

    public boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofMillis(30000));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        return wait.until(jsLoad);
    }

    public void scrollDown(int scroller) {
        JavascriptExecutor jse = (JavascriptExecutor) getDriver();
        jse.executeScript("window.scrollBy(0," + String.valueOf(scroller) + ")");
    }

    public void goToLinks(String link) {
        switch (link) {
            case "itinerary":
                scrollDown(250);
                waitForJStoLoad();
                break;
            case "datesAndPrice":
                getWait().until(ExpectedConditions.elementToBeClickable(datesPricesLink));
                getDriver().findElement(datesPricesLink).click();
                scrollDown(250);
                waitForJStoLoad();
//                getWait().until(ExpectedConditions.elementToBeClickable(datesPricesLink));
//                getDriver().findElement(datesPricesLink).click();
                break;

            case "accommodation":
                waitForJStoLoad();
                getWait().until(ExpectedConditions.elementToBeClickable(datesPricesLink));
                getDriver().findElement(accommodationlink).click();

                break;
            default:
                System.out.println("Please specify a link to go to:");
        }
    }

    public WebElement getWebelementBy(By element) {
        return getDriver().findElement(element);
    }

    public void closeBrowsers() {
        getDriver().manage().deleteAllCookies();
        browserSetup.closeBrowser();
    }

    public void takeScreenshots() {
        File file = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String screenshotBase64 = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
