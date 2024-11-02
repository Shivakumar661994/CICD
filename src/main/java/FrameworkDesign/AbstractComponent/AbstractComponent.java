package FrameworkDesign.AbstractComponent;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FrameworkDesign.PageObejectModel.CartPage;
import FrameworkDesign.PageObejectModel.CheckOutPage;
import FrameworkDesign.PageObejectModel.OrderPage;

public class AbstractComponent {

	//This CLass uses for all REUSABLITY of code for child classes	
		WebDriver driver;
		public AbstractComponent(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(css ="[routerlink*='cart']")
		WebElement cartHeader;
		
		
		@FindBy(css = "[routerlink*='myorders']")
		WebElement orderHeader;
		
		public  CartPage goTOCartPage() {
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
			cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		}

		public OrderPage goToOrdersPage()
		{
			orderHeader.click();
			OrderPage orderPage = new OrderPage(driver);
			return orderPage;
		}
		public  void waitForElementAppear(By findBy) {
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}
			public  void waitForWebElementAppear(WebElement findBy) {
			
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(findBy));
			}
		
		public void waitForElememtToDiappear(WebElement ele) throws InterruptedException
{
			Thread.sleep(1000);
//			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
//			wait.until(ExpectedConditions.invisibilityOf(ele));
			
		}
}