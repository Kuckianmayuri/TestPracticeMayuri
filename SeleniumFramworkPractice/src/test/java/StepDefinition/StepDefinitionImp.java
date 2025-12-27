package StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumLiveProject.pageObjects.cartPage;
import seleniumLiveProject.pageObjects.checkOutPage;
import seleniumLiveProject.pageObjects.confirmationPage;
import seleniumLiveProject.pageObjects.landingPage;
import seleniumLiveProject.pageObjects.productCatalog;

public class StepDefinitionImp extends BaseTest {
	public landingPage LandingPage;
	public productCatalog productCatalog;
	public confirmationPage confirmationPage;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		LandingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String name, String password) {
		productCatalog = LandingPage.loginApplication(name, password);
	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productname) {
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productname);
	}

	@And("^I add product (.+) and submit the order$")
	public void I_add_product_and_submit_the_order(String productname) {
		cartPage cartPage = productCatalog.goToCartPage(); // since we are using child class inherits the parent class
		Boolean match = cartPage.verifyProductDisplay(productname);
		Assert.assertTrue(match);
		checkOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		confirmationPage = checkOutPage.submitOrder();
	}

	@Then("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_ConfirmationPage(String string) {
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.trim().equalsIgnoreCase(string));
		driver.close();
	}

	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());
		driver.close();
	}
}
