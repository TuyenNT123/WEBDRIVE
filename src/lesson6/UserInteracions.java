package lesson6;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserInteracions {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_02_ClickAndHoldeElement() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> items = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']")); 
		Actions action = new Actions(driver);
		action.clickAndHold(items.get(0)).moveToElement(items.get(3)).release().perform();
		List<WebElement> itemsIsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(itemsIsSelected.size(),4);
		
	}
//	@Test
	public void TC_03_ClickAndSelectRandomItem() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> items = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']")); 
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).perform();
		action.click(items.get(0)).click();
		action.click(items.get(2)).click();
		action.click(items.get(4)).click();
		action.click(items.get(6)).click();
		List<WebElement> itemsIsSelected = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		Assert.assertEquals(itemsIsSelected.size(),4);
	}
//	@Test
	public void TC_04_DoubleClick () {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement btn = driver.findElement(By.xpath("//button[text()='Double click me']")); 
		Actions action = new Actions(driver);
		
		action.doubleClick(btn).perform();
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
