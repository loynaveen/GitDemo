package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".form-group input")
	WebElement countryInput;

	@FindBy(xpath = "//*[@type='button'][2]")
	WebElement countrySelect;

	@FindBy(css = ".actions a")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) {
		countryInput.sendKeys(countryName);
		waitForElementToAppear(results);
		countrySelect.click();
	}

	public ConfirmationPage submitOrder() {
		submit.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}
}
