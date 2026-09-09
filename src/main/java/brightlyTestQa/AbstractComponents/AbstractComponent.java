package brightlyTestQa.AbstractComponents;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponent {
    protected WebDriver driver;
    Actions actions;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public void waitForElementToAppearAndScrollTo(WebElement element, int yaxis) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions.scrollToElement(element)
                .scrollByAmount(0, yaxis)
                .perform();

        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}
