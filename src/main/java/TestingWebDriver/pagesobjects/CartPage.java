package TestingWebDriver.pagesobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestingWebDriver.pagesobjects.abtractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	


	public Boolean VerifyProductDisplay(String productName) {
		Boolean check = productTitles.stream().anyMatch(product->product.getText().trim().equalsIgnoreCase(productName));
		productTitles.stream().forEach(product -> System.out.println(product.getText()));

		return check;
	}
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
