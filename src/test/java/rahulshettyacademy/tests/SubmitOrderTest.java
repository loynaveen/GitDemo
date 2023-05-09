package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String prodName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {
		// TODO Auto-generated method stub

		/* Landing PAge */

		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		// Product Page Add to Cart

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("prodN"));

		CartPage cartPage = productCatalogue.goToCartPage(); // Cart Header

		// Cart Page

		Boolean match = cartPage.verifyProductDisplay(input.get("prodN"));
		Assert.assertTrue(match);

		CheckoutPage checkoutPage = cartPage.goTocheckout();

		// CheckoutPage Operations

		checkoutPage.selectCountry("ind");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();

		String confirmMessage = confirmationPage.verifyConfirmationMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("naveen909loy@gmail.com", "NAVeen909@");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(prodName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "/src/test/java/rahulshettyacademy/data/PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "naveen909loy@gmail.com");
//	map.put("password", "NAVeen909@");
//	map.put("prodN", "ADIDAS ORIGINAL");
//
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map.put("email", "shetty@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("prodN", "ZARA COAT 3");

}
