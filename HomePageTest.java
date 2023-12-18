import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class HomePageTest {

    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.loginProcess("142420", "231225");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe("https://qaebank.ccbp.tech/"));
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1)
    public void HomePageUiTest(){

        WebElement logoImgEl = homePage.findAppLogoImg();

        Assert.assertTrue(logoImgEl.isDisplayed(), "Ebank App logo is not displaying..");

        String headingText = homePage.getHeadingText();
        String expHeadingText = "Your Flexibility, Our Excellence";

        Assert.assertEquals(headingText, expHeadingText, "Heading text does not match.");

        WebElement digitalCardImgEl = homePage.findDigitalCardImg();

        Assert.assertTrue(digitalCardImgEl.isDisplayed(), "Digital card image is not displayed.");
    }

    @Test(priority = 2)
    public void LogoutFunctionalityTest(){
        homePage.clickOnLogoutBtn();

        String expectedUrl = "https://qaebank.ccbp.tech/ebank/login";

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URLs are not matching.");
    }
}






















