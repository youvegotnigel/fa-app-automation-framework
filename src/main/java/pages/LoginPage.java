/**
 * @author  : youvegotnigel
 * created on 2021/01/30
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    private WebDriver driver;
    //fields
    private By usernameTxtBox = By.cssSelector("input[placeholder='Username']");
    private By passwordTxtBox = By.cssSelector("input[placeholder='Password']");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By emptyUsername = By.xpath("//p[normalize-space()='Enter username']");
    private By emptyPassword = By.xpath("//p[normalize-space()='Enter password']");
    private By invalidCredentials = By.xpath("//p[@class='error-msg-invalid']");

    private By newPageBanner = By.xpath("//div[contains(text(),'nCinga is now Zilingo Factory')]");
    private By signInText = By.cssSelector("div[class='login-header']");
    private By zFactoryLogo = By.cssSelector("img[class='login-z-logo']");


    //constructor
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //set the username
    public void setUsername(String username) {

        explicitWaitForUsernameTextBox();
        driver.findElement(usernameTxtBox).clear();
        driver.findElement(usernameTxtBox).sendKeys(username);
    }

    //set the password
    public void setPassword(String password) {
        explicitWaitForPasswordTextBox();
        driver.findElement(passwordTxtBox).clear();
        driver.findElement(passwordTxtBox).sendKeys(password);
    }

    //error message for username filed empty
    public String getErrorMessageForEmptyUN(){
        explicitWaitForEmptyUsername();
        return driver.findElement(emptyUsername).getText();
    }

    //error message for password filed empty
    public String getErrorMessageForEmptyPW(){
        explicitWaitForEmptyPassword();
        return driver.findElement(emptyPassword).getText();
    }

    //error message for incorrect credentials
    public String getErrorMessageForWrongCredentials(){
        explicitWaitForInvalidCredentials();
        return driver.findElement(invalidCredentials).getText();
    }

    //click on the login button
    public HomePage clickLoginButton(){

        //mouse move action
        //Actions action = new Actions(driver);
        //action.moveToElement(driver.findElement(loginButton)).build().perform();

        driver.findElement(loginButton).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);

        return new HomePage(driver);
    }

    public void explicitWaitForEmptyUsername(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyUsername));
    }

    public void explicitWaitForEmptyPassword(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emptyPassword));
    }

    public void explicitWaitForInvalidCredentials(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(invalidCredentials));
    }

    public void explicitWaitForUsernameTextBox(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameTxtBox));
    }

    public void explicitWaitForPasswordTextBox(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordTxtBox));
    }

    public void explicitWaitForPageBanner(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(newPageBanner));
    }

    public void explicitWaitForLoginHeader(){
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInText));
    }

    public String getBannerText(){
        explicitWaitForPageBanner();
        return driver.findElement(newPageBanner).getText();
    }

    public String getLoginHeader(){
        explicitWaitForLoginHeader();
        return driver.findElement(signInText).getText();
    }

    public void inputLoginCredentials(String un, String pw) {
        setUsername(un);
        setPassword(pw);
    }

    //pass the driver for the next page
    public HomePage goToHomePage() {
        return new HomePage(driver);
    }
}
