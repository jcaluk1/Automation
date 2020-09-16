package framework;

import com.aventstack.extentreports.ExtentTest;
import framework.reporter.ExtentManager;
import framework.reporter.ExtentTestManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    private String location = System.getProperty("user.dir");
    protected ChromeDriver driver;
    protected Wrapper baseObj;
    protected ExtentTest test = ExtentTestManager.getTest();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        ExtentManager.init();
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"testType"})
    public void beforeClass(@Optional("UI") String testType) {

        System.setProperty("webdriver.chrome.driver", location + "\\drivers\\chromedriver.exe");
        if (testType.equalsIgnoreCase("UI")) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("http://automationpractice.com/index.php");
            baseObj = new Wrapper(driver);
        } else baseObj = new Wrapper();
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method method) {
        Test testMethod = method.getAnnotation(Test.class);
        String testName = testMethod.testName().equals("") ? method.getName() : testMethod.testName();
        test = ExtentTestManager.startTest(testName);

    }

    @AfterClass(alwaysRun = true)
    @Parameters("testType")
    public void afterClass(@Optional("UI") String testType) {
        if (testType.equalsIgnoreCase("UI"))
            driver.close();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentManager.getReporter().flush();
    }
}
