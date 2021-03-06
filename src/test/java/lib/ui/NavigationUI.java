package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUI extends MainPageObject {

    protected static String MY_LISTS_LINK;
    protected static String OPEN_NAVIGATION;

    public NavigationUI(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMw()) {
            this.tryClickElementWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.waitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }

    public void openNavigation() {
        if(Platform.getInstance().isMw()) waitForElementAndClick(OPEN_NAVIGATION, "Can't find and click open navigation button", 5);
        else System.out.println("Method openNavigation() do nothing for platform: " + Platform.getInstance().getPlatformVar());
    }
}
