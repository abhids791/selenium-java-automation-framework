package brightlyQa.pagesObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmBookingPage extends BasePage {
    WebDriver driver;
    public ConfirmBookingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id ="flight-booking-success")
    WebElement bookingMessage;

    public String verifyConfirmBooking(){
        return bookingMessage.getText();
    }
}
