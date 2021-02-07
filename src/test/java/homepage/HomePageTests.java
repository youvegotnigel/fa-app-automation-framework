/**
 * @author  : youvegotnigel
 * created on 2021/01/30
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
import org.testng.asserts.SoftAssert;
import pages.HomePage;

import java.util.List;

//@Listeners({ListenerClass.class}) /** comment this out when running from testNG.xml **/
public class HomePageTests extends BaseTests {

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

    @Test(dependsOnMethods = {"test_successful_login"}, description = "Verify homepage header")
    public void verify_homepage_header(){

        Log.startTestCase("Verify homepage header");
        Log.info("This is Verify homepage header");

        HomePage homepage = loginPage.goToHomePage();

        Log.info("verify nCinga Logo is displayed on the header");
        ExtentManager.test.createNode("nCinga Logo is displayed on the header");
        Assert.assertTrue(homepage.nCingaLogoIsDisplayed(),"nCinga Logo is displayed on the header");

        Log.info("Verify the clock is showing current time");
        ExtentManager.test.createNode("Verify the clock is showing current time");
        Assert.assertEquals(homepage.getClockTime(),homepage.getCurrentTime(),"Clock times are not matching");

        Log.info("Verify the username is displayed on top right hand side");
        ExtentManager.test.createNode("Verify the username is displayed on top right hand side");
        Assert.assertEquals(homepage.getUsername().toLowerCase(),"test-user","username is not valid");

        Log.endTestCase("Verify homepage header");

    }


    @Test(dependsOnMethods = {"test_successful_login"}, description = "Verify homepage tiles content")
    public void verify_contents_of_side_menu_with_tile_icons(){

        Log.startTestCase("Verify homepage tiles content");
        Log.info("Verify homepage tiles content");

        HomePage homePage = loginPage.goToHomePage();

        List<WebElement> tileList = homePage.getTilesList();
        List<WebElement> sideMenuList = homePage.getSideMenuList();

        //convert both lists to String
        List<String> tileListString = homePage.convertWebElementToStringList(tileList);
        List<String> sideMenuListString = homePage.convertWebElementToStringList(sideMenuList);

        Log.info("tile menu list : ");
        for(String list : tileListString){
            Log.info("contents : " + list);
        }

        Log.info("side menu list : ");
        for(String list : sideMenuListString){
            Log.info("contents : " + list);
        }

        //remove matching elements
        tileListString.removeAll(sideMenuListString);

        SoftAssert softAssert = new SoftAssert();

        ExtentManager.test.createNode("items is side menu should match with items in tiles");
        softAssert.assertEquals(tileListString.size(),0,"check log file to find missing items form the side menu list."); //soft assertion

        if(tileListString.size()>0){
            Log.warn("Missing items from the Side Menu List " + tileListString);
        }

        Log.info("Click on home button to close side menu");
        homePage.clickOnHomeButton();

        Log.info("verify landing page");
        ExtentManager.test.createNode("verify landing page");
        softAssert.assertEquals(homePage.getHomePageHeaderName(), "Business Administrator App");

        softAssert.assertAll();
        Log.endTestCase("Verify homepage tiles content");

    }

    @Test(dependsOnMethods = {"test_successful_login"}, description = "Verify sign out")
    public void verify_sign_out(){

        SoftAssert softAssert1 = new SoftAssert();

        Log.startTestCase("Verify sign out");

        HomePage homePage = loginPage.goToHomePage();

        Log.info("Click on menu icon to open");
        homePage.clickMenuIcon();

        Log.info("verify sign out text");
        ExtentManager.test.createNode("verify sign out text");
        softAssert1.assertEquals(homePage.getSideMenuFooter(),"SIGN OUT");

        Log.info("Click on sign out button");
        homePage.clickSideMenuFooter();

        Log.info("verify sign in page banner on the page header");
        ExtentManager.test.createNode("verify sign in page banner on the page header");
        softAssert1.assertEquals(loginPage.getBannerText(), "nCinga is now Zilingo Factory");

        Log.info("verify sign in text on the sign in page");
        ExtentManager.test.createNode("verify sign in text on the sign in page");
        softAssert1.assertEquals(loginPage.getLoginHeader(), "Sign in");

        softAssert1.assertAll();
        Log.endTestCase("Verify sign out");

    }






}
