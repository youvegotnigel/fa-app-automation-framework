/**
 * @author  : youvegotnigel
 * created on 2021/01/31
 */
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SewingPlanUploadPage {

    private WebDriver driver;

    private By title = By.xpath("//ion-navbar[@class='toolbar toolbar-md toolbar-md-dark']//ion-title[@class='title title-md']");

    //Constructor
    public SewingPlanUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    //explicit wait
    public void explicitWaitMethod(By element) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public String getTitleText(){
        explicitWaitMethod(title);
        return driver.findElement(title).getText();
    }
}
