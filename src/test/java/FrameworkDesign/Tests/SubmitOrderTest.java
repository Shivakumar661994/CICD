package FrameworkDesign.Tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import FrameworkDesign.PageObejectModel.CartPage;
import FrameworkDesign.PageObejectModel.CheckOutPage;
import FrameworkDesign.PageObejectModel.ConfirmationPage;
import FrameworkDesign.PageObejectModel.OrderPage;
import FrameworkDesign.PageObejectModel.ProductCatalogue;
import FrameworkDesign.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";
	@Test(dataProvider= "getData" , groups = {"Purchase"})
//	public void submitOrder(String email,String password, String productName ) throws InterruptedException, IOException { 
	public void submitOrder(HashMap<String,String> input)  throws InterruptedException, IOException { 
	
	ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getprodutList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage	= productCatalogue.goTOCartPage();
		Boolean match =	cartPage.verifyProductDisplay(input.get("product"));
			Assert.assertTrue(match);
			CheckOutPage checkOutPage =	cartPage.goTOcheckOut();
			checkOutPage.selectCountry("India");
			ConfirmationPage conformationPage =	checkOutPage.submitOrder();
			String confirmMessage =conformationPage.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		
	List<HashMap<String,String>>data =getJsonDataToMap("C:\\Users\\Sankeerthana M\\OneDrive\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\FrameworkDesign\\Data\\PurchaseOrder.json");
		return new Object[][] {  {data.get(0)} ,{data.get(1)} };
		
	}
	

	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("Shiva@gmail.com", "Black@123");
		OrderPage orderPage = landingPage.goToOrdersPage();
	Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
		
	}

}
	
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "Shiva@gmail.com");
//	map.put("password", "Black@123");
//	map.put("product", "ADIDAS ORIGINAL");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "chitti@gmail.com");
//	map1.put("password", "Black@123");
//	map1.put("product", "QWERTY");
//	return new Object[][] {  {map} ,{map1} };
//	
//	data provider by creating Object Multi dimential array
//	return new  Object[][] { {"Shiva@gmail.com", "Black@123", "ADIDAS ORIGINAL"},{"chitti@gmail.com", "Black@123" , "Qwerty"}  };

	
	

