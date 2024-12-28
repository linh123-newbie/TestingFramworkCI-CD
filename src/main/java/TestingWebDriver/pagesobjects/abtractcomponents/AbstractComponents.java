package TestingWebDriver.pagesobjects.abtractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TestingWebDriver.pagesobjects.CartPage;
import TestingWebDriver.pagesobjects.OrderPage;
import TestingWebDriver.pagesobjects.ProductCatalogue;

public class AbstractComponents {
	WebDriver driver; 
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
	}
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public void waitForElementsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement el) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(el));
	}
	public CartPage goTocartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		return new OrderPage(driver);
	}
	public void waitForElementsToDisappear() throws InterruptedException {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(1000);
	}
}
