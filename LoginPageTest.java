import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.time.Duration;

public class LoginPageTest {

    public WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\sujat\\Downloads\\chromedriver-win32\\chromedriver-win32\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.get("https://qaebank.ccbp.tech/ebank/login");

        loginPage = new LoginPage(driver);
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1)
    public void loginPageUiTest(){
        WebElement loginImgEl = loginPage.findLoginImg();
        Assert.assertTrue(loginImgEl.isDisplayed(), "Login Image is not displaying...");

        String userIdLabelText = loginPage.getLabelText(0);
        String expUserId = "User ID";

        Assert.assertEquals(userIdLabelText, expUserId, "User ID label text is not matching..");

        String pinLabelText = loginPage.getLabelText(1);
        String expPin = "PIN";

        Assert.assertEquals(pinLabelText, expPin, "PIN label text is not matching..");

        String loginHeading = loginPage.getLoginHeading();
        String expLoginHeading = "Welcome Back!";

        Assert.assertEquals(loginHeading, expLoginHeading, "Login heading is not matching..");
    }

    @Test(priority = 2)
    public void loginWithEmptyInputs(){
        loginPage.clickOnLoginBtn();

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "Invalid user ID";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with empty input fields is not matching..");
    }

    @Test(priority = 3)
    public void loginWithEmptyUserId(){
        loginPage.enterPin("231225");
        loginPage.clickOnLoginBtn();

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "Invalid user ID";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with empty user id input field is not matching..");
    }

    @Test(priority = 4)
    public void loinWithEmptyPin(){
        loginPage.enterUserId("142420");
        loginPage.clickOnLoginBtn();

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "Invalid PIN";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with empty pin input field is not matching..");
    }

    @Test(priority = 5)
    public void loginWithInvalidPin(){
        loginPage.loginProcess("142420", "123456");

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "User ID and PIN didn't match";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with invalid PIN is not matching..");
    }

    @Test(priority = 6)
    public void loginWithInvalidUserId(){
        loginPage.loginProcess("123456", "231225");

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "Invalid user ID";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with invalid user ID is not matching..");
    }

    @Test(priority = 7)
    public void loginWithInvalidCreds(){
        loginPage.loginProcess("123456", "234456");

        String errMsg = loginPage.getErrMsg();
        String expErrMsg = "Invalid user ID";

        Assert.assertEquals(errMsg, expErrMsg, "Error message with both invalid user ID and PIN is not matching..");
    }

    @Test(priority = 8)
    public void loginWithValidCreds(){
        loginPage.loginProcess("142420", "231225");

        String expectedUrl = "https://qaebank.ccbp.tech/";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expectedUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "URLs are not matching..");
    }

}
























