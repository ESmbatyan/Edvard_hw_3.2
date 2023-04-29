package pages;

import contansts.locators.BasePageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    private WebDriver webDriver;

    private By continueButton = By.id(BasePageLocators.CONTINUE_BUTTON_ID);
    private By signInButton = By.id(BasePageLocators.SIGN_IN_BUTTON_ID);
    private By wrongCredentialsErrorMessage = By.id(BasePageLocators.WRONG_CREDENTIALS_ERROR_MESSAGE_ID);

    private By signInWithGoogleButton = By.id(BasePageLocators.SIGN_IN_WITH_GOOGLE_BUTTON_ID);
    private By signInWithFacebookButton = By.id(BasePageLocators.SIGN_IN_WITH_FB_BUTTON_ID);
    private By signInWithAppleButton = By.id(BasePageLocators.SIGN_IN_WITH_APPLE_BUTTON_ID);

    private By email = By.id(BasePageLocators.EMAIL);
    private By passwordByDriver = By.id(BasePageLocators.PASSWORD);

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void setUsernameField(String username) {
        webDriver.findElement(email).sendKeys(username);
    }

    public void setPasswordField(String password) {
        webDriver.findElement(passwordByDriver).sendKeys(password);
    }

    public BasePage clickContinueButton() {
        WebElement button = new WebDriverWait(webDriver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
        return new BasePage(webDriver);
    }

    public GooglePage signInWithGoogle() {
        WebElement button = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(signInWithGoogleButton));
        return new GooglePage(webDriver);
    }

    public FacebookPage signInWithFB() {
        WebElement button = new WebDriverWait(webDriver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(signInWithFacebookButton));
        return new FacebookPage(webDriver);
    }

    public ApplePage signInWithApple() {
        WebElement button = new WebDriverWait(webDriver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(signInWithAppleButton));
        return new ApplePage(webDriver);
    }

    public BasePage clickSignInButton() {
        WebElement button = new WebDriverWait(webDriver, Duration.ofSeconds(100))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
        button.click();
        return new BasePage(webDriver);
    }

    public String wrongCredentialsDisplayStatus() {
        setUsernameField(BasePageLocators.WRONG_LOGIN);
        setPasswordField(BasePageLocators.WRONG_PASSWORD);
        clickSignInButton();
        return webDriver.findElement(wrongCredentialsErrorMessage).getCssValue("display");
    }


}
