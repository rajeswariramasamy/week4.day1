package week4.day1;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Chittorgarh {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://www.chittorgarh.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println(driver.getCurrentUrl());
		System.err.println(driver.getTitle());
		//2. Click on stock market
		driver.findElement(By.id("navbtn_stockmarket")).click();
		//3. Click on NSE bulk Deals
		driver.findElement(By.linkText("NSE Bulk Deals")).click();
		//4. Get all the Security names
		List<String> s = new ArrayList<>();
		int rowsize = driver.findElements(By.xpath("//div[@class='table-responsive']/table/tbody/tr")).size();
		for (int i=0;i<rowsize;i++)
		{
			String securitytext = driver.findElement(By.xpath("//div[@class='table-responsive']/table/tbody/tr["+(i+1)+"]/td[3]")).getText();
			s.add(securitytext);
			
		}
		//5. Ensure whether there are duplicate Security names
		System.out.println("List with Duplicate Security names : "+s);
		Set<String> s1 = new LinkedHashSet<>();
		for (int j=0;j<s.size();j++)
		{
			boolean add1 = s1.add(s.get(j));
			if (add1 != true)
			{
				System.out.println("Duplicate Security name : " + s.get(j));
			}
		}
		System.out.println("Set with unique security names : "+s1);
	}

}
