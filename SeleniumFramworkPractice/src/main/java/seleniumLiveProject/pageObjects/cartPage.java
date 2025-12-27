package seleniumLiveProject.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstactComponent;

public class cartPage extends abstactComponent {
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkOutEle;

	@FindBy(css = ".cartSection h3")
	List<WebElement> cardProducts;

	public Boolean verifyProductDisplay(String productname) {
		Boolean match = cardProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productname));
		return match;
	}

	public checkOutPage goToCheckOut() {
		checkOutEle.click();
		return new checkOutPage(driver);
	}

}
