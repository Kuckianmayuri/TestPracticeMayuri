package seleniumLiveProject;

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

import io.github.bonigarcia.wdm.WebDriverManager;

public class standAlonetest {

	public static void main(String[] args) throws InterruptedException {

		String productName = "ZARA COAT 3";
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("kuckianmayuri@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Study14#");
		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = products.stream()
		    .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
		    .findFirst()
		    .orElse(null);

		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);

		WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//button[normalize-space()='Checkout']")));
		checkoutButton.click();

		Actions actions = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		WebElement countryInput =
		 driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
		 actions.moveToElement(countryInput).click().sendKeys("ind").build().perform();
		
		 Thread.sleep(2000);
		
		 List<WebElement> listCountry =
		 driver.findElements(By.xpath("//section[contains(@class,'ta-results')]/button"));
		 for (WebElement country : listCountry) {
		 if (country.getText().equalsIgnoreCase("India")) {
		 actions.moveToElement(country).click().build().perform();
		 break;
		 }
		 }

		// listCountry.stream()
		// .filter(country -> country.getText().equalsIgnoreCase("India"))
		// .findFirst()
		// .ifPresent(country ->
		// actions.moveToElement(country).click().build().perform()); // using stream

//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
//
//		driver.findElement(By.xpath("(//section[contains(@class,'ta-results')]/button")).click();
		driver.findElement(By.xpath("//a[@class='btnn action__submit ng-star-inserted']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.trim().equalsIgnoreCase("Thankyou for the order."));

        System.out.println("Executed standalone test");
		driver.close();

	}

}
