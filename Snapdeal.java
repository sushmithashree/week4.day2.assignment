package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {
	public static void main(String[]args) throws InterruptedException, IOException  
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get(" https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		WebElement men = driver.findElement(By.className("navlink"));
		Actions builder = new Actions(driver);
		builder.moveToElement(men).perform();
		WebElement sportShoe = driver.findElement(By.className("linkTest"));
		builder.click(sportShoe).perform();
		Thread.sleep(2000);
		String count = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("The total count of Sports shoes: "+count);
		WebElement trainingShoes = driver.findElement(By.xpath("//div[text()='Training Shoes']"));
		builder.click(trainingShoes).perform();
		String titleOfPage = driver.getCurrentUrl();
		System.out.println("The Title of the page is: "+titleOfPage);
		Thread.sleep(2000);
		WebElement sort = driver.findElement(By.className("sort-selected"));
		builder.click(sort).perform();
		String titleOfSortedPage = driver.getCurrentUrl();
		System.out.println("The Title of the Sorted page is: "+titleOfSortedPage);
		Thread.sleep(2000);
		WebElement sorted = driver.findElement(By.xpath("(//li[@class='search-li'])[1]"));
		builder.click(sorted).perform();
		Thread.sleep(2000);
		if(titleOfSortedPage.equals(titleOfPage)) {
			System.out.println("The Items displayed are sorted correctly");
		}
		else {
			System.out.println("The Items displayed are not sorted correctly");
		}
		driver.findElement(By.className("input-filter")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		Thread.sleep(2000);
		driver.findElement(By.className("input-filter")).sendKeys("900");
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1200");
		Thread.sleep(2000);
	    WebElement go = driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]"));
	    builder.click(go).perform();
	    Thread.sleep(2000);
	    WebElement navy = driver.findElement(By.xpath("//label[@for='Color_s-Navy']"));
	    builder.click(navy).perform();
	    Thread.sleep(2000);
	    List<WebElement> FiltersList = driver.findElements(By.className("filters"));
		System.out.println("Filters Applied: "+FiltersList.size());
	    
	   WebElement element = driver.findElement(By.xpath("//img[@class='product-image wooble']"));
	   builder.moveToElement(element).perform();
	   WebElement quickView = driver.findElement(By.xpath("//div[contains(@class,'center quick-view-bar')]"));
	   builder.click(quickView).perform();
	   Thread.sleep(2000);
	   
	   String firstWindow = driver.getWindowHandle();
	   Set<String>windowHandles = driver.getWindowHandles();
	   WebElement price = driver.findElement(By.xpath("//div[contains(@class,'product-price pdp-e-i-PAY-l')]"));
	   System.out.println("The Cost is: "+price.getText());
	   Thread.sleep(2000);
	   
	    File source = driver.getScreenshotAs(OutputType.FILE);
	    File dest = new File(".src/main/resources/snaps/SnapDeal01.jpg");
	    FileUtils.copyFile(source, dest);
	    
	    driver.findElement(By.xpath("//div[contains(@class,'close close1')]")).click();
	    //driver.close();
	    
	}
}
