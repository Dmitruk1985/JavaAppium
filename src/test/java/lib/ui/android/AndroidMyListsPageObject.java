package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
        OPTIONS_BUTTON_BY_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']" +
                "/ancestor::*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@content-desc='More options']";
        DELETE_ARTICLE_OPTION = "id:org.wikipedia:id/reading_list_item_remove_text";
    }

    public AndroidMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
