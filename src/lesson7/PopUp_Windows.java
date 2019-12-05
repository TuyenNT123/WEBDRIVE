package lesson7;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import  org.testng.Assert;

public class PopUp_Windows {
	WebDriver driver;
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC02_WindowsTab() throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentWin = driver.getWindowHandle();
		
		WebElement google = driver.findElement(By.xpath("//a[text()='GOOGLE']"));
		google.click();
		Thread.sleep(3000);
//		switchToChildWindowByid(parentWin);
		switchToChildWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		driver.switchTo().window(parentWin);
		WebElement fb = driver.findElement(By.xpath("//a[text()='FACEBOOK']"));
		fb.click();
		Thread.sleep(3000);
		switchToChildWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		driver.switchTo().window(parentWin);
		
		WebElement tiki = driver.findElement(By.xpath("//a[text()='TIKI']"));
		tiki.click();
		Thread.sleep(3000);
		switchToChildWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		if(closeAllWindows(parentWin)) {
			Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");
		}else {
			System.out.println("Fail");
		}
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void switchToChildWindowByid(String parent) throws Exception{
		Set<String> allWindows = driver.getWindowHandles();
		for (String tab : allWindows) {
			if (!tab.contentEquals(parent)) {
				driver.switchTo().window(tab);
				break;
			}
		}
		Thread.sleep(3000);
	}
	public void switchToChildWindowByTitle(String title) throws Exception {
		Set<String> allWindows = driver.getWindowHandles();
		for (String tab : allWindows) {
			driver.switchTo().window(tab);
			Thread.sleep(3000);
			String currentTitle = driver.getTitle();
			if (currentTitle.equals(title)) {
				break;
			}
		}
	}
	public boolean closeAllWindows(String parent) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String tab : allWindows) {
			if (!tab.equals(parent)) {
				driver.switchTo().window(tab);
				driver.close();
			}
		}
		driver.switchTo().window(parent);
		if(driver.getWindowHandles().size() == 1) {
			return true;
		}else {
			return false;
		}
	}
}
