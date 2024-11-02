package FrameworkDesign.PageObejectModel;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import FrameworkDesign.AbstractComponent.AbstractComponent;
public class CartPage extends AbstractComponent{

	WebDriver driver;
	public CartPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	//Pagefactory
	@FindBy(css =".totalRow button")
	WebElement checkOutEle;
	
	@FindBy(css =".cartSection h3")
	List<WebElement> cartProducts;
	
	
//	Action Methods for produts element
	
	public Boolean verifyProductDisplay(String productName ) {
		Boolean match = 	cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckOutPage goTOcheckOut() {
		checkOutEle.click();
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
		
	}
}