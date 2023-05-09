package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> items;

	@FindBy(css = ".totalRow button")
	WebElement checkoutButton;

	public Boolean verifyOrderDisplay(String productName) {
		Boolean match = items.stream().anyMatch(item -> item.getText().equalsIgnoreCase(productName));
		return match;
	}


}
