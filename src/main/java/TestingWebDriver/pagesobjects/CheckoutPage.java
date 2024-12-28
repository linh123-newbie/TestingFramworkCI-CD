package TestingWebDriver.pagesobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestingWebDriver.pagesobjects.abtractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	//Actions action = new Actions(driver);
//  action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
////  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='result']")));
//  driver.findElement(By.xpath("//section[contains(@class,'result')]/button[2]")).click();
//  driver.findElement(By.cssSelector(".action__submit")).click();
	WebDriver driver;
	
	@FindBy(css="input[placeholder='Select Country']")
	private WebElement selectCountryList;
	
	@FindBy(xpath="//section[contains(@class,'result')]/button[2]")
	private WebElement country;
	
	@FindBy(css=".action__submit")
	private WebElement orderButton;
	
	By waitCountry = By.cssSelector("section[class*='result']");
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void chooseCountry(Actions action, String nameCountry) {
		action.sendKeys(selectCountryList, nameCountry).build().perform();
		waitForElementsToAppear(waitCountry);
		country.click();
	}
	public ConfirmationPage goToOrderPage() {
		orderButton.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
}
