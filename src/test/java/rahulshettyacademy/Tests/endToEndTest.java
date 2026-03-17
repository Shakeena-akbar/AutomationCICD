package rahulshettyacademy.Tests;

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

import rahulshettyacademy.pageobjects.LandingPage;

public class endToEndTest {

	public static void main(String[] args) throws InterruptedException {
		
		String val = "ZARA COAT 3";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.findElement(By.cssSelector("input[class*=form-control]")).sendKeys("syha@gmail.com");
		driver.findElement(By.cssSelector("input[placeholder='enter your passsword']")).sendKeys("Syhafatim26@");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));
		List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));
		WebElement result = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(val)).findFirst().orElse(null);
		result.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		//wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(val));
		Assert.assertTrue(match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("section[class*='ta-results']")));
		driver.findElement(By.xpath("(//section[contains(@class,'ta-results')]/button)[2]")).click();
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).click().build().perform();
		//driver.findElement(By.cssSelector("div[class='actions'] a")).click();
		String ConfirmMessage = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(ConfirmMessage, "THANKYOU FOR THE ORDER.");
		
		
		
	}

}
