package myprojects.automation.assignment3;

import myprojects.automation.assignment3.utils.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;

/**
 * Base script functionality, can be used for all Selenium scripts.
 */
public abstract class BaseScript {

    /**
     *
     * @return New instance of {@link WebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link FirefoxDriver} instance by default.
     */
    public static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        switch (browser) {
            default:
                System.setProperty(
                        "webdriver.gecko.driver",
                        new File(BaseScript.class.getResource("/geckodriver.exe").getFile()).getPath());
                return new FirefoxDriver();
        }
    }

    /**
     * Creates {@link WebDriver} instance with timeout and browser window configurations.
     *
     * @return New instance of {@link EventFiringWebDriver} object. Driver type is based on passed parameters
     * to the automation project, returns {@link FirefoxDriver} instance by default.
     */
    public static EventFiringWebDriver getConfiguredDriver() {
        return new EventFiringWebDriver(getDriver());
       // TODO configure browser window (set timeouts, browser pindow position) and connect loggers.
        //throw new UnsupportedOperationException("Method doesn't return configured WebDriver instance");
    }
}
