package com.freeCrm.testMethods;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPage 
{
	
	public static WebDriver driver;
	@BeforeMethod
	public void preCondition()
	{
		System.setProperty("webdriver.chrome.driver", "E://ANKUR MALVIYA/eclipse/ecllipse Second/IrctcMavenProject/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://ui.freecrm.com/");
	}
	
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}
	

	@Test(dataProvider="Data" , enabled=false)
	public void LoinPageTest(String un , String pw)
	{
			WebElement login = driver.findElement(By.xpath(".//input[@type='text']"));
			login.sendKeys(un);
			WebElement pass = driver.findElement(By.xpath(".//input[@type='password']"));
			pass.sendKeys(pw);
			WebElement btn = driver.findElement(By.xpath(".//div[text(),'Login']"));
			btn.click();
	}
	@Test
	public void linkTest() throws MalformedURLException, IOException
	{
		List<WebElement> links = driver.findElements(By.tagName("a"));
		for(WebElement link : links)
		{
			String linkT= link.getAttribute("href");
			System.out.println("The link address is :"+linkT);
			HttpURLConnection h = (HttpURLConnection) new URL(linkT).openConnection();
			h.setRequestMethod("HEAD");
			h.connect();
			int resp = h.getResponseCode();
			System.out.println("The resp code is : "+resp);
			if(linkT==null||linkT.isEmpty())
			{
				System.out.println("The link is null");
			}
			else
			{
				System.out.println(linkT+" is valid");
			}
			
			
		}
	}
	
	@DataProvider(name="Data")
	public static Object[] [] credentials()
	{
		return new Object[][] {{"aaa","123"},{"om","12"},{"ankur123","test123"}};
	}
	
}
