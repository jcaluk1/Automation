package pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePage extends BasePage {
    GridResults gridResults = new GridResults(driver);

    By signInBTN = By.className("login");
    By signOutBTN = By.className("logout");
    By bestSellersLNK = By.cssSelector("a[href='http://automationpractice.com/index.php?controller=best-sales']");
    By womenCategoryLNK = By.cssSelector("a[href='http://automationpractice.com/index.php?id_category=3&controller=category']");
    By searchField = By.id("search_query_top");
    By searchBTN = By.name("submit_search");

    public HomePage(ChromeDriver driver) {
        super(driver);
    }

    public void clickOnSignIn() {
        baseObj.info("Click on Sign In button");
        baseObj.click(signInBTN);
        baseObj.waitForBrowserToLoadCompletely();
    }

    public void validateSignOutButtonIsDisplayed() {
        if (baseObj.isElementDisplayed(signOutBTN))
            baseObj.pass("Sign out button is displayed");
        else
            baseObj.fail("Sign out button is not displayed");
    }

    public void clickOnSignOut() {
        baseObj.info("Click on Sign Out");
        baseObj.click(signOutBTN);
    }

    public void clickOnBestSellers() {
        baseObj.info("Click on Best sellers");
        baseObj.click(bestSellersLNK);
        baseObj.waitForBrowserToLoadCompletely();
    }

    public void clickOnWomenCategory() {
        baseObj.info("Click on Women category");
        baseObj.click(womenCategoryLNK);
        baseObj.waitForBrowserToLoadCompletely();
    }

    public void search(String searchCriteria) {
        baseObj.info("Enter " + searchCriteria + " in search field");
        baseObj.setTextBoxValue(searchField, searchCriteria);
        baseObj.click(searchBTN);
        gridResults.waitForSearchResults();
    }


}
