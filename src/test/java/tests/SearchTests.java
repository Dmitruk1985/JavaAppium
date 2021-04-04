package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {
    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Search test")
    @Description("We make the search and make sure expected result is displayed")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("bject-oriented programming language");
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Cancel search")
    @Description("We cancel the search and make sure cancel button has disappeared")
    @Step("Starting test testCancelSearch")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCancelSearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.waitForCancelButtonToAppear();
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForCancelButtonToDissappear();
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Amount of not empty search")
    @Description("We make sure more than one result is found in case of correct search")
    @Step("Starting test testAmountOfNotEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfNotEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        searchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue("We found to few results", amount_of_search_results > 0);
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Amount of empty search")
    @Description("We make sure no results are found in case of incorrect search")
    @Step("Starting test testAmountOfEmptySearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testAmountOfEmptySearch() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "thtyujyefwefwefwst";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForEmptyResultsLabel();
        searchPageObject.assertThereIsNoResultsOfSearch();
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Make and cancel search")
    @Description("We make sure more than one result is found and then cancel search")
    @Step("Starting test testCancelSearchTest")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCancelSearchTest() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.waitForSearchResult("Java");
        int amount_of_articles = searchPageObject.getAmountOfFoundArticles();
        Assert.assertTrue("List has <=1 result", amount_of_articles > 1);
        searchPageObject.clickCancelSearch();
        searchPageObject.waitForSearchResultsToDisappear();
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("All search results have key word")
    @Description("We make sure all search results contain key word")
    @Step("Starting test testAllSearchResultsHaveKeyWordTest")
    @Severity(value = SeverityLevel.NORMAL)
    public void testAllSearchResultsHaveKeyWordTest() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInput();
        String search_line = "Java";
        searchPageObject.typeSearchLine(search_line);
        searchPageObject.waitForSearchResult(search_line);
        List<WebElement> titles = searchPageObject.getAllSearchTitles();
        searchPageObject.assertAllElementsContainText(titles, search_line);
    }

    @Test
    @Features(value ={@Feature(value="Search")})
    @DisplayName("Search results have title and description")
    @Description("We make sure first three search results have title and description")
    @Step("Starting test testSearchResultsHaveTitleAndDescription")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearchResultsHaveTitleAndDescription() {

        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.initSearchInputAndTypeSearchLine("Java");
        if(Platform.getInstance().isAndroid()) {
            searchPageObject.waitForElementByTitleAndDescription("Java", "Island of Indonesia");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "Programming language");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        }
        else  if(Platform.getInstance().isMw()){
            searchPageObject.waitForElementByTitleAndDescription("Java", "Indonesian island");
            searchPageObject.waitForElementByTitleAndDescription("JavaScript", "High-level programming language");
            searchPageObject.waitForElementByTitleAndDescription("Java (programming language)", "Object-oriented programming language");
        }

    }


}
