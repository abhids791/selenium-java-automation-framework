package brightlyTestQa.test;

import brightlyTestQa.pagesObjects.*;
import brightlyTestQa.testComponents.BaseTest;
import brightlyTestQa.testComponents.RetryWhenTestFailed;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class BookFlightTest extends BaseTest {

    @DataProvider
    public Object[][] sourceDestinationData() throws IOException {
        List<HashMap<String, String>> data = getJsonDatgaToMap("src/test/java/brightlyTestQa/data/toFromAddress.json");
        return new Object[][] {{data.get(0)}, {data.get(1)}, {data.get(2)}};
    }


    @Test(dataProvider = "sourceDestinationData", retryAnalyzer = RetryWhenTestFailed.class, groups={"Regression"})
    public void bookFlightOneWay(HashMap<String, String> data) throws InterruptedException, IOException {

        //Enter Search Details
        landingPage.selectValueInFromDropDown(data.get("from"));
        landingPage.selectValueInToDropDown(data.get("to"));
        landingPage.selectValueFromDepartureDate("09-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        FlightListPage flightListPage=landingPage.clickOnSearchFlightButton();

        //Select Flight from Departure and Return
        flightListPage.clickOnFirstDepartureFlightDisplayed();
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

    @Test(retryAnalyzer = RetryWhenTestFailed.class, groups={"Regression","Smoke"})
    public void bookFlightTwoWay() throws InterruptedException, IOException {

        //Enter Search Details
        landingPage.selectValueInFromDropDown("Tokyo");
        landingPage.selectValueInToDropDown("Mumbai");
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