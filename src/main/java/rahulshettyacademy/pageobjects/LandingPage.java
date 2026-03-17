package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory

	@FindBy(css = "input[class*=form-control]")
	WebElement email;
	
	
	@FindBy(css = "input[placeholder='enter your passsword']")
	WebElement password;

	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement submit;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;

	public String getErrorMessage()
	{
		WebElementToAppear(errorMessage);
		String error = errorMessage.getText();
		return error;
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}

	
	public ProductCatalogue LoginApplication(String useremail, String pass)
	{
		email.sendKeys(useremail);
		password.sendKeys(pass);
		submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
	}
	
	
	
}

