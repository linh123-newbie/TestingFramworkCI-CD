package testingwebdriver.tests;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;


import TestingWebDriver.pagesobjects.CartPage;
import TestingWebDriver.pagesobjects.CheckoutPage;
import TestingWebDriver.pagesobjects.ConfirmationPage;
import TestingWebDriver.pagesobjects.LandingPage;
import TestingWebDriver.pagesobjects.ProductCatalogue;
import testingwebdriver.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest{
		@Test(groups= {"ErrorHandling"}, retryAnalyzer =testingwebdriver.TestComponents.Retry.class)
		public void LoginErrorValidation() throws IOException, InterruptedException {
			
			String email = "gialinh0167@gmail.com";
			String password = "01656107662a";			
			//Login page
			landing.loginApplication(email, password);
			Assert.assertEquals(landing.getErrorMessage().trim(), "Incorrect email or password.");
		}
		@Test
		public void ProductErrorValidation() throws IOException, InterruptedException {
			String email = "gialinh0167@gmail.com";
			String password = "01656107662aA";
			String item = "ADIDAS ORIGINAL";
			LandingPage landing = launchApplication();
			
			//Login page
			ProductCatalogue productCatalogue = landing.loginApplication(email, password);
			
			//Add item into cart
			List<WebElement> products = productCatalogue.getProductList();
			productCatalogue.addProductToCart(item);
			
			productCatalogue.waitForElementsToDisappear();
			//Go to cart Page
			CartPage cartPage = productCatalogue.goTocartPage();
			Boolean check = cartPage.VerifyProductDisplay("ADIDAS ORIGINALL");
			Assert.assertFalse(check);
		}
		
}
