package FrameworkDesign.PageObejectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import FrameworkDesign.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	
public 	CheckOutPage(WebDriver driver){
	super(driver);
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
}
@FindBy(css ="[placeholder='Select Country']")
WebElement country;


@FindBy(css =".action__submit")
WebElement submitButton;

@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
private WebElement selectCountry;

By results = By.cssSelector(".ta-results");
//Action Methods
public void selectCountry(String countryName) {
	Actions a = new Actions(driver);
	a.sendKeys(country, countryName).build().perform();
	waitForElementAppear(By.cssSelector(".ta-results"));
	selectCountry.click();
}
	public  ConfirmationPage  submitOrder() {
		submitButton.click();
		 return new ConfirmationPage(driver);
	}

}



