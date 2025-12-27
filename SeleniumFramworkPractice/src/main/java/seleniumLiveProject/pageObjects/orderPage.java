package seleniumLiveProject.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstactComponent;


public class orderPage extends abstactComponent {
	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	@FindBy(css = "tbody tr td:nth-child(3)")
	List<WebElement> productNames;

	public Boolean verifyOrderDisplay(String productname) {
		Boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productname));
		return match;
	}


}
