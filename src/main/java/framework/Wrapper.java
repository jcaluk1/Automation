package framework;

import framework.reporter.ExtentTestManager;
import org.apache.log4j.Logger;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Wrapper {

    protected WebDriver driver;
    protected Integer timeout = 30;
    private static final Logger LOGGER = Logger.getLogger(Wrapper.class);

    public Wrapper() {
    }

    public Wrapper(WebDriver driver) {
        this.driver = driver;
    }

    public ExtentTest getExtentTest() {
        return ExtentTestManager.getTest();
    }

    public void pass(String msg) {
        LOGGER.info(String.format("[PASS]-%s", msg));
        getExtentTest().pass("<b style='color:green;'>" + msg + "");
    }

    public void fail(String msg) {
        LOGGER.info(String.format("[FAIL]-%s", msg));
        getExtentTest().fail("<b style='color:red;'>" + msg + "");
        Assert.fail(msg);
    }

    public void info(String msg) {
        LOGGER.info(msg);
        getExtentTest().info(msg);
    }

    public void error(String msg, Throwable e) {
        LOGGER.debug(e);
        getExtentTest().error("<b style='color:orange;'>" + msg + "");
        getExtentTest().info(e);
        Assert.fail(msg, e);
    }

    public void validateActualAndExpectedText(String actual, String expected) {

        if (actual.trim().equalsIgnoreCase(expected.trim())) {
            pass("Text Validation Passed" + ".  Actual text: [" + actual + "]  AND  expected: [" + expected + "]");
        } else {
            fail("Text Validation Failed " + ".  Actual text: [" + actual + "]  AND  expected: [" + expected + "]");
        }
        Assert.assertEquals(actual, expected);
    }

    public void click(By elementLocator) {
        try {
            WebElement element = driver.findElement(elementLocator);
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementNotVisibleException e) {
            error("" + elementLocator + " " + " has not been clicked because is not visible on Page. Selenium is not able to perform click.", e);
        } catch (StaleElementReferenceException e) {
            try {
                WebElement element = driver.findElement(elementLocator);
                new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
                element.click();
            } catch (StaleElementReferenceException ex) {
                error("Element with locator " + elementLocator + " has been changed or no longer attached to DOM", ex);

            }
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click", elementLocator);
        }
    }

    public void click(WebElement element) {
        try {
            new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementNotVisibleException e) {
            error("" + element + " " + " has not been clicked because is not visible on Page. Selenium is not able to perform click.", e);
        } catch (WebDriverException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click", element);
        }
    }


    public void setTextBoxValue(By elementLocator, String textToInput) {
        try {
            WebElement element = driver.findElement(elementLocator);
            element.clear();
            element.sendKeys(textToInput);
        } catch (ElementNotVisibleException e) {
            error(textToInput + " has not been entered in text box because element with locator " + elementLocator + " is not visible on page.", e);
        } catch (StaleElementReferenceException e) {
            try {
                WebElement element = driver.findElement(elementLocator);
                element.clear();
                element.sendKeys(textToInput);
            } catch (StaleElementReferenceException ex) {
                error("Element with locator " + elementLocator + " has been changed or no longer attached to DOM", ex);
            }
        } catch (InvalidElementStateException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + textToInput + "')", elementLocator);
        }
    }

    public void selectByValue(By elementLocator, String value) {
        Select comboBox = new Select(driver.findElement(elementLocator));
        comboBox.selectByValue(value);
    }

    public void selectByVisibleText(By elementLocator, String text) {
        Select comboBox = new Select(driver.findElement(elementLocator));
        comboBox.selectByVisibleText(text);
    }

    public void waitForBrowserToLoadCompletely() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(d ->
                    ((JavascriptExecutor) d).executeScript("return document.readyState;").toString().equalsIgnoreCase("complete"));
        } catch (Exception ie) {
            info(ie.getMessage());
        }
    }

    public void waitForElementVisibility(By elementLocator) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 60);

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
        } catch (TimeoutException e) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            } catch (TimeoutException e1) {
                info(String.format("Element with locator %s is not visible", elementLocator));
            }
        }
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public String generateRandomNumberOfFixLength(int digCount) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(digCount);
        for (int i = 0; i < digCount; i++)
            sb.append((char) ('1' + rnd.nextInt(8)));
        return sb.toString();
    }

    public Boolean isElementDisplayed(By elementLocator){
        try {
            return (!driver.findElements(elementLocator).isEmpty() && driver.findElement(elementLocator).isDisplayed());
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
