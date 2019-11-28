package lesson8;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;

public class WebDriver_Wait {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

//	@Test
	public void TC01_demo() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		WebElement btnCreateAcc = driver.findElement(By.xpath("//button[@id='SubmitCreate']"));
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//visiable
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitCreate']")));
		Assert.assertTrue(btnCreateAcc.isDisplayed());
		// presence 
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='SubmitCreate']")));
		//invisiable
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//a[text()='women']")));
		
	}
//	@Test
	public void TC02_implicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		try {
			driver.findElement(By.xpath("//button[text()='Start']")).click();
			WebElement text = driver.findElement(By.xpath("//div[@id='finish']/h4"));
			Assert.assertTrue(text.isDisplayed());
			Assert.assertEquals("Hello World!", text.getText());
		}catch (Exception e) {
			System.out.println("Exception: don't find element");
		}
	}
//	@Test
	public void TC03_explicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			driver.findElement(By.xpath("//button[text()='Start']")).click();
			By xpathLoading = By.xpath("//div[@id='loading']/h4");
			wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathLoading));
			Assert.assertEquals("Hello World!", driver.findElement(By.xpath("//div[@id='finish']/h4")).getText());
			System.out.println("3s");
		}catch (Exception e) {
			wait = new WebDriverWait(driver, 5);
			try {
				driver.findElement(By.xpath("//button[text()='Start']")).click();
				By xpathLoading = By.xpath("//div[@id='loading']/h4");
				wait.until(ExpectedConditions.invisibilityOfElementLocated(xpathLoading));
				Assert.assertEquals("Hello World!", driver.findElement(By.xpath("//div[@id='finish']/h4")).getText());
				System.out.println("5s");
			}catch (Exception e2) {
				System.out.println("Exception: don't find element");
			}	
		}
	}
//	@Test
	public void TC04_explicitWait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		try {
			driver.findElement(By.xpath("//button[text()='Start']")).click();
			By xpathText = By.xpath("//div[@id='finish']/h4");
			wait.until(ExpectedConditions.visibilityOfElementLocated(xpathText));
			Assert.assertEquals("Hello World!", driver.findElement(xpathText).getText());
			System.out.println("3s");
		}catch (Exception e) {
			wait = new WebDriverWait(driver, 5);
			try {
				driver.findElement(By.xpath("//button[text()='Start']")).click();
				By xpathText = By.xpath("//div[@id='finish']/h4");
				wait.until(ExpectedConditions.visibilityOfElementLocated(xpathText));
				Assert.assertEquals("Hello World!", driver.findElement(xpathText).getText());
				System.out.println("5s");
			}catch (Exception e2) {
				System.out.println("Exception: don't find element");
			}	
		}
	}
//	@Test
	public void TC05_explicitWait() {
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
