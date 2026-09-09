package AimsGreen.QA.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends BasePage {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css= "select[data-testid='flight-from']")
    WebElement fromDropDown;

    @FindBy(css= "select[data-testid='flight-to']")
    WebElement toDropDown;

    @FindBy(css ="input[data-testid='flight-departure-date']")
    WebElement departureDateElement;

    @FindBy(css = "input[data-testid='flight-return-date']")
    WebElement returnDateElement;

    @FindBy(id ="flight-passengers")
    WebElement passengerFiledElement;

    @FindBy(id ="flight-search")
    WebElement flightSearchButton;

    @FindBy(id="flight-one-way")
    WebElement oneWayEle;

    @FindBy(css ="div[class='invalid-feedback']")
    List<WebElement> toFieldErrorMessage;

    @FindBy(css ="div[class='invalid-feedback']")
    List<WebElement> fromFieldErrorMessage;



    public void selectValueInFromDropDown(String from){
        selectOptionFromDropdown(driver, fromDropDown, from);
    }

    public void selectValueInToDropDown(String to){
        selectOptionFromDropdown(driver, toDropDown, to);
    }

    public void selectValueFromDepartureDate(String date){
        departureDateElement.sendKeys("09-09-2026");
    }

    public void selectValueFromReturnDate(String date){
        returnDateElement.sendKeys("16-09-2026");
    }

    public void enterValueInPassengersInputField(String numberOfPassengers){
        enterValueUsingCtrlA(passengerFiledElement, numberOfPassengers);
    }

    public FlightListPage clickOnSearchFlightButton(){
        moveToElementAndClick(flightSearchButton);
        return new FlightListPage(driver);
    }

    public void clickOnOneWay(){
        oneWayEle.click();
    }
    public void goToLandingPage(){
        driver.get("https://www.qapractice.com/flight-booking-scenarios");
    }

    public String getToFieldErrorMessage(){
        return toFieldErrorMessage.get(1).getText();
    }

    public String getFromFieldErrorMessage(){
        System.out.println(fromFieldErrorMessage.get(0).getText());
        return fromFieldErrorMessage.get(0).getText();
    }

}
