/**
 * @author  : youvegotnigel
 * created on 2021/01/30
 */
package login;

import base.BaseTests;
import com.extentManager.ExtentManager;
import com.listener.ListenerClass;
import com.utility.Log;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

//@Listeners({ListenerClass.class}) /** comment this out when running from testNG.xml **/
public class LoginPageTests extends BaseTests {

    @Test(priority = 3, description = "Verify valid login")
    public void test_successful_login() {

        Log.startTestCase("Verify valid login");
        Log.info("This is Verify valid login");

        Log.info("input username and password");
        loginPage.inputLoginCredentials("ba-bxm", "123");

        Log.info("click on login button");
        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Log.info("verify landing page");
        ExtentManager.test.createNode("verify landing page");
        Assert.assertEquals(homePage.getHomePageHeaderName(), "Business Administrator App");

        Log.endTestCase("Verify valid login");
    }

    @Test(priority = 2, description = "Verify invalid login")
    public void test_unsuccessful_login() {

        Log.startTestCase("Verify invalid login");
        Log.info("This is Verify invalid login");

        Log.info("input invalid username and password");
        loginPage.inputLoginCredentials("123", "ba-bxm");

        Log.info("click on login button");
        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Log.info("verify error message");
        ExtentManager.test.createNode("verify error message");
        Assert.assertEquals(loginPage.getErrorMessageForWrongCredentials(), "Invalid username or password");
        Log.endTestCase("Verify invalid login");

    }

    @Test(priority = 1, description = "Verify invalid login when login credentials are empty")
    public void test_login_for_empty_credential() {

        Log.startTestCase("Verify invalid login when login credentials are empty");
        Log.info("This is Verify invalid login when login credentials are empty");

        Log.info("input empty username and password");
        loginPage.inputLoginCredentials("", "");

        Log.info("click on login button");
        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Log.info("verify error message for username");
        ExtentManager.test.createNode("Validate error msg. displayed for empty username");
        Assert.assertEquals(loginPage.getErrorMessageForEmptyUN(), "Enter username");

        Log.info("verify error message for password");
        ExtentManager.test.createNode("Validate error msg. displayed for empty password");
        Assert.assertEquals(loginPage.getErrorMessageForEmptyPW(), "Enter password");

        Log.endTestCase("Verify invalid login when login credentials are empty");

    }

    @Test(priority = 1, description = "verify login screen interface")
    public void verify_landing_page() {

        Log.startTestCase("verify login screen interface");
        Log.info("This is verify login screen interface");

        Log.info("verify sign in page banner on the page header");
        ExtentManager.test.createNode("verify sign in page banner on the page header");
        Assert.assertEquals(loginPage.getBannerText(), "nCinga is now Zilingo Factory");

        Log.info("verify sign in text on the sign in page");
        ExtentManager.test.createNode("verify sign in text on the sign in page");
        Assert.assertEquals(loginPage.getLoginHeader(), "Sign in");

        Log.endTestCase("verify login screen interface");

    }

}
