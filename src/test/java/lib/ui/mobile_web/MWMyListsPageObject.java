package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        OPTIONS_BUTTON_BY_TITLE = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_title' and @text='{TITLE}']" +
                "/ancestor::*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@content-desc='More options']";
        DELETE_ARTICLE_OPTION = "id:org.wikipedia:id/reading_list_item_remove_text";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE')]/../../div[contains(@class, 'watched')]";
        ARTICLE_DESCRIPTION_BY_TITLE_TPL = "//ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]/following-sibling::div//span[@class='modified-enhancement']";
    }

    public MWMyListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
