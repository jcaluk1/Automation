package pages;

import framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateAccountPage extends BasePage {

    By firstNameInput = By.id("customer_firstname");
    By lastNameInput = By.id("customer_lastname");
    By passwordInput = By.id("passwd");
    By dayOfBirthDD = By.id("days");
    By monthOfBirthDD = By.id("months");
    By yearOfBirthDD = By.id("years");
    By newsletterCHK = By.id("uniform-newsletter");
    By specialOffersCHK = By.id("uniform-optin");
    By addressFirstNameInput = By.id("firstname");
    By addressLastNameInput = By.id("lastname");
    By addressCompanyInput = By.id("company");
    By addressInput = By.id("address1");
    By address2Input = By.id("address2");
    By cityInput = By.id("city");
    By stateDD = By.id("id_state");
    By postCodeInput = By.id("postcode");
    By additionalInfoInput = By.id("other");
    By homePhoneInput = By.id("phone");
    By mobilePhoneInput = By.id("phone_mobile");
    By addressAliasInput = By.id("alias");
    By submitAccountBTN = By.id("submitAccount");

    public CreateAccountPage(ChromeDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        baseObj.info("Enter first name: " + firstName);
        baseObj.setTextBoxValue(firstNameInput, firstName);
    }

    public void enterLastName(String lastName) {
        baseObj.info("Enter last name: " + lastName);
        baseObj.setTextBoxValue(lastNameInput, lastName);
    }

    public void enterPassword(String password) {
        baseObj.info("Enter password: " + password);
        baseObj.setTextBoxValue(passwordInput, password);
    }

    public void selectDateOfBirth(String day, String month, String year) {
        baseObj.info("Enter date of birth: " + day + " " + month + " " + year);
        baseObj.selectByValue(dayOfBirthDD, day);
        baseObj.selectByVisibleText(monthOfBirthDD, month);
        baseObj.selectByValue(yearOfBirthDD, year);
    }

    public void clickOnSignUpForNewsletter(Boolean signForNewsletter) {
        if (signForNewsletter) {
            baseObj.info("Click on Sign up for our newsletter!");
            baseObj.click(newsletterCHK);
        } else {
            baseObj.info("Did not click on Sign up for our newsletter!");
        }
    }

    public void clickOnReceiveSpecialOffers(Boolean receiveSpecialOffers) {
        if (receiveSpecialOffers) {
            baseObj.info("Click on Receive special offers from our partners!");
            baseObj.click(specialOffersCHK);
        } else {
            baseObj.info("Did not click on Receive special offers from our partners!");
        }
    }

    public void enterAddressFirstName(String addressFirstName) {
        baseObj.info("Enter address first name: " + addressFirstName);
        baseObj.setTextBoxValue(addressFirstNameInput, addressFirstName);
    }

    public void enterAddressLastName(String addressLastName) {
        baseObj.info("Enter address last name: " + addressLastName);
        baseObj.setTextBoxValue(addressLastNameInput, addressLastName);
    }

    public void enterAddressCompany(String company) {
        baseObj.info("Enter company: " + company);
        baseObj.setTextBoxValue(addressCompanyInput, company);
    }

    public void enterAddress(String address) {
        baseObj.info("Enter address: " + address);
        baseObj.setTextBoxValue(addressInput, address);
    }

    public void enterAddress2(String address2) {
        baseObj.info("Enter address (Line 2): " + address2);
        baseObj.setTextBoxValue(address2Input, address2);
    }

    public void enterCity(String city) {
        baseObj.info("Enter city: " + city);
        baseObj.setTextBoxValue(cityInput, city);
    }

    public void selectState(String state) {
        baseObj.info("Select state: " + state);
        baseObj.selectByVisibleText(stateDD, state);
    }

    public void selectTitle(String title) {
        By titleLocator = By.xpath("//label[contains(.,'" + title + "')]");
        baseObj.click(titleLocator);
    }

    public void enterPostcode(String postcode) {
        baseObj.info("Enter postcode: " + postcode);
        baseObj.setTextBoxValue(postCodeInput, postcode);
    }

    public void enterAdditionalInfo(String additionalInfo) {
        baseObj.info("Enter additional info: " + additionalInfo);
        baseObj.setTextBoxValue(additionalInfoInput, additionalInfo);
    }

    public void enterHomePhone(String homePhone) {
        baseObj.info("Enter home phone: " + homePhone);
        baseObj.setTextBoxValue(homePhoneInput, homePhone);
    }

    public void enterMobilePhone(String mobilePhone) {
        baseObj.info("Enter mobile phone: " + mobilePhone);
        baseObj.setTextBoxValue(mobilePhoneInput, mobilePhone);
    }

    public void enterAddressAlias(String addressAlias) {
        baseObj.info("Enter address alias: " + addressAlias);
        baseObj.setTextBoxValue(addressAliasInput, addressAlias);
    }

    public void clickSubmitAccount() {
        baseObj.info("Click on Submit account");
        baseObj.click(submitAccountBTN);
        baseObj.waitForBrowserToLoadCompletely();

    }

    public void filInDataAndCreateAccount(String title, String firstName, String lastName, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth, Boolean signForNewsLetter, Boolean receiveSpecialOffers, String addressFirstName, String addressLastName, String company, String address, String address2, String city, String state, String postcode, String additionalInfo, String homePhone, String mobilePhone, String addressAlias) {
        selectTitle(title);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPassword(password);
        selectDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
        clickOnSignUpForNewsletter(signForNewsLetter);
        clickOnReceiveSpecialOffers(receiveSpecialOffers);
        enterAddressFirstName(addressFirstName);
        enterAddressLastName(addressLastName);
        enterAddressCompany(company);
        enterAddress(address);
        enterAddress2(address2);
        enterCity(city);
        selectState(state);
        enterPostcode(postcode);
        enterAdditionalInfo(additionalInfo);
        enterHomePhone(homePhone);
        enterMobilePhone(mobilePhone);
        enterAddressAlias(addressAlias);
        clickSubmitAccount();
    }
}
