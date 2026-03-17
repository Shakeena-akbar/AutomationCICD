package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;


public class ProductCatalogue extends AbstractComponent {

	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css = "div.col-lg-4")
	List<WebElement> products;
	
	By productload = By.cssSelector("div.col-lg-4");
	By actualprod = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	public List<WebElement> getProductList()
	{
		WebElementToBeAppear(productload);
		return products;
	}
	
	public WebElement getProductName(String val)
	{
		WebElement item = getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(val)).findFirst().orElse(null);
		return item;
	}	
	
	public void addToCart(String val) throws InterruptedException
	{
		WebElement item = getProductName(val);
		item.findElement(actualprod).click();
		WebElementToBeAppear(toastMessage);
		WebElementToBeDisappear();
	}

}
