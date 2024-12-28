package TestingWebDriver.pagesobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TestingWebDriver.pagesobjects.abtractcomponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

	
	WebDriver driver;
	
	@FindBy(css="h1.hero-primary")
	WebElement confirmText;
	
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String confirmCheckText() {
		return confirmText.getText();
		
	}
	
	
}
