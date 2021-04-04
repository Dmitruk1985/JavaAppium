package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private static final String
            LOGIN_BUTTON = "xpath://body//a[text()='Log in']",
          //  LOGIN_BUTTON = "link:Log in",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        waitForElementPresent(LOGIN_BUTTON, "Can't find auth button", 5);
        waitForElementAndClick(LOGIN_BUTTON, "Can't find and click auth button", 5);
    }

    public void enterLoginData(String login, String password) {
        waitForElementAndSendKeys(LOGIN_INPUT, login, "Can't find and put login into login input", 5);
        waitForElementAndSendKeys(PASSWORD_INPUT, password, "Can't find and put password into password input", 5);
    }

    public void submitForm() {
        waitForElementAndClick(SUBMIT_BUTTON, "Can't find and click submit button", 5);
    }
}
