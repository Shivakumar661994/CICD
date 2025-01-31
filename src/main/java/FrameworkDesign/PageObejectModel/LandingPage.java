package FrameworkDesign.PageObejectModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import FrameworkDesign.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	WebElement userEmail =	driver.findElement(By.id("userEmail"));
	//Pagefactory
	@FindBy(id ="userEmail")
	WebElement userEmail;
	@FindBy(id ="userPassword")
	WebElement passwordEle;
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
//Action Methods for above pagefactory Elements	
	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		
		ProductCatalogue productCatalogue =  new ProductCatalogue(driver);
			return	productCatalogue;
	}
	
	
	public void goTo(){
		
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage() {
		waitForWebElementAppear(errorMessage);
		return errorMessage.getText();
		
		
	}
	
	
}
