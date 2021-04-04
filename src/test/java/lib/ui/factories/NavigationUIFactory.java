package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.android.AndroidNavigationUI;
import lib.ui.android.AndroidSearchPageObject;
import lib.ui.ios.IOSNavigationUI;
import lib.ui.ios.IOSSearchPageObject;
import lib.ui.mobile_web.MWNavigationUI;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIFactory {
    public static NavigationUI get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) return new AndroidNavigationUI(driver);
        else if (Platform.getInstance().isMw()) return new MWNavigationUI(driver);
        else return new IOSNavigationUI(driver);
    }
}
