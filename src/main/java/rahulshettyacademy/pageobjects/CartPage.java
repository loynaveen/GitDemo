package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = ".cartSection h3")
	List<WebElement> items;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public Boolean verifyProductDisplay(String productName) {
		Boolean match = items.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}

	public CheckoutPage goTocheckout() {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;

	}
}
