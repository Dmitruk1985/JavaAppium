package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListTests extends CoreTestCase {
    private static final String name_of_folder = "Learning programming";
    private static final String login = "dmitry.dmitruk";
    private static final String password = "Dmitruk1985";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWithSubstring("bject-oriented programming language");
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        if (Platform.getInstance().isAndroid()) articlePageObject.addArticleToMyList(name_of_folder);
        else articlePageObject.addArticlesToMySaved();
        if (Platform.getInstance().isMw()) {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login",
                    article_title,
                    articlePageObject.getArticleTitle());
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(article_title);
    }

    @Test
    public void testSaveTwoArticlesToMyList() {

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        MyListsPageObject myListsPageObject = MyListsPageObjectFactory.get(driver);
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);
        String name_of_folder = "MyList";
        String firstTitle = "Java (programming language)";
        String secondTitle = "Java";
        String article_title = "";
        String firstDescription;
        String secondDescription;
        String first_title = "";
        String second_title = "";

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.openArticleAndAddItToMyList("Java", firstTitle, name_of_folder, true);
            articlePageObject.openArticleAndAddItToMyList("Java", secondTitle, name_of_folder, false);
        } else if (Platform.getInstance().isMw()) {
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
            articlePageObject.waitForTitleElement();
            first_title = articlePageObject.getArticleTitle();
            articlePageObject.addArticlesToMySaved();
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();
            articlePageObject.waitForTitleElement();
            Assert.assertEquals("We are not on the same page after login", first_title, articlePageObject.getArticleTitle());
            articlePageObject.addArticlesToMySaved();
            searchPageObject.initSearchInput();
            searchPageObject.typeSearchLine("Java");
            searchPageObject.clickByArticleWithSubstring("Indonesian island");
            articlePageObject.waitForTitleElement();
            second_title = articlePageObject.getArticleTitle();
            articlePageObject.addArticlesToMySaved();
        }
        articlePageObject.closeArticle();
        navigationUI.openNavigation();
        navigationUI.clickMyLists();
        if (Platform.getInstance().isAndroid()) myListsPageObject.openFolderByName(name_of_folder);
        myListsPageObject.swipeByArticleToDelete(first_title);
        if (Platform.getInstance().isMw()) myListsPageObject.assertArticleIsPresentInMyListByDescription(second_title);
        else if (Platform.getInstance().isAndroid()) {
            myListsPageObject.openArticle(secondTitle);
            String secondTitleAfterDeleting = articlePageObject.getArticleTitle();
            Assert.assertEquals("Article's title was changed", secondTitle, secondTitleAfterDeleting);
        }
    }
}
