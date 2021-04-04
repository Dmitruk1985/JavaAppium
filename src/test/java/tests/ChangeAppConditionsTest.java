package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class ChangeAppConditionsTest extends CoreTestCase {
    @Test
    @Features(value ={@Feature(value="Search"), @Feature(value="Article")})
    @DisplayName("Change screen orientation on search results")
    @Description("We change screen orientation twice, and make sure article's title has not been changed")
    @Step("Starting test testChangeScreenOrientationOnSearchResults")
    @Severity(value = SeverityLevel.MINOR)
    public void testChangeScreenOrientationOnSearchResults() {
        if (Platform.getInstance().isMw()) return;
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String title_before_rotation = articlePageObject.getArticleTitle();
        rotateScreenLandscape();
        String title_after_rotation = articlePageObject.getArticleTitle();
        Assert.assertEquals("Article title have been change after rotation", title_before_rotation, title_after_rotation);
        rotateScreenPortrait();
        String title_after_second_rotation = articlePageObject.getArticleTitle();
        Assert.assertEquals("Article title have been change after rotation", title_before_rotation, title_after_second_rotation);
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Check search article in background")
    @Description("We run app in background, and after make sure search result has not been changed")
    @Step("Starting test testCheckSearchArticleInBackground")
    @Severity(value = SeverityLevel.MINOR)
    public void testCheckSearchArticleInBackground() {
        if (Platform.getInstance().isMw()) return;
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Object-oriented programming language");
        backgroundApp(2);
        searchPageObject.waitForSearchResult("Object-oriented programming language");
    }
}
