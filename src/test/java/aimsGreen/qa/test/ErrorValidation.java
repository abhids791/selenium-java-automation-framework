package aimsGreen.qa.test;


import org.testng.Assert;
import org.testng.annotations.Test;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ErrorValidation extends BaseTest {

    @Test(retryAnalyzer = RetryWhenTestFailed.class)
    public void testErrorValidationForToField() {
        // Add your test logic here
        landingPage.selectValueInFromDropDown("Tokyo");
        landingPage.selectValueFromDepartureDate("09-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        landingPage.clickOnSearchFlightButton();
        Assert.assertEquals(landingPage.getToFieldErrorMessage(), "Please select a destination city");

    }

    @Test(groups = "smoke", retryAnalyzer = RetryWhenTestFailed.class)
    public void testErrorValidationForFromField() {
        // Add your test logic here
        landingPage.selectValueInToDropDown("Tokyo");
        landingPage.selectValueFromDepartureDate("09-09-2026");
        landingPage.enterValueInPassengersInputField("2");
        landingPage.clickOnOneWay();
        landingPage.clickOnSearchFlightButton();
        Assert.assertEquals(landingPage.getFromFieldErrorMessage(), "Please select a departure city.");

    }


}