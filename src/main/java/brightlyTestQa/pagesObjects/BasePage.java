package brightlyTestQa.pagesObjects;

import brightlyTestQa.AbstractComponents.AbstractComponent;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class BasePage extends AbstractComponent {
    SoftAssert softAssert= new SoftAssert();
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    public BasePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectOptionFromDropdown(WebDriver driver, WebElement dropdownSelector, String optionText) {
        Select dropDown = new Select(dropdownSelector);
        dropDown.selectByValue(optionText);
    }

    public void enterValueUsingCtrlA(WebElement inputElement, String value) {
        actions.moveToElement(inputElement).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(value).perform();

    }

    public void moveToElementAndClick(WebElement element) {
        WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
        Actions actions = new Actions(driver);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(element).click().perform();
    }

    public void scrollUpAndClick(WebElement element, int yaxis) {
        waitForElementToAppearAndScrollTo(element, yaxis);
        clickElement(element);
    }

    public void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
