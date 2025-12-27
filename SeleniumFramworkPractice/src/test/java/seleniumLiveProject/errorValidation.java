package seleniumLiveProject;

import java.io.IOException;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import seleniumLiveProject.pageObjects.cartPage;
import seleniumLiveProject.pageObjects.productCatalog;

public class errorValidation extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
	
		LandingPage.loginApplication("kuckianmayuri@gmail.com", "Studdy14#");
		Assert.assertEquals("Incorrect email or password.", LandingPage.getErrorMessage());
	}

	@Test
	public void productErrorValidation() {
		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub

		productCatalog productCatalog = LandingPage.loginApplication("kuckianmayuri@gmail.com", "Study14#");
		productCatalog.addProductToCart(productName);
		cartPage cartPage = productCatalog.goToCartPage(); // since we are using child class inherits the parent class
		cartPage.verifyProductDisplay("ZARA COAT 33");
	}
}
