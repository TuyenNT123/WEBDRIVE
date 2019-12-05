package lesson4;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Dropdown {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", "E:\\Congviec\\autotest\\drivers\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void tc01_HtmlDropdownList() throws Exception{
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement job1 = driver.findElement(By.xpath("//select[@id='job1']"));
		Select selectJob1 = new Select(job1);
		Assert.assertFalse(selectJob1.isMultiple());
//		selectJob1.get
		selectJob1.selectByVisibleText("Automation Tester");
		Assert.assertEquals("Automation Tester", selectJob1.getFirstSelectedOption().getText());
		
		selectJob1.selectByValue("manual");
		Assert.assertEquals("Manual Tester", selectJob1.getFirstSelectedOption().getText());
		
		selectJob1.selectByIndex(3);
		Assert.assertEquals("Mobile Tester", selectJob1.getFirstSelectedOption().getText());
		
		Assert.assertEquals(5, selectJob1.getOptions().size());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
