package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {
	public static void main(String[]args) throws InterruptedException, IOException  
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://dev91206.service-now.com");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("qfTSkA19EJux");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("filter")).sendKeys("incident");
		driver.findElement(By.id("filter")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("incident.short_description")).sendKeys("Application not working");
		String incNumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("The Incident Number is: "+incNumber);
		
		driver.findElement(By.xpath("//button[@id='lookup.incident.caller_id']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		System.out.println(windowHandles);
		List<String> list = new ArrayList<String>(windowHandles);
		System.out.println("Current Url is: "+driver.getCurrentUrl());
		driver.switchTo().window(list.get(1));
		System.out.println(list.get(1));
		driver.findElement(By.linkText("Abel Tuter")).click();
		Thread.sleep(2000);
		driver.switchTo().window(list.get(0));
		System.out.println(driver.getCurrentUrl());
		System.out.println(list.get(0));
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(incNumber);
		driver.findElement(By.xpath("(//input[@class='form-control'])[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		
		String SearchIncNumb = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println("The Insearch Incident Number is: "+SearchIncNumb);
		if(incNumber.equals(SearchIncNumb)) {
			System.out.println("Incident number is created and verified");
		}
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		Thread.sleep(2000);
		File source = driver.getScreenshotAs(OutputType.FILE);
		String imageFileName = "./src/main/resources/snapshot/"+incNumber+".png";
		File dest = new File(imageFileName);
		FileUtils.copyFile(source, dest);
			

}
}
