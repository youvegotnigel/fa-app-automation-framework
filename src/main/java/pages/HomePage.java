/**
 * @author : youvegotnigel
 * created on 2021/01/30
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomePage {

    private WebDriver driver;

    private By menuIcon = By.cssSelector("button[class='bar-buttons bar-buttons-md bar-button bar-button-md bar-button-default bar-button-default-md bar-button-menutoggle bar-button-menutoggle-md']");
    private By pageHeader = By.cssSelector("div[class='tool-block app-hdr'] div[class='toolbar-title toolbar-title-md']");
    private By nCingaLogo = By.cssSelector("img[class='tab-logo']");
    private By clock = By.cssSelector("button[class='tool-block hdr-time bar-button bar-button-md bar-button-default bar-button-default-md bar-button-small bar-button-small-md'] span[class='button-inner']");
    private By username = By.xpath("//button[@class='username-wrapper tool-block bar-button bar-button-md bar-button-default bar-button-default-md bar-button-small bar-button-small-md']");
    private By tiles = By.xpath("//ion-content[@class='content content-md']//ion-col");

    private By sideMenuLogo = By.cssSelector("img[class='menu-logo-resize']");
    private By sideMenuTitle = By.cssSelector("ion-toolbar[class='toolbar toolbar-md'] div[class='toolbar-title toolbar-title-md']");
    private By sideMenuLists = By.xpath("//ion-menu[@role='navigation']//button");

    private By homeButton = By.xpath("//button[1]//div[1]//div[1]");
    private By sideMenuFooter = By.cssSelector("ion-segment-button[role='button']");

    private By sewingPlanUploadButton = By.xpath("//span[normalize-space()='Sewing Plan Upload']");
    private By sizeListMappingButton = By.xpath("//span[normalize-space()='Size List Mapping']");
    private By silhouetteCreationButton = By.xpath("//span[normalize-space()='Silhouette Creation']");
    private By scheduleSettingsButton = By.xpath("//span[normalize-space()='Schedule Settings']");

    //Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void explicitWaitMethod(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public String getHomePageHeaderName() {
        explicitWaitMethod(pageHeader);
        return driver.findElement(pageHeader).getText();
    }

    public boolean nCingaLogoIsDisplayed() {
        explicitWaitMethod(nCingaLogo);
        return driver.findElement(nCingaLogo).isDisplayed();
    }

    public void clickMenuIcon() {
        explicitWaitMethod(menuIcon);
        driver.findElement(menuIcon).click();
    }

    public String getClockTime() {
        explicitWaitMethod(clock);
        return driver.findElement(clock).getText();
    }

    public String getUsername() {
        explicitWaitMethod(username);
        return driver.findElement(username).getText();
    }

    public boolean isLogoDisplayed() {
        explicitWaitMethod(sideMenuLogo);
        return driver.findElement(sideMenuLogo).isDisplayed();
    }

    public String getSideMenuTitle() {
        explicitWaitMethod(sideMenuTitle);
        return driver.findElement(sideMenuTitle).getText();
    }

    public String getSideMenuFooter() {
        explicitWaitMethod(sideMenuFooter);
        return driver.findElement(sideMenuFooter).getText();
    }

    public void clickSideMenuFooter() {
        explicitWaitMethod(sideMenuFooter);
        driver.findElement(sideMenuFooter).click();
    }

    public List<WebElement> getTilesList() {
        explicitWaitMethod(tiles);
        List<WebElement> allTileElements = driver.findElements(tiles);

        //use enhanced for loop to print list
//        for(WebElement list : allTileElements){
//            System.out.println(list.getText());
//        }

        //allTileElements.forEach(s -> System.out.println(s));

        return allTileElements;
    }

    public List<WebElement> getSideMenuList() {

        //must click menu icon to get list
        clickMenuIcon();

        explicitWaitMethod(sideMenuLists);
        List<WebElement> allListElements = driver.findElements(sideMenuLists);

        //use enhanced for loop to print list
//        for(WebElement list : allTileElements){
//            System.out.println(list.getText());
//        }
        return allListElements;
    }

    public String getCurrentTime() {

        //get the current time in this format
        //info : https://docs.oracle.com/javase/10/docs/api/java/text/SimpleDateFormat.html
        String nowTime = new SimpleDateFormat("h:mm a").format(new Date());

        return nowTime; //return in expected format
    }

    public List<String> convertWebElementToStringList(List <WebElement> list){
        List <String> stringList = new ArrayList<String>();

        for(WebElement a : list){
            stringList.add(a.getText().toLowerCase());
        }

        return stringList;
    }

    public void clickOnHomeButton(){
        explicitWaitMethod(homeButton);
        driver.findElement(homeButton).click();
    }

    public void printList(List <String> list){
        for(String a : list){
            System.out.println("list : " + a);
        }
    }

    //pass the driver for the Sewing Plan Upload Page
    public SewingPlanUploadPage goToSewingPlanUploadPage() {
        explicitWaitMethod(sewingPlanUploadButton);
        driver.findElement(sewingPlanUploadButton).click();
        return new SewingPlanUploadPage(driver);
    }

    //pass the driver for the Size List Mapping Page
    public SizeListMappingPage goToSizeListMappingPage() {
        explicitWaitMethod(sizeListMappingButton);
        driver.findElement(sizeListMappingButton).click();
        return new SizeListMappingPage(driver);
    }
}
