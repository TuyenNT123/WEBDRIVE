package lesson4;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Textbox_Textare_Dropdown {
	WebDriver driver;
	
	String userName ="mngr233426";
	String userPassword ="gurYguh";
	By userID = By.xpath("//input[@name='uid']");
	By password = By.xpath("//input[@name='password']");
	By btnLogin = By.xpath("//input[@name='btnLogin']");
	By dropDownGender = By.xpath("//*[@id='input_71']"); //for test TC02_dropDown
	By xpathbtnNewCus = By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']");
	By xpathName = By.xpath("//input[@name='name']");
	
	By xpathMale = By.xpath("//input[@name='rad1'][1]");
	By xpathDateOfBirth  = By.xpath("//*[@id='dob']");
	By xpathAddress  = By.xpath("//textarea[@name='addr']");
	By xpathCity = By.xpath("//input[@name='city']");
	By xpathState  = By.xpath("//input[@name='state']");
	By xpathPIN  = By.xpath("//input[@name='pinno']");
	By xpathMobileNumber  = By.xpath("//input[@name='telephoneno']");
	By xpathEmail  = By.xpath("//input[@name='emailid']");
	By xpathPassword  = By.xpath("//input[@name='password']");
	By xpathBtnSubmit  = By.xpath("//input[@name='sub']");
	By xpathMesgSuccess= By.xpath("//p[@class='heading3']");
	String urlCurrent ="";
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		String url = "http://demo.guru99.com/v4";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority=1, enabled = true)
	public void TC01_loginWithCorrectAcc() {
		WebElement  user = driver.findElement(userID);
		WebElement  pass = driver.findElement(password);
		WebElement  login = driver.findElement(btnLogin);
		user.clear();
		user.sendKeys(userName);
		pass.clear();
		pass.sendKeys(userPassword);
		login.click();
		WebElement btnNewCus = driver.findElement(xpathbtnNewCus);
		urlCurrent = btnNewCus.getAttribute("href");
//		System.out.println(driver.getCurrentUrl());
		
	}
	@Test(enabled = false)
	public void TC02_dropDown() throws Exception {
		driver.get("https://automationfc.github.io/multiple-fields/index.html");
		WebElement gender = driver.findElement(dropDownGender);
		Select selectGender = new Select(gender);
//		int no = selectGender.getOptions().size();
//		Assert.assertEquals(3, no);
//		String textGender = selectGender.getFirstSelectedOption().getText();
		String textGender = selectGender.getOptions().get(1).getText();
		Assert.assertEquals("Male", textGender);
//		Assert.assertFalse(select.isMultiple());
	}
	@Test(priority=2, enabled = true)
	public void payment_CreateNewAccount() throws Exception {
		driver.get(urlCurrent);
		WebElement btnNewCus = driver.findElement(xpathbtnNewCus);
		btnNewCus.click();
		WebElement name = driver.findElement(xpathName);
		WebElement male = driver.findElement(xpathMale);
		WebElement dateOfBirth = driver.findElement(xpathDateOfBirth);
		WebElement address = driver.findElement(xpathAddress);
		WebElement city = driver.findElement(xpathCity);
		WebElement state = driver.findElement(xpathState);
		WebElement pin = driver.findElement(xpathPIN);
		WebElement telephone = driver.findElement(xpathMobileNumber);
		WebElement email = driver.findElement(xpathEmail);
		WebElement pass = driver.findElement(xpathPassword);
		WebElement btnSubmit = driver.findElement(xpathBtnSubmit);
		WebElement mesgSuccess = driver.findElement(xpathMesgSuccess);
		
		String expectName= "AUTOMATION TESTING";
		String expectDateOfBirth = "01/01/1989";
		String expectAddress = "PO Box 911 8331 Duis Avenue";
		String expectCity = "Tampa";
		String expectState = "FL";
		String expectPin = "466250";
		String expectMobile = "4555442476";
		String expectEmail = "automation@gmail.com";
		String expectPassword = "automation";
		String expectMesgSuccess = "Customer Registered Successfully!!!";
		
		
		if(male.isDisplayed() && male.isEnabled() && male.isSelected()) {
			male.click();
		}
		enterValue(name, expectName);
		enterValue(dateOfBirth, expectDateOfBirth);
		enterValue(address, expectAddress);
		enterValue(city, expectCity);
		enterValue(state, expectState);
		enterValue(pin, expectPin);
		enterValue(telephone, expectMobile);
		enterValue(email, expectEmail);
		enterValue(pass, expectPassword);
		
		btnSubmit.click();		
	}
	public void enterValue(WebElement e, String value) {
		if(e.isDisplayed() && e.isEnabled()) {
			e.clear();
			e.sendKeys(value);
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
