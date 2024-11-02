package FrameworkDesign.Tests;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FrameworkDesign.PageObejectModel.CartPage;
import FrameworkDesign.PageObejectModel.ProductCatalogue;
import FrameworkDesign.TestComponents.BaseTest;
import FrameworkDesign.TestComponents.Retry;
public class ErrorValidationTest extends BaseTest {
@Test(groups = {"ErrorHandling"} , retryAnalyzer = Retry.class)
	public void submitOrder() {
		
		landingPage.loginApplication("Shva@gmail.com", "Black@123");
//		landingPage.getErrorMessage();
Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}

@Test
	public void productErrorValidation() throws InterruptedException {
	
String productName = "ADIDAS ORIGINAL";
	
	ProductCatalogue productCatalogue = landingPage.loginApplication("Shiva@gmail.com", "Black@123");
		List<WebElement> products = productCatalogue.getprodutList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage	= productCatalogue.goTOCartPage();
		Boolean match =	cartPage.verifyProductDisplay("ADIDAS ORIGINAL2");
			Assert.assertFalse(match);
	
	
}

}
