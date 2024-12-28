package TestingWebDriver.pagesobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestingWebDriver.pagesobjects.abtractcomponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents{
	WebDriver driver;
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//List<WebElement> list = driver.findElements(By.cssSelector(".mb-3"));

	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector("button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	By disappearElement = By.cssSelector("div[style*='z-index']");

	public List<WebElement> getProductList() {
		waitForElementsToAppear(productsBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return prod;
	}
	public void addProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementsToAppear(toastMessage);
		waitForElementsToDisappear();
	
	
	
	}
}
