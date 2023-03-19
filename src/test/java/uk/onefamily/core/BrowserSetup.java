package uk.onefamily.core;


import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

/*
This class handles all the browser set up and webdriver initialiations

 */
public class BrowserSetup {
    static Logger log = Logger.getLogger(String.valueOf(BrowserSetup.class));
    String browser = null;

    WebDriver driver = null;

    WebDriverWait webDriverWait = null;

    /*
    Constructor:
    Gets the browser type from the env var browser
    and initialises the browser
    Returns a webdriver object
     */
    public BrowserSetup() {
        getBrowser();
        initialiseBrowser();
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initialiseBrowser();
        }
        return driver;
    }

    public WebDriverWait getWait() {
        webDriverWait = new WebDriverWait(driver, Duration.ofMillis(30000));
        return webDriverWait;
    }

    public String getBrowser() {
        browser = System.getenv("browser");
        return browser;
    }

    /*
    Chromedriver option has been created.
    Need to create various options based on the browser type in a separate methods
     */
    public WebDriver initialiseBrowser() {
        switch (browser.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver(setChromeOptions());
                driver.get("https://www.mailtravel.co.uk");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                break;
            case "FIREFOX":
                driver = new FirefoxDriver();
                break;
            case "IE":
                driver = new InternetExplorerDriver();
                break;
            default:
                log.severe("The values for the browser env variable is - CHROME, SAFARI, FIREFOX & IE. " +
                        "Add more to the swtich-case in BrowserSetip.initialiseBrowser");
        }
        return driver;
    }

    public void closeBrowser() {
        driver.close();
    }

    /*
    Returns the options for chromedriver
     */
    private ChromeOptions setChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");
        return options;
    }


}
