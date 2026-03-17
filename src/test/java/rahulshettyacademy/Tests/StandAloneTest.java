package rahulshettyacademy.Tests;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrdersPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest{
		
	String val = "ZARA COAT 3";

		@Test(dataProvider ="getData", groups ={"purchase"})
		public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		//cicd testing
//cicd testing 2
		ProductCatalogue productCatalogue = landingpage.LoginApplication(input.get("email"), input.get("password"));
		
		List<WebElement> ProdList = productCatalogue.getProductList();
		productCatalogue.addToCart(input.get("product"));
		CartPage cartpage = productCatalogue.goToCart();
		
   		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
   		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckout();
		
		checkoutpage.SelectCountry("India");
		String expectedresult = checkoutpage.ConfirmationPage();
		Assert.assertEquals(expectedresult, "THANKYOU FOR THE ORDER.");	
	}

		@Test(dependsOnMethods= {"submitOrder"})                                                                     
		public void OrderVerify()
		{
			ProductCatalogue productCatalogue = landingpage.LoginApplication("syha@gmail.com", "Syhafatim26@");
			OrdersPage orderpage = productCatalogue.goToOrderPage();
			Assert.assertTrue(orderpage.VerifyProductName(val));
		}
			
			
		@DataProvider
		public Object[][]  getData() throws IOException
		{
			List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\DataReader.json");
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
	
}
		
