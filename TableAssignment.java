package week4.day1;

import java.time.Duration;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TableAssignment {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		System.out.println(driver.getCurrentUrl());
		System.err.println(driver.getTitle());

		// Get the count of number of columns
		WebElement tab = driver.findElement(By.tagName("table"));
		int noofcolumn = tab.findElements(By.tagName("th")).size();
		System.out.println("No of Columns in Table: " + noofcolumn);

		// Get the count of number of rows
		List<WebElement> rows = tab.findElements(By.tagName("tr"));
		int noofrows = rows.size();
		System.out.println("No of Rows in Table: " + noofrows);

		// Get the progress value of 'Learn to interact with Elements'
		int count = 0;
		int final1 = 0;
		for (int i = 1; i < noofrows; i++) {
			for (int j = 1; j <= noofcolumn; j++) {
				WebElement ele = driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[" + j + "]"));
				String text = ele.getText();

				if (text.equals("Learn to interact with Elements")) {
					System.out.println(text + "->"
							+ (driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[" + (j + 1) + "]")))
									.getText());
				}

			}

			String text2 = driver.findElement(By.xpath("//table//tr[" + (i + 1) + "]//td[2]")).getText();
			String text3 = text2.replaceAll("\\D", "");
			int percent = Integer.parseInt(text3);
			if (percent < count) {
				final1 = percent;
				count = final1;
			} else {
				count = percent;
			}

		}
		
		for (int i1 = 1; i1 < noofrows; i1++) {
			for (int j1 = 1; j1 <= noofcolumn; j1++) {
				WebElement ele = driver.findElement(By.xpath("//table//tr[" + (i1 + 1) + "]//td[" + j1 + "]"));
				String text3 = ele.getText();
				if (text3.contains(Integer.toString(final1)))
				{
					driver.findElement(By.xpath("//table//tr[" + (i1 + 1) + "]//td[3]//input")).click();
					break;
				}
			}
		}
	}

}
