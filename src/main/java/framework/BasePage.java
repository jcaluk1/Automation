package framework;

import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

    protected ChromeDriver driver;
    protected Wrapper baseObj;

    public BasePage(ChromeDriver driver) {
        this.driver = driver;
        baseObj = new Wrapper(driver);
    }
}
