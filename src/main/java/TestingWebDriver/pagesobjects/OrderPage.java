package TestingWebDriver.pagesobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestingWebDriver.pagesobjects.abtractcomponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	WebDriver driver;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	@FindBy(css="tr td:nth-of-type(2)")
	List<WebElement> productTitles;
	
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean check = productTitles.stream().anyMatch(product->product.getText().trim().equalsIgnoreCase(productName));
		return check;
	}
	
}
