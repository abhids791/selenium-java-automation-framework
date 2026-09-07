package aimsGreen.qa.test;

import AimsGreen.QA.pages.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import java.time.Duration;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BookFlightTwoWay {
    WebDriver driver;
    SoftAssert softAssert= new SoftAssert();


    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.qapractice.com/flight-booking-scenarios");
    }
    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void Method1() throws InterruptedException {

        //Enter Search Details
        LandingPage landingPage= new LandingPage(driver);
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