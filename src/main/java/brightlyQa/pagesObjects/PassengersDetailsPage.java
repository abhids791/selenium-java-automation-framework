package brightlyQa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PassengersDetailsPage extends BasePage{
    WebDriver driver;


    public PassengersDetailsPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css ="h2")
    List<WebElement> passengerDetailsPage;

    @FindBy(id ="flight-passenger-name")
    WebElement fullNameElement;

    @FindBy(id="flight-passenger-email")
    WebElement emailIdelEment;

    @FindBy(id="flight-passenger-phone")
    WebElement phoneElement;

    @FindBy(css ="button[data-testid='flight-continue-to-payment']")
    WebElement continueButton;


    public String verifyPassengerDetailsPage(){
        boolean passengerDetailsDisplayed = false;
        for(WebElement textElement: passengerDetailsPage ){
            if(textElement.getText().equalsIgnoreCase("Passenger details")){
                passengerDetailsDisplayed=true;
                return textElement.getText();
            }
        }
        return "";
    }

    public void enterValueInFullNameField(String fullName){
        fullNameElement.sendKeys("fullName");
    }

    public void enterValueInEmailIdField(String email){
        emailIdelEment.sendKeys(email);
    }

    public void enterValueInPhoneField(String phone){
        phoneElement.sendKeys(phone);
    }

    public PaymentPage clickOnContinueToPaymentButton(){
        scrollUpAndClick(continueButton, -200);
        return new PaymentPage(driver);
    }
}
