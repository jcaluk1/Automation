package pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends BasePage {

    By emailInput = By.id("email_create");
    By createAccountBTN = By.id("SubmitCreate");
    By accountInfo = By.className("account_creation");

    public LoginPage(ChromeDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        baseObj.info("Enter email: " + email);
        baseObj.setTextBoxValue(emailInput, email);
    }

    public void clickOnCreateAnAccountButton() {
        baseObj.info("Click on Create an account button");
        baseObj.click(createAccountBTN);
        baseObj.waitForElementVisibility(accountInfo);
    }

    public void enterEmailAddressAndClickOnCreateAccount(String email) {
        enterEmail(email);
        clickOnCreateAnAccountButton();
    }
}
