package brightlyTestQa.test;

import brightlyTestQa.testComponents.BaseTest;
import brightlyTestQa.testComponents.RetryWhenTestFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ErrorValidationTest extends BaseTest {

    @Test(groups = "Regression", retryAnalyzer = RetryWhenTestFailed.class)
    public void testErrorValidationForToField() {
        // Add your test logic here
        landingPage.selectValueInFromDropDown("Tokyo");
        landingPage.selectValueFromDepartureDate("09-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        landingPage.clickOnSearchFlightButton();
        Assert.assertEquals(landingPage.getToFieldErrorMessage(), "Please select a destination city.");
    }

    @Test(groups = {"Regression", "Smoke"}, retryAnalyzer = RetryWhenTestFailed.class)
    public void testErrorValidationForFromField() {
        // Add your test logic here
        landingPage.selectValueInToDropDown("Tokyo");
        landingPage.selectValueFromDepartureDate("14-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        landingPage.clickOnSearchFlightButton();
        Assert.assertEquals(landingPage.getFromFieldErrorMessage(), "Please select a departure city.");
    }

    @Test(groups = {"Regression","Smoke"}, retryAnalyzer = RetryWhenTestFailed.class)
    public void testErrorValidationForDepartureDateField() {
        // Add your test logic here
        landingPage.selectValueInFromDropDown("Tokyo");
        landingPage.selectValueInToDropDown("Delhi");

        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        landingPage.clickOnSearchFlightButton();
        Assert.assertEquals(landingPage.getDepartureDateFieldErrorMessage(), "Please select a departure date.");

    }
}