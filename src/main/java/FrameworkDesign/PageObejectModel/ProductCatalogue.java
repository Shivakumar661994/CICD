package FrameworkDesign.PageObejectModel;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import FrameworkDesign.AbstractComponent.AbstractComponent;
public class ProductCatalogue extends AbstractComponent{

	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		//initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	//Pagefactory
	@FindBy(css =".mb-3")
	List<WebElement> products;
	
	@FindBy(css =".ng-animating")
	WebElement spinner;
	
	// By is used without webelement 
	By produtsBy = By.cssSelector(".mb-3");
	By addTOCart = By.cssSelector(".card-body button:last-of-type");
	By toastMassage = By.cssSelector("#toast-container");
	
//	Action Methods for produts element
	
	public List<WebElement> getprodutList() {
		waitForElementAppear(produtsBy);
		return products;
		
	}

	public WebElement getProductByName(String productName ) {
		
		WebElement prod =	getprodutList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod= getProductByName(productName);
		prod.findElement(addTOCart).click();
		waitForElementAppear(toastMassage);
		waitForElememtToDiappear(spinner);
	}
}
