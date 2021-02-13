/**
 * @author  : youvegotnigel
 * created on 2021/02/07
 */
package planupload;

import base.BaseTests;
import com.extentManager.ExtentManager;
import com.listener.ListenerClass;
import com.utility.Log;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SewingPlanUploadPage;

//@Listeners({ListenerClass.class}) /** comment this out when running from testNG.xml **/
public class SewingPlanUploadPageTests extends BaseTests {


    @Test(description = "Verify valid login")
    public void test_successful_login() {

        SoftAssert softAssert2 = new SoftAssert();

        Log.startTestCase("Verify valid login");
        Log.info("This is Verify valid login");

        Log.info("input username and password");
        loginPage.inputLoginCredentials("ba-bxm", "123");

        Log.info("click on login button");
        HomePage homePage = loginPage.clickLoginButton(); // click login button and pass the driver

        Log.info("verify landing page");
        ExtentManager.test.createNode("verify landing page");
        softAssert2.assertEquals(homePage.getHomePageHeaderName(), "Business Administrator App");

        softAssert2.assertAll();
        Log.endTestCase("Verify valid login");
    }


    @Test(dependsOnMethods = {"test_successful_login"}, description = "Verify sewing plan upload landing page")
    public void verify_sewing_plan_upload_landing_page() {

        SoftAssert softAssert = new SoftAssert();

        Log.startTestCase("Verify sewing plan upload landing page");
        Log.info("This is Verify sewing plan upload landing page");

        Log.info("click on sewing plan upload button");
        SewingPlanUploadPage sewingPlanUploadPage = loginPage.goToHomePage().goToSewingPlanUploadPage();

        Log.info("verify landing page");
        softAssert.assertEquals(sewingPlanUploadPage.getTitleText(), "Upload Sewing Plan1");

        softAssert.assertAll();
        Log.endTestCase("Verify sewing plan upload landing page");

    }
}
