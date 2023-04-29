package pages;

import com.google.common.io.Files;
import contansts.messages.AssertionMessages;
import contansts.urls.Links;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BasePageTest {

    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/main/java/drivers/chromedriver.exe";
    protected WebDriver driver;
    protected BasePage basePage;

    @BeforeClass
    public void setUp() {
        System.setProperty(WEBDRIVER, DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        moveToSignInPage();
    }

    @BeforeMethod
    public void moveToSignInPage() {
        driver.get(Links.SIGN_IN_URL);
        basePage = new BasePage(driver);
    }
/*
    @Test
    public void testFailedLogin() {
        String errorMessageDisplayStatus = basePage.wrongCredentialsDisplayStatus();
        Assert.assertEquals(errorMessageDisplayStatus, AssertionMessages.LOGIN_ERROR_MESSAGE_EXPECTED_STATUS);
    }*/

    @Test
    public void testContinueButton() {
        this.basePage.clickContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), Links.SIGN_IN_URL);
    }

  /*  @Test
    public void testGoogleButton() {
        basePage.signInWithGoogle();
        Assert.assertEquals(driver.getCurrentUrl(), Links.GOOGLE_URL);
    }

    @Test
    public void testFacebookButton() {
        basePage.signInWithFB();
        Assert.assertEquals(driver.getCurrentUrl(), Links.FB_URL);
    }

    @Test
    public void testAppleButton() {
        basePage.signInWithApple();
        Assert.assertEquals(driver.getCurrentUrl(), Links.APPLE_URL);
    }*/

    @AfterMethod
    public void recordFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try {
                Files.move(screenshot, new File("src/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
