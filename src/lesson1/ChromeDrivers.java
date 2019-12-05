package lesson1;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeDrivers {
	ChromeDriver driver;
	
	@BeforeClass
	public void beforeClass() {	
		System.setProperty("webdriver.chrome.driver", "E:\\Congviec\\autotest\\drivers\\chromedriver.exe");
		driver = new ChromeDriver(); 
		String url = "http://google.com";
		driver.get(url);
	}
	@Test
	public void TC01() {
		System.out.println("welcome");
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
}

