package lession2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class XPath {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.chrome.driver", "E:\\Congviec\\autotest\\drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver","E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		String url = "http://live.demoguru99.com/";
		driver.get(url);
		// wait định ngầm; thời gian chờ cho 1 element hienr thị trên màn hinh. Cái hàm
		// này sẽ ảnh hưởng đến hàm findElement & findElements
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void tc01() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String strWrongEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		String strWrongURDEmail = "This is a required field.";
		Assert.assertEquals(strWrongURDEmail, strWrongEmail);

		String strWrongApp = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		String strWrongURD = "This is a required field.";
		Assert.assertEquals(strWrongURD, strWrongApp);

	}

	@Test
	public void tc02() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("121@3223.2323");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String strWrongEmail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		String strWrongURDEmail = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(strWrongURDEmail, strWrongEmail);

	}
	@Test
	public void tc03() {
//		tuyennt@vnpay.vn 123456
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("tuyennt@vnpay.vn");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String strWrongPass = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		String strWrongURDPass = "Please enter 6 or more characters without leading or trailing spaces.";
		Assert.assertEquals(strWrongURDPass, strWrongPass);

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
