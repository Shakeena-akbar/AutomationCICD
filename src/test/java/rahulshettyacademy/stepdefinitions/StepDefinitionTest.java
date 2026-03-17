package rahulshettyacademy.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckOutPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionTest extends BaseTest {
	
	public LandingPage landingpage;
	public ProductCatalogue productCatalogue;
	public CheckOutPage checkoutpage;
	
	@Given("I  landed on e-commerce page")
	public void Landing_Page() throws IOException
	{
		landingpage = LaunchingApplication();
	}
	
	@Given("^Logged in with valid username (.+) and password (.+)$")
	public void Logging_In(String username, String Password)
	{
		productCatalogue = landingpage.LoginApplication(username, Password);
	}
	
	@When("^I add the product (.+) to the cart$")
	public void Product_Addition(String ProductName) throws InterruptedException
	{
		List<WebElement> ProdList = productCatalogue.getProductList();
		productCatalogue.addToCart(ProductName);
	}
	
	@When("^checkout (.+) and submit the order$")
	public void Submit_Order(String ProductName)
	{
        CartPage cartpage = productCatalogue.goToCart();
		Boolean match = cartpage.VerifyProductDisplay(ProductName);
   		Assert.assertTrue(match);
		checkoutpage = cartpage.goToCheckout();
		checkoutpage.SelectCountry("India");
		String expectedresult = checkoutpage.ConfirmationPage();
	}
	
	@Then("{string} message is displayed.")
	public void Confirmation_Message(String message)
	{
		String expectedresult = checkoutpage.ConfirmationPage();
		Assert.assertEquals(expectedresult, message);
		driver.close();
	}

	@Then("{string} error message is displayed")
	public void Login_Error_Message(String error)
	{
		Assert.assertEquals(error, landingpage.getErrorMessage());
		driver.close();
	}
	
}
