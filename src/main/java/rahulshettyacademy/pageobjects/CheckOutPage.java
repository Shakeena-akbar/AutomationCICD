package rahulshettyacademy.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
			
	WebDriver driver;

	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement Country;
	
	@FindBy(xpath="(//section[contains(@class,'ta-results')]/button)[2]")
	WebElement option;
	
	@FindBy(css=".action__submit")
	WebElement placeorderbutton;
	
	@FindBy(className="hero-primary")
	WebElement message;

	
	By button = By.cssSelector("section[class*='ta-results']");

	
	public void SelectCountry(String countryname)
	{
		Actions a = new Actions(driver);
		a.sendKeys(Country, countryname).build().perform();
		WebElementToBeAppear(button);
		option.click();
		a.moveToElement(placeorderbutton).click().build().perform();
	}
	
	public String ConfirmationPage()
	{
		return message.getText();
	}
}
