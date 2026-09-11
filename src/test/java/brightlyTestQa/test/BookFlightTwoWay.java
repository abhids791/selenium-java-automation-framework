package brightlyTestQa.test;

import brightlyTestQa.pagesObjects.ConfirmBookingPage;
import brightlyTestQa.pagesObjects.FlightListPage;
import brightlyTestQa.pagesObjects.PassengersDetailsPage;
import brightlyTestQa.pagesObjects.PaymentPage;
import brightlyTestQa.testComponents.BaseTest;
import brightlyTestQa.testComponents.RetryWhenTestFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class BookFlightTwoWay extends BaseTest {
    @Test(retryAnalyzer = RetryWhenTestFailed.class, groups={"Regression"})
    public void bookFlightTwoWay() throws InterruptedException, IOException {

        //Enter Search Details
        landingPage.selectValueInFromDropDown("Paris");
        landingPage.selectValueInToDropDown("Delhi");
        landingPage.selectValueFromDepartureDate("09-09-2026");
        landingPage.selectValueFromReturnDate("16-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        FlightListPage flightListPage=landingPage.clickOnSearchFlightButton();

        //Select Flight from Departure and Return
        flightListPage.clickOnFirstDepartureFlightDisplayed();
        flightListPage.clickOnFirstReturnFlightDisplayed();
        PassengersDetailsPage passengersDetailsPage = flightListPage.clickOnContinueToPassengerDetailsButton();

        //Passenger details Page Verify Passenger Details is displayed
        Assert.assertEquals(passengersDetailsPage.verifyPassengerDetailsPage(),"Passenger details");
        passengersDetailsPage.verifyPassengerDetailsPage();
        passengersDetailsPage.enterValueInFullNameField("Abhijit Das");
        passengersDetailsPage.enterValueInEmailIdField("abhids788@gmail.com");
        passengersDetailsPage.enterValueInPhoneField("+918971399758");
        PaymentPage paymentPage= passengersDetailsPage.clickOnContinueToPaymentButton();

        //Payment Page

        Assert.assertEquals(paymentPage.verifyPaymentPage(),"Payment");
        paymentPage.enterValueInCardNumberField("1234567890123456");
        paymentPage.enterValueInExpiryField("03/36");
        paymentPage.enterValueInCvvField("123");
        ConfirmBookingPage confirmBookingPage= paymentPage.clickOnPayAndConfirmBooking();

        //Book Confirmed Page
        Assert.assertEquals(confirmBookingPage.verifyConfirmBooking(),"Booking Confirmed!");

    }
}
