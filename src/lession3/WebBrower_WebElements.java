package lession3;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class WebBrower_WebElements {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver",
				"E:\\Congviec\\autotest\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		String url = "https://automationfc.github.io/basic-form/index.html";
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(enabled = true)
	public void checkElementDisplayed() {
		WebElement email = driver.findElement(By.xpath("//label[text() ='Email:']"));
		WebElement age = driver.findElement(By.xpath("//label[text() ='Age:']"));
		WebElement education = driver.findElement(By.xpath("//label[text() ='Education:']"));
		WebElement inputEmail = driver.findElement(By.xpath("//*[@id='mail']"));
		WebElement radioAgeUnder18 = driver.findElement(By.xpath("//*[@id='under_18']"));
		WebElement edu = driver.findElement(By.xpath("//*[@id='edu']"));
		if (email.isDisplayed() == true) {
			inputEmail.clear();
			inputEmail.sendKeys("tuyennt@vnpay.vn");
		}
		if (education.isDisplayed()) {
			edu.clear();
			edu.sendKeys("Education textarea");
		}
		if (age.isDisplayed() && radioAgeUnder18.isDisplayed()) {
			radioAgeUnder18.click();
		}

	}
	@Test(enabled = true)
	public void checkElementEnabled() {
		WebElement inputEmail = driver.findElement(By.xpath("//*[@id='mail']"));
		WebElement radioAgeUnder18 = driver.findElement(By.xpath("//*[@id='under_18']"));
		WebElement edu = driver.findElement(By.xpath("//*[@id='edu']"));
		WebElement job1 = driver.findElement(By.xpath("//*[@id='job1']"));
		WebElement slide1 = driver.findElement(By.xpath("//*[@id='slider-1']"));
		WebElement dev = driver.findElement(By.xpath("//*[@id='development']"));
		WebElement pass = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement bio = driver.findElement(By.xpath("//*[@id='bio']"));
		WebElement job2 = driver.findElement(By.xpath("//*[@id='job2']"));
		WebElement interestDisable = driver.findElement(By.xpath("//*[@id='check-disbaled']"));
		WebElement slide2 = driver.findElement(By.xpath("//*[@id='slider-2']"));
		WebElement ageDisable = driver.findElement(By.xpath("//*[@id='radio-disabled']"));
		System.out.println("--------------------------");
		if (inputEmail.isEnabled() == true) {
			System.out.println("Email is enabled");
		}else {
			System.out.println("Email is disabled");
		}
		if (radioAgeUnder18.isEnabled() == true) {
			System.out.println("Age (Under 18) is enabled");
		}else {
			System.out.println("Age (Under 18) is disabled");
		}
		if (edu.isEnabled() == true) {
			System.out.println("Education is enabled");
		}else {
			System.out.println("Education is disabled");
		}
		if (job1.isEnabled() == true) {
			System.out.println("Job Role 01 is enabled");
		}else {
			System.out.println("Job Role 01 is disabled");
		}
		if (dev.isEnabled() == true) {
			System.out.println("Interests(Development) is enabled");
		}else {
			System.out.println("Interests(Development) is disabled");
		}
		if (slide1.isEnabled() == true) {
			System.out.println("Slide 01 is enabled");
		}else {
			System.out.println("Slide 01 is disabled");
		}
		if (pass.isEnabled() == true) {
			System.out.println("Password is enabled");
		}else {
			System.out.println("Password is disabled");
		}
		if (ageDisable.isEnabled() == true) {
			System.out.println("Age (Radio button is disabled) is enabled");
		}else {
			System.out.println("Age (Radio button is disabled) is disabled");
		}
		if (bio.isEnabled() == true) {
			System.out.println("Biography is enabled");
		}else {
			System.out.println("Biography is disabled");
		}
		if (job2.isEnabled() == true) {
			System.out.println("Job Role 02 is enabled");
		}else {
			System.out.println("Job Role 02 is disabled");
		}
		if (interestDisable.isEnabled() == true) {
			System.out.println("Interest (Checkbox is Disabled) is enabled");
		}else {
			System.out.println("Interest (Checkbox is Disabled) is disabled");
		}
		if (slide2.isEnabled() == true) {
			System.out.println("Slide 02 is enabled");
		}else {
			System.out.println("Slide 02 is disabled");
		}
	}
	@Test(enabled = true)
	public void checkSelected() {
		WebElement radioAgeUnder18 = driver.findElement(By.xpath("//*[@id='under_18']"));
		WebElement dev = driver.findElement(By.xpath("//*[@id='development']"));
		System.out.println("--------------------------");
		if (radioAgeUnder18.isEnabled() == true && radioAgeUnder18.isDisplayed() == true) {
			radioAgeUnder18.click();
		}
		if (dev.isEnabled() == true && dev.isDisplayed() == true) {
			dev.click();
		}
		if(radioAgeUnder18.isSelected()) {
			System.out.println("Age under 18 is checked");
		}
		if(dev.isSelected()) {
			System.out.println("Intersets Development is checked");
			System.out.print("Uncheck Development: ");
			dev.click();
			if(!dev.isSelected()) {
				System.out.println("Success");
			}else {
				System.out.println("Fail");
			}
			
		}
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
