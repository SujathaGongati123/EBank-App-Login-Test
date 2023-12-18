package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    By loginImgLocator = By.className("login-ebank-image");
    By labeltextsLocator = By.xpath("//label[@class='input-label']");
    By loginHeadingLocator = By.className("login-heading");
    By userIdLocator = By.id("userIdInput");
    By pinLocator = By.id("pinInput");
    By loginBtnLocator = By.className("login-button");
    By errMsgLocator = By.className("error-message-text");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement findLoginImg(){
        return driver.findElement(loginImgLocator);
    }

    public String getLoginHeading(){
        WebElement loginHeadingEl = driver.findElement(loginHeadingLocator);
        return loginHeadingEl.getText();
    }

    public String getLabelText(int index){
        List<WebElement> labelTexts = driver.findElements(labeltextsLocator);

        return labelTexts.get(index).getText();
    }

    public void enterUserId(String userId){
        driver.findElement(userIdLocator).sendKeys(userId);
    }

    public void enterPin(String pin){
        driver.findElement(pinLocator).sendKeys(pin);
    }

    public void clickOnLoginBtn(){
        driver.findElement(loginBtnLocator).click();
    }

    public void loginProcess(String userId,String pin){
        enterUserId(userId);
        enterPin(pin);
        clickOnLoginBtn();
    }

    public String getErrMsg(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(errMsgLocator));
        WebElement errMsgEl = driver.findElement(errMsgLocator);

        return errMsgEl.getText();
    }
}





















