package testingwebdriver.tests;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testingwebdriver.TestComponents.BaseTest;
import TestingWebDriver.pagesobjects.CartPage;
import TestingWebDriver.pagesobjects.CheckoutPage;
import TestingWebDriver.pagesobjects.ConfirmationPage;
import TestingWebDriver.pagesobjects.LandingPage;
import TestingWebDriver.pagesobjects.OrderPage;
import TestingWebDriver.pagesobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {
	String email = "gialinh0167@gmail.com";
	String password = "01656107662aA";
	String item = "ADIDAS ORIGINAL";
//	String nameCountry = "india";

	@Test(dataProvider = "getData", groups = { "purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		LandingPage landing = launchApplication();

		// Login page
		ProductCatalogue productCatalogue = landing.loginApplication(input.get("email"), input.get("password"));

		// Add item into cart
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("item"));

		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[style*='z-index']")));

		// Go to cart Page
		CartPage cartPage = productCatalogue.goTocartPage();
		Boolean check = cartPage.VerifyProductDisplay(input.get("item"));
		Assert.assertTrue(check);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		// Checkout page
		Actions action = new Actions(driver);
		checkoutPage.chooseCountry(action, "india");
		ConfirmationPage confirmationPage = checkoutPage.goToOrderPage();

		// Confirm page
		Assert.assertTrue(confirmationPage.confirmCheckText().equalsIgnoreCase("Thankyou for the order."));
	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void OrderHistoryTest() {
		ProductCatalogue productCatalogue = landing.loginApplication(email, password);
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyProductDisplay(item));
	}
//	public String getScreenShot(String testCaseName) throws IOException {
//		TakesScreenshot ts = (TakesScreenshot)driver;
//		File source = ts.getScreenshotAs(OutputType.FILE);
//		File file = new File("D://Ojo-sama//"+testCaseName+".png");
//		FileUtils.copyFile(source, file);
//		return "D://Ojo-sama//"+testCaseName+".png";
//	}
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataMap(System.getProperty("user.dir")+"//src//test//java//TestingWebDriver//data//PurchaseOrder.json");
		return new Object[][] { {data.get(0)},{data.get(0)} };
	}

//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] { { "gialinh0167@gmail.com", "01656107662aA", "ADIDAS ORIGINAL" },
//				{ "gialinh212003@gmail.com", "01656107662aA", "ZARA COAT 3" } };
//	}

//	@DataProvider
//	public Object[][] getData() {
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email","gialinh0167@gmail.com");
//		map.put("password","01656107662aA");
//		map.put("item","ADIDAS ORIGINAL");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email","gialinh212003@gmail.com");
//		map1.put("password","01656107662aA");
//		map1.put("item","ZARA COAT 3");
//		
//		return new Object[][] { {map},{map1} };
//	}
	
}
