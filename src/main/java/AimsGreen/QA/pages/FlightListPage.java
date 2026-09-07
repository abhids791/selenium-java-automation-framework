package AimsGreen.QA.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FlightListPage extends BasePage{
    WebDriver driver;

    public FlightListPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "div[data-testid='flight-departure-list'] div div div div:nth-child(4)")
    List<WebElement> departureElements;

    @FindBy(css ="div[data-testid='flight-return-list'] div div div div:nth-child(4)")
    List<WebElement> returnElements;

    @FindBy(css ="button[data-testid='flight-continue-to-passengers']")
    WebElement continueButton;


    public void clickOnFirstDepartureFlightDisplayed(){
        for(WebElement ele: departureElements){
            ele.findElement(By.cssSelector("button")).click();
            break;
        }
    }

    public void clickOnFirstReturnFlightDisplayed(){
        for(WebElement ele: returnElements){
            WebElement selectButton= ele.findElement(By.cssSelector("button"));
            moveToElementAndClick(selectButton);
            break;
        }
    }

    public PassengersDetailsPage clickOnContinueToPassengerDetailsButton(){
        moveToElementAndClick(continueButton);
        return new PassengersDetailsPage(driver);
    }



}
