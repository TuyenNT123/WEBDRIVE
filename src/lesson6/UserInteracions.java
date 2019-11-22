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
//	@Test
	public void TC_01_MoveMouseToElement() {
		driver.get("https://www.myntra.com/");
		WebElement profile = driver.findElement(By.xpath("//span[text()='Profile']"));
		Actions action = new Actions(driver);
		action.moveToElement(profile).perform();
		WebElement itmeLogIn = driver.findElement(By.xpath("//a[text()='log in']"));
		WebElement itmeLogout = driver.findElement(By.xpath("//div[text()=' Logout ']"));
		itmeLogout.click();
		Assert.assertTrue(itmeLogIn.isDisplayed());
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
	public void TC_04_DoubleClick () throws Exception {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		WebElement btn = driver.findElement(By.xpath("//button[text()='Double click me']")); 
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
	    Thread.sleep(500); 
		Actions action = new Actions(driver);
		action.doubleClick(btn).perform();
		WebElement text = driver.findElement(By.xpath("//p[@id='demo']"));
		Assert.assertEquals(text.getText(), "Hello Automation Guys!");
	}
//	@Test
	public void TC_05_RightClickToElement () {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement btn = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(btn).perform();
		WebElement quitBtn = driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']"));
		action.moveToElement(quitBtn).perform();
		Assert.assertTrue(quitBtn.getAttribute("class").contains("context-menu-visible"));
		
		
	}
//	@Test
	public void TC_06_DragAndDropElement () {
		driver.get("https://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement cycleSmall = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement cycleBig = driver.findElement(By.xpath("//div[@id='droptarget']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(cycleSmall, cycleBig).build().perform();
		String actual = cycleBig.getText();
		Assert.assertEquals(actual, "You did great!");
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
