package testingwebdriver.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import TestingWebDriver.pagesobjects.CartPage;
import TestingWebDriver.pagesobjects.CheckoutPage;
import TestingWebDriver.pagesobjects.ConfirmationPage;
import TestingWebDriver.pagesobjects.LandingPage;
import TestingWebDriver.pagesobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testingwebdriver.TestComponents.BaseTest;

public class StepDefinitionImplement extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CartPage cartPage;
	public ConfirmationPage confirmationPage;
	public List<WebElement> products;
	public CheckoutPage checkoutPage;
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	@Given("^Logged in with email (.+) and password (.+)$")
	public void logged_in_with_email_and_password(String email, String password) {
		productCatalogue = landing.loginApplication(email, password);
	}
    @When("^I add product (.+) to cart$")
    public void i_add_product_to_cart(String item) throws InterruptedException {
    	products = productCatalogue.getProductList();
    	productCatalogue.addProductToCart(item);
    	productCatalogue.waitForElementsToDisappear();
    }
    @When("^Checkout (.+) and submit the order$")
    public void checkout_and_submit_the_order(String item){
    	cartPage = productCatalogue.goTocartPage();

		Boolean match = cartPage.VerifyProductDisplay(item);
		Assert.assertTrue(match);
		checkoutPage = cartPage.goToCheckout();
		Actions action = new Actions(driver);
		checkoutPage.chooseCountry(action,"india");
		confirmationPage = checkoutPage.goToOrderPage();
    }
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string) {
    	String confirmMessage = confirmationPage.confirmCheckText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
    }
    @Then("^\"([^\"]*)\" message is displayed")
    public void message_is_displayed(String string) {
		Assert.assertEquals(landing.getErrorMessage().trim(), "Incorrect email or password.");
		driver.close();
    }
}
