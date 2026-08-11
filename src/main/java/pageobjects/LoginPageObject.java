package pageobjects;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageuis.LoginPageUI;

public class LoginPageObject extends BasePage {
    private final WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public DashboardPageObject loginToSystem(String username, String password) {
        sendkeyToElement(LoginPageUI.USERNAME_TEXTBOX,  username);
        sendkeyToElement(LoginPageUI.PASSWORD_TEXTBOX, password);
        clickToElement(LoginPageUI.LOGIN_BUTTON);
        return new DashboardPageObject(driver);
    }
}
