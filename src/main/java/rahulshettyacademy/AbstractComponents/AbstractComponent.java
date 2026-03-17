package rahulshettyacademy.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrdersPage;


public class AbstractComponent {
		
	WebDriver driver;

	public AbstractComponent (WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, driver);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement headerbutton;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement OrderHeader;
	
	
	public void WebElementToBeAppear(By findBy)
	{

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WebElementToAppear(WebElement findBy)
	{

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void WebElementToBeDisappear() throws InterruptedException
	{
		Thread.sleep(4000); 
	}
	
	
	public CartPage goToCart()
	{
		headerbutton.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}
	
	public OrdersPage goToOrderPage()
	{
		OrderHeader.click();
		OrdersPage orderpage = new OrdersPage(driver);
		return orderpage;
	}
	
}

