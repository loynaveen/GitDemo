package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = { "ErrorHandling" }, retryAnalyzer = Retry.class)
	public void loginErrorValidationTest() throws IOException {
		// TODO Auto-generated method stub

		landingPage.loginApplication("naveen9099loy@gmail.com", "NAVeen9091@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}

	@Test
	public void productErrorValidationTest() throws IOException {
		// TODO Auto-generated method stub

		String prodName = "ADIDAS ORIGINAL";

		/* Landing PAge */

		LandingPage landingPage = launchApplication();
		ProductCatalogue productCatalogue = landingPage.loginApplication("naveen909loy@gmail.com", "NAVeen909@");

		// Product Page Add to Cart

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(prodName);

		CartPage cartPage = productCatalogue.goToCartPage(); // Cart Header

		// Cart Page

		Boolean match = cartPage.verifyProductDisplay(prodName + "1");
		Assert.assertFalse(match);

	}

}
