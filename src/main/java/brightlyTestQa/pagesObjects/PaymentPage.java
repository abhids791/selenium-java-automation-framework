package brightlyTestQa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PaymentPage extends BasePage {
    WebDriver driver;


    public PaymentPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2")
    List<WebElement> testElement;

    @FindBy(id ="flight-card-number")
    WebElement cardNumberField;

    @FindBy(id="flight-expiry")
    WebElement cardExp;

    @FindBy(id ="flight-cvv")
    WebElement cvvElement;

    @FindBy(id ="flight-book")
    WebElement payButton;

    public String verifyPaymentPage() {
        boolean paymentPageDisplayed = false;
        for (WebElement text : testElement) {
            if (text.getText().equalsIgnoreCase("Payment")) {
                paymentPageDisplayed = true;
                return text.getText();
            }
        }
        return "";
    }

    public void enterValueInCardNumberField(String cardNumber){
        cardNumberField.sendKeys(cardNumber);

    }

    public void enterValueInExpiryField(String exp){
        cardExp.sendKeys(exp);
    }

    public void enterValueInCvvField(String cvv){
        cvvElement.sendKeys(cvv);
    }

    public ConfirmBookingPage clickOnPayAndConfirmBooking(){
        payButton.click();
        return new ConfirmBookingPage(driver);
    }
}
