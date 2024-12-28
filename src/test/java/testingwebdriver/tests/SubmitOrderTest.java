package testingwebdriver.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testingwebdriver.TestComponents.BaseTest;
import TestingWebDriver.pagesobjects.CartPage;
import TestingWebDriver.pagesobjects.CheckoutPage;
import TestingWebDriver.pagesobjects.ConfirmationPage;
import TestingWebDriver.pagesobjects.LandingPage;
import TestingWebDriver.pagesobjects.OrderPage;
import TestingWebDriver.pagesobjects.ProductCatalogue;




public class SubmitOrderTest extends BaseTest{
	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{

		
		ProductCatalogue productCatalogue = landing.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("item"));
		productCatalogue.waitForElementsToDisappear();
		CartPage cartPage = productCatalogue.goTocartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("item"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		Actions action = new Actions(driver);
		checkoutPage.chooseCountry(action,"india");
		ConfirmationPage confirmationPage = checkoutPage.goToOrderPage();
		String confirmMessage = confirmationPage.confirmCheckText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		//"ZARA COAT 3";
		ProductCatalogue productCatalogue = landing.loginApplication("gialinh0167@gmail.com", "01656107662aA");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyProductDisplay(productName));
		
}
	

	
	//Extent Reports - 
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{

		
		List<HashMap<String,String>> data = getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//TestingWebDriver//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	
	
	
	
//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//	    
//	  }
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");
		  
}
