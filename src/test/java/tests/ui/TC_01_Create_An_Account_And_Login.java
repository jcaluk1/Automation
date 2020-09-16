package tests.ui;

import framework.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.CreateAccountPage;
import pages.HomePage;
import pages.LoginPage;

public class TC_01_Create_An_Account_And_Login extends BaseTest {

    LoginPage login;
    HomePage homePage;
    CreateAccountPage createAccountPage;

    @DataProvider(name = "getTestData")
    public Object[][] getTestData() {
        return new Object[][]{{"email" + baseObj.generateRandomNumberOfFixLength(4) + "@mail.com", "Mr.", "Jasmin", "Caluk", "password2", "30", "January ", "1992", true, true, "AddressFirst", "AddressLast", "Company", "Address", "Address2", "NY", "New York", "00000", "Infoooo", "45678", "987234", "alias"},
                {"email" + baseObj.generateRandomNumberOfFixLength(4) + "@mail.com", "Mrs.", "Neka", "Nekic", "passwordd", "8", "May ", "2005", false, true, "AddressFirst", "AddressLast", "Company", "Address", "Address2", "Mexico", "New Mexico", "00000", "Infoooo info", "45333", "987256756534", "alias 2"}};
    }

    @BeforeClass(alwaysRun = true)
    public void setup() {
        login = new LoginPage(driver);
        homePage = new HomePage(driver);
        createAccountPage = new CreateAccountPage(driver);
    }

    @Test(dataProvider = "getTestData", groups = "uiTests")
    public void validateLogin(String email, String title, String firstName, String lastName, String password, String dayOfBirth, String monthOfBirth, String yearOfBirth, Boolean signForNewsLetter, Boolean receiveSpecialOffers, String addressFirstName, String addressLastName, String company, String address, String address2, String city, String state, String postcode, String additionalInfo, String homePhone, String mobilePhone, String addressAlias) {
        homePage.clickOnSignIn();
        login.enterEmailAddressAndClickOnCreateAccount(email);
        createAccountPage.filInDataAndCreateAccount(title, firstName, lastName, password, dayOfBirth, monthOfBirth, yearOfBirth, signForNewsLetter, receiveSpecialOffers, addressFirstName, addressLastName, company, address, address2, city, state, postcode, additionalInfo, homePhone, mobilePhone, addressAlias);
        homePage.validateSignOutButtonIsDisplayed();
    }

    @AfterMethod(alwaysRun = true)
    public void signOut() {
        homePage.clickOnSignOut();
    }
}
