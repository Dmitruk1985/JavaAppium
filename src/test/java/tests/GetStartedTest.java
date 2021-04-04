package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    @DisplayName("Pass through welcome")
    @Description("For iOS platform we should pass through welcome screen")
    @Step("Starting test testPassThroughWelcome")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPassThroughWelcome(){
        if(Platform.getInstance().isAndroid() || Platform.getInstance().isMw()) return;
        WelcomePageObject welcomePage = new WelcomePageObject(driver);
        welcomePage.waitForLearnMoreLink();
        welcomePage.clickNextButton();
        welcomePage.waitForNewWaysToExplore();
        welcomePage.clickNextButton();
        welcomePage.waitForAddOrEditPreferredLangText();
        welcomePage.clickNextButton();
        welcomePage.waitForLearnMoreAboutDataCollectedText();
        welcomePage.clickGetStartedButton();
    }
}
