package seleniumLiveProject.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponent.abstactComponent;

public class productCatalog extends abstactComponent{

	WebDriver driver;
	public productCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css=".col-lg-4")
	List<WebElement> products;
	// Locator for all product elements. 
	// We use 'By' here instead of 'WebElement' because our wait method (waitElementToApper)
	// accepts a 'By' type — not a WebElement. This allows us to wait for the product list 
	// to appear before fetching the actual WebElements.
	By productsBy =  By.cssSelector(".col-lg-4"); 
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitElementToApper(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click(); //scrope of prod is within product hence we should initialise with by
		waitElementToApper(toastMessage);
		waitElementToDisapper(spinner);
		
	}
	
}
