package TryPakage;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class TheTest {
	WebDriver driver = new ChromeDriver();

	String webSite = "https://magento.softwaretestingboard.com/women/tops-women/jackets-women.html";
	Random rand = new Random();

	@BeforeTest
	public void myBeforeTest() {
		driver.manage().window().maximize();

	}

	@Test(invocationCount = 5)
	public void myFirstTest() throws InterruptedException {

		driver.get(webSite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		WebElement ItemsContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));

		List<WebElement> AllItems = ItemsContainer.findElements(By.tagName("li"));
		AllItems.get(0).click();
		Thread.sleep(1000);

		// assert to check the size
		WebElement SizesContainer = driver
				.findElement(By.xpath("//div[@class='swatch-attribute size']//div[@role='listbox']"));

		List<WebElement> AllSizes = SizesContainer.findElements(By.tagName("div"));
		int randomSize = rand.nextInt(AllSizes.size());
		AllSizes.get(randomSize).click();
		String ExpectedSize = AllSizes.get(randomSize).getText();

		String ActualSize = driver.findElement(By.cssSelector(".swatch-attribute-selected-option")).getText();
		Assert.assertEquals(ActualSize, ExpectedSize);

		// assert to check the color
		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> allColors = ColorContainer.findElements(By.tagName("div"));
		int RandomColor = rand.nextInt(allColors.size());

		allColors.get(RandomColor).click();

		
		// method one to check the color -- good 
		
		String ExpectedColor = allColors.get(RandomColor).getAttribute("option-label");
		String ActualColor = driver
				.findElement(By.cssSelector(
						"div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']"))
				.getText();
		
		Assert.assertEquals(ActualColor, ExpectedColor);

		
		
		
		
		
		
		
		// method 2 to check the color - not good enough 
//		if (RandomColor == 0) {
//			String ExpectedColor = "Black";
//			String ActualColor = driver
//					.findElement(By.cssSelector(
//							"div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']"))
//					.getText();
//			Assert.assertEquals(ActualColor, ExpectedColor);
//		} else if (RandomColor == 1) {
//			String ExpectedColor = "Blue";
//			String ActualColor = driver
//					.findElement(By.cssSelector(
//							"div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']"))
//					.getText();
//			Assert.assertEquals(ActualColor, ExpectedColor);
//
//		} else if (RandomColor == 2) {
//			String ExpectedColor = "Orange";
//			String ActualColor = driver
//					.findElement(By.cssSelector(
//							"div[class='swatch-attribute color'] span[class='swatch-attribute-selected-option']"))
//					.getText();
//			Assert.assertEquals(ActualColor, ExpectedColor);
//
//		}

		WebElement AddtoCartButton = driver.findElement(By.id("product-addtocart-button"));
		AddtoCartButton.click();
		WebElement Msg = driver.findElement(By.xpath("//div[@data-ui-id='message-success']"));
		String ActualMsg = Msg.getText();
		Assert.assertEquals(ActualMsg.contains("added"), true);

	}

	@AfterTest
	public void myAfterTest() {
	}

}



