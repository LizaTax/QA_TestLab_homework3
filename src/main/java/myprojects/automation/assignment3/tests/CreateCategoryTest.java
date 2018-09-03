package myprojects.automation.assignment3.tests;

import myprojects.automation.assignment3.BaseScript;
import myprojects.automation.assignment3.GeneralActions;
import myprojects.automation.assignment3.utils.WebDriverLogger;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class CreateCategoryTest extends BaseScript {

    private static final String EMAIL = "webinar.test@gmail.com";
    private static final String PASSWORD = "Xcg7299bnSmMuRLp9ITw";

    public static void main(String[] args){
        EventFiringWebDriver driver = BaseScript.getConfiguredDriver();
        driver.register(new WebDriverLogger());
        GeneralActions actions = new GeneralActions(driver);
        String categoryName  = "Skirts";
        actions.openUrl("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        actions.login(EMAIL, PASSWORD);
        actions.createCategory(categoryName);
        actions.verifyCategoryIsAdded(categoryName);
        actions.closeBrowser();
    }
}
