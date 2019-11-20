package lesson5;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;
	 
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void tc01_Button() {
		driver.get("http://live.demoguru99.com/");
		By xpathLinkMyCount = By.xpath("//div[@class='footer']//a[text() = 'My Account']");
		By xpathBtnCreateAcc = By.xpath("//form[@id = 'login-form']//a[@class='button']");
		
		clickElement(xpathLinkMyCount);
		String url = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/login/", url);
		clickElement(xpathBtnCreateAcc);
		url = driver.getCurrentUrl();
		Assert.assertEquals("http://live.demoguru99.com/index.php/customer/account/create/", url);
	}
	@Test
	public void tc02_Checkbox(){
		driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		By xpathEquipDualZone = By.xpath("//input[@id='eq5']");
		WebElement equipDualZone = driver.findElement(xpathEquipDualZone);
		checkElement(xpathEquipDualZone);
		Assert.assertTrue(equipDualZone.isSelected());
		unCheckElement(xpathEquipDualZone);
		Assert.assertFalse(equipDualZone.isSelected());
	}
	@Test
	public void tc03_RadioButton(){
		driver.get("https://demos.telerik.com/kendo-ui/styling/radios");
		By xpathRadioBtn = By.xpath("//input[@id='engine3']");
		WebElement radioBtn = driver.findElement(xpathRadioBtn);
		checkElement(xpathRadioBtn);
		if(!radioBtn.isSelected()) {
			radioBtn.click();
		}
	}
	@Test
	public void tc04_AcceptAlert(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By btn = By.xpath("//button[text()='Click for JS Alert']");
		clickElement(btn);
		Alert alert = driver.switchTo().alert();
		String expected = "I am a JS Alert";
		String actual = alert.getText();
		Assert.assertEquals(actual, expected);
		alert.accept();
		expected= "";
		actual = "";
		expected = "You clicked an alert successfully";
		actual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actual, expected.trim());
	}
	@Test
	public void tc05_ConfirmAlert(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By btn = By.xpath("//button[text()='Click for JS Confirm']");
		clickElement(btn);
		Alert alert = driver.switchTo().alert();
		String expected = "I am a JS Confirm";
		String actual = alert.getText();
		Assert.assertEquals(actual, expected);
		alert.dismiss();
		expected= "";
		actual = "";
		expected = "You clicked: Cancel";
		actual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actual, expected.trim());
	}
	@Test
	public void tc06_PromptAlert(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		By btn = By.xpath("//button[text()='Click for JS Prompt']");
		clickElement(btn);
		Alert alert = driver.switchTo().alert();
		String expected = "I am a JS prompt";
		String actual = alert.getText();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
		String str = "tuyennt";
		alert.sendKeys(str);
		alert.accept();
		expected= "";
		actual = "";
		expected = "You entered: "+str;
		actual = driver.findElement(By.xpath("//p[@id='result']")).getText();
		Assert.assertEquals(actual, expected.trim());
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void clickElement(By xpath) {
		WebElement e = driver.findElement(xpath);
		if(e.isDisplayed() ) {
			e.click();
		}else {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", e);
		}
	}
	public void checkElement(By xpath) {
		WebElement e = driver.findElement(xpath);
		if(e.isDisplayed() && !e.isSelected()) {
			e.click();
		}else if(!e.isSelected()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;			
			js.executeScript("arguments[0].click();", e);
		}
	}
	
	public void unCheckElement(By xpath) {
		WebElement e = driver.findElement(xpath);
		if(e.isDisplayed() && e.isSelected()) {
			e.click();
		}else if(e.isSelected()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;			
			js.executeScript("arguments[0].click();", e);
		}
	}
}
