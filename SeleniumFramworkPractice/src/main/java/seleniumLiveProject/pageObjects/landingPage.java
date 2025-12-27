package seleniumLiveProject.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstactComponent;

public class landingPage extends abstactComponent{
	
	WebDriver driver;
	public landingPage(WebDriver driver) {
		super(driver); //to send varaible from child to parent here landing page is child & abstractcomponenet is parent
		//Initialization
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		productCatalog productCatalog = new productCatalog(driver);
		return productCatalog;
	}
	
	public String getErrorMessage() {
	    waitElementToApper(errorMessage);
	   return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
	}
	
}
