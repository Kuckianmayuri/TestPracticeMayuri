package AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumLiveProject.pageObjects.cartPage;
import seleniumLiveProject.pageObjects.orderPage;

public class abstactComponent {
	
	WebDriver driver;  // local instant
	public abstactComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;  //giving life using constructor 
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;
	
	public void  waitElementToApper(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void  waitElementToApper(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitElementToDisapper(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(ele));	
	}
	
	public cartPage goToCartPage() {
		cartHeader.click(); // Add to the cart
		return new cartPage(driver);
	}
	
	public orderPage goToOrderPage() {
		orderHeader.click(); // Add to the cart
		return new orderPage(driver);
	}
}
