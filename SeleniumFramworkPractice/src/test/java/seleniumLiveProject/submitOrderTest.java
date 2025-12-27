package seleniumLiveProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;
import seleniumLiveProject.pageObjects.cartPage;
import seleniumLiveProject.pageObjects.checkOutPage;
import seleniumLiveProject.pageObjects.confirmationPage;
import seleniumLiveProject.pageObjects.orderPage;
import seleniumLiveProject.pageObjects.productCatalog;

public class submitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException {

		// TODO Auto-generated method stub

		productCatalog productCatalog = LandingPage.loginApplication(input.get("email"), input.get("password"));
		productCatalog.addProductToCart(input.get("product"));
		cartPage cartPage = productCatalog.goToCartPage(); // since we are using child class inherits the parent class
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkOutpage = cartPage.goToCheckOut();
		checkOutpage.selectCountry("India");
		confirmationPage confirmationPage = checkOutpage.submitOrder();

		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.trim().equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		productCatalog productCatalog = LandingPage.loginApplication("kuckianmayuri@gmail.com", "Study14#");
		orderPage orderPage = productCatalog.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonToMap("./src\\\\test\\\\java\\\\Data\\\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "kuckianmayuri@gmail.com");
//		map1.put("password", "Study14#");
//		map1.put("product", "ZARA COAT 3");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("email", "kuckian@gmail.com");
//		map2.put("password", "IamKing@000");
//		map2.put("product", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map1},{map2}};
//
//	}
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"kuckianmayuri@gmail.com", "Study14#", "ZARA COAT 3" },{"kuckian@gmail.com","IamKing@000","ADIDAS ORIGINAL"}};
//
//	}

}
