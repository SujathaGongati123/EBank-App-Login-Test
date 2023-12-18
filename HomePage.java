package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By headingLocator = By.className("heading");
    By appLogoImgLocator = By.className("ebank-logo");
    By digitalCardImgLocator = By.className("digital-card-image");
    By logoutBtnLocator = By.className("logout-button");

    public HomePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getHeadingText(){
        return driver.findElement(headingLocator).getText();
    }

    public WebElement findAppLogoImg(){
        return driver.findElement(appLogoImgLocator);
    }

    public WebElement findDigitalCardImg(){
        return driver.findElement(digitalCardImgLocator);
    }

    public void clickOnLogoutBtn(){
        driver.findElement(logoutBtnLocator).click();
    }
}
















