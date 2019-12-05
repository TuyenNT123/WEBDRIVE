package lesson1;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebDriver {
	// khai bao
	
	FirefoxDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver","E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		String url = "https://github.com/mozilla/geckodriver/releases";
		driver.get(url);
	}

	@Test
	public void tC01_Login () {
		System.out.println("TC 01");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
