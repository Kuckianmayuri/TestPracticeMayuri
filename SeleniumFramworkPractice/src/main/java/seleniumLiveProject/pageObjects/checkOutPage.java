package seleniumLiveProject.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.abstactComponent;

public class checkOutPage extends abstactComponent {

	WebDriver driver;

	public checkOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='btnn action__submit ng-star-inserted']")
	WebElement submit;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "//section[contains(@class,'ta-results')]/button")
	WebElement selectCountry;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		Actions actions = new Actions(driver);
		actions.moveToElement(country).click().sendKeys(countryName).build().perform();
		waitElementToApper(results);
		selectCountry.click();
	}
	// WebElement countryInput =
	// driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
	// actions.moveToElement(countryInput).click().sendKeys("ind").build().perform();
	//
	// Thread.sleep(2000);
	//
	// List<WebElement> listCountry =
	// driver.findElements(By.xpath("//section[contains(@class,'ta-results')]/button"));
	// for (WebElement country : listCountry) {
	// if (country.getText().equalsIgnoreCase("India")) {
	// actions.moveToElement(country).click().build().perform();
	// break;
	// }
	// }

	// listCountry.stream()
	// .filter(country -> country.getText().equalsIgnoreCase("India"))
	// .findFirst()
	// .ifPresent(country ->
	// actions.moveToElement(country).click().build().perform()); // using stream

	public confirmationPage submitOrder() {
		submit.click();
		return new confirmationPage(driver);
	}
}
