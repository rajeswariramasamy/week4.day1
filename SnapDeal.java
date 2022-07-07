package week4.day1;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {
	public static void main(String[] args) throws InterruptedException, IOException {
		// Launch URL
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// Go to Mens Fashion
		driver.findElement(By.xpath("(//span[@class='catText'])[1]")).click();
		// Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		// Get the count of the sports shoes
		WebElement findele1 = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		String text = findele1.getText();
		System.out.println(text);
		Thread.sleep(9000);
		// Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		// Sort by Low to High
		Thread.sleep(9000);
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		// Check if the items displayed are sorted correctly
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		/*
		 * List<WebElement> findElement1 =
		 * driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		 * 
		 * for (WebElement webEle1 : findElement1) {
		 * System.out.println(webEle1.getAttribute("display-price")); }
		 */
		// Select the price range (900-1200)
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();

		// Filter with color Navy
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='View More ']")).click();
		WebElement find3 = driver.findElement(By.xpath("//input[@value='Navy']"));
		String att1 = find3.getAttribute("disabled");
		if (att1.equals("true")) {
			driver.findElement(By.xpath("//input[@value='Yellow']/following-sibling::label[1]")).click();
		} else {
			driver.findElement(By.xpath("//input[@value='Navy']/following-sibling::label[1]")).click();
		}
		// verify the all applied filters
		String price = driver.findElement(By.xpath("(//div[@class='filters']//div)[1]")).getText();
		String color = driver.findElement(By.xpath("(//a[@data-key='Color_s|Color'])[1]/parent::div")).getText();
		System.out.println(price + " " + color);
		if (price.equals(null) || color.equals(null)) {
			System.out.println("All filters are not applied");
		}
		// Mouse Hover on first resulting Training shoes
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("(//picture[@class='picture-elem']/img)[1]")));
		a.build().perform();
		// click QuickView button
		String curwindow = driver.getWindowHandle();
		System.out.println("Main Window " + curwindow);
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		// Print the cost and the discount percentage
		String subwindow = driver.getWindowHandle();
		System.out.println("Sub Window " + subwindow);
		String prodprice = driver.findElement(By.className("payBlkBig")).getText();
		System.out.println("Product price is: " + prodprice);
		String proddis = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Product discount is: " + proddis);
		// Take the snapshot of the shoes.
		File screenshot = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\user\\eclipse-workspace\\MavenProject\\src\\main\\java\\screenshot\\SS001.png");
		FileUtils.copyFile(screenshot, dest);
		// Close the current window
		 driver.close();
		// Close the main window
		 driver.quit();

	}

}
