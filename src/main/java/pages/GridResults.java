package pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class GridResults extends BasePage {

    By products = By.className("product_list");

    public GridResults(ChromeDriver driver) {
        super(driver);
    }

    public void waitForSearchResults() {
        baseObj.waitForElementVisibility(products);
    }

    public void validateNumberOfProductsOnGrid(String expectedNumber) {
        baseObj.validateActualAndExpectedText(String.valueOf(driver.findElement(products).findElements(By.className("ajax_block_product")).size()), expectedNumber);
    }

    public List<String> getAllProductNames() {
        List<String> productNames = new ArrayList<>();
        WebElement productContainer = driver.findElement(products);
        for (int i = 0; i < productContainer.findElements(By.className("product-name")).size(); i++) {
            productNames.add(productContainer.findElements(By.className("product-name")).get(i).getText());
        }
        return productNames;
    }
}
