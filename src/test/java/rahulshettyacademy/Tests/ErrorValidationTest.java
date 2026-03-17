package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

		@Test (groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
		public void LoginPageError() throws IOException, InterruptedException {
		
		landingpage.LoginApplication("syha@gmail.com", "Syhaaaaa26@");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
		}
		
		@Test
		public void VerifyProductDisplayError() throws InterruptedException
		{
			String val = "ZARA COAT 3";
			ProductCatalogue productCatalogue = landingpage.LoginApplication("syha@gmail.com", "Syhafatim26@");
			
			List<WebElement> ProdList = productCatalogue.getProductList();
			productCatalogue.addToCart(val);
			CartPage cartpage = productCatalogue.goToCart();
			
	   		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
			Assert.assertFalse(match);

		}

}
