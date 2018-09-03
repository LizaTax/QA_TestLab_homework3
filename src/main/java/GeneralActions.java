package myprojects.automation.assignment3;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Contains main script actions that may be used in scripts.
 */
public class GeneralActions {
    private WebDriver driver;
    private WebDriverWait wait;

    public GeneralActions(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    /**
     * Logs in to Admin Panel.
     * @param email
     * @param password
     */
    public void login(String email, String password) {
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("passwd")).sendKeys(password);
        driver.findElement(By.name("submitLogin")).click();
    }

    /**
     * Adds new category in Admin Panel.
     * @param categoryName
     */
    public void createCategory(String categoryName) {
        verifyElementAppears(By.id("subtab-AdminCatalog"));
        hoverMouse(By.id("subtab-AdminCatalog"));
        clickOnElement(By.id("subtab-AdminCategories"));
        clickOnElement(By.id("page-header-desc-category-new_category"));
        verifyElementAppears(By.id("name_1"));
        setEditText(By.id("name_1"), categoryName);
        clickOnElement(By.id("category_form_submit_btn"));
        verifyElementWithTextPresent(By.cssSelector(".bootstrap > .alert-success:not(.hide)"), "Создано");
    }

    public void closeBrowser() {
        driver.close();
    }

    /**
     * Checks that new category appears in Categories table
     * @param categoryName
     */
    public void verifyCategoryIsAdded(String categoryName) {
        setEditText(By.name("categoryFilter_name"), categoryName);
        clickOnElement(By.id("submitFilterButtoncategory"));
        verifyElementWithTextPresent(By.cssSelector("#table-category tbody tr:nth-child(1) td:nth-child(3)"), categoryName);
    }

    public void openUrl(String url){
        driver.get(url);
    }

    private void clickOnElement(By by) {
        WebElement e = wait.until(ExpectedConditions.elementToBeClickable(by));
        if (e == null) {
            throw new ElementNotInteractableException(String.format("Element %s can not be clicked", by));
        }
        e.click();
    }

    private void hoverMouse(By by) {
        WebElement element = driver.findElement(by);
        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent( 'mouseover', true, true );"
                + "fireOnThis.dispatchEvent(evObj);";
        ((JavascriptExecutor)driver).executeScript(code, element);
    }


    private void setEditText(By by, String text) {
        WebElement element = driver.findElement(by);
        if (!element.getAttribute("value").isEmpty()) {
            element.clear();
        }
        element.sendKeys(text);
    }

    private void verifyElementAppears(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    private void verifyElementWithTextPresent(By by, String text) {
        WebElement element = driver.findElement(by);
        if (!element.getText().contains(text)) {
            element.findElement(By.xpath(String.format(".//*[contains(., '%s')]", text)));
        }
        verifyElementIsVisible(by);
    }

    private void verifyElementIsVisible(By by) {
        if (!driver.findElement(by).isDisplayed()) {
            throw new NotFoundException("Error: element " + by + " is not visible on the page!");
        }
    }
}

