/**
 * @author  : youvegotnigel
 * created on 2021/01/31
 */
package homepage;

import base.BaseTests;
import com.extentManager.ExtentManager;
import com.listener.ListenerClass;
import com.utility.Log;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;

@Listeners({ListenerClass.class}) /** comment this out when running from testNG.xml **/
public class SideMenuTests extends BaseTests {

    @Test(priority = 1, description = "Verify valid login")
    public void test_successful_login() {

        Log.startTestCase("Verify valid login");
        Log.info("This is Verify valid login");

        Log.info("input username and password");
        loginPage.inputLoginCredentials("ba-bxm", "123");

        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Log.info("verify landing page");
        ExtentManager.test.createNode("verify landing page");
        Assert.assertEquals(homePage.getHomePageHeaderName(), "Business Administrator App");

        Log.endTestCase("Verify valid login");
    }


    @Test(dependsOnMethods = {"test_successful_login"}, description = "Verify the contents of the side menu")
    public void test_contents_of_side_menu(){

        HomePage homepage = loginPage.goToHomePage();

        Log.info("Click on Side Menu Icon");
        homepage.clickMenuIcon();

        //Click on Home
        homepage.getTilesList().indexOf(0);

    }

    //test the links of the side menu


}
