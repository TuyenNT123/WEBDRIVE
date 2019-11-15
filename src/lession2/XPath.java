package lession2;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
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
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
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
	@Test
	public void tc04() {
//		tuyennt@vnpay.vn 123456
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("tuyennt@vnpay.vn");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12345679");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
//		String strApp = driver.findElement(By.xpath("//form[@id='login-form']/preceding-sibling::ul[@class='messages']//span")).getText();
		String strApp = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		String strRight = "Invalid login or password.";
		Assert.assertEquals(strRight, strApp);

	}

	@Test
	public void tc05() {
		String email = "tuyennt@vnpay.vn";
		String pwd = "123456";
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']"))
				.click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(pwd);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
//		String strApp = driver
//				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'tuyên Nguyrn Thu')]")).getText();
		String strApp = driver.findElement(By.xpath("//p[@class='welcome-msg']")).getText();
		String strRight = "tuyên Nguyrn Thu";
		String welcome = "Welcome, ";
		strApp = strApp.substring(welcome.length(),welcome.length()+strRight.length()).toLowerCase();
		Assert.assertEquals(strRight.toLowerCase(), strApp);
		
	}

	@Test
	public void tc06() {
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//li[@class='first']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']")).click();
		String firstName ="Nguyễn";
		String middleName = "Thị";
		String lastName = "Tuyến";
		String email = radomInt()+"@vnpay.vn";
		String pwd = "123456";
		String confirmPwd = "123456";
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys(middleName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(confirmPwd);
		driver.findElement(By.xpath("//button[@class='button' and @title='Register']")).click();
		String strShow = driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText();
		String strEqual = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(strEqual, strShow);
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//a[@class='skip-link skip-account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		String url = driver.getCurrentUrl();
		if(url=="http://live.demoguru99.com/index.php/customer/account/logoutSuccess/") {
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			url = driver.getCurrentUrl();
		}
		System.out.println(url);
		String home = "http://live.demoguru99.com/index.php/";
		Assert.assertEquals(home, url);
		

	}
	public static int radomInt() {
		Random random = new Random();
		int n = random.nextInt(99999);
		return n;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
