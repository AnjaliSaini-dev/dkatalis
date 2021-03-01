package dkatalis;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader;

public class MainTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\apps\\chromedriver_win32\\chromedriver.exe");
		
	}

	@SuppressWarnings("deprecation")
	@Test
	public void checkoutSuccessTest() throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://demo.midtrans.com/");
		
		//Click Buy Now Button
		findElementByXpathAndClick(driver, "//*[@id=\"container\"]/div/div/div[1]/div[2]/div/div/a");
		
	
		Thread.sleep(1000);
	    
		//Click Checkout Button
		findElementByXpathAndClick(driver, "//*[@id=\"container\"]/div/div/div[2]/div[2]/div[1]");
				
		
		driver.switchTo().frame("snap-midtrans");
		
	
		//Click Continue button
		findElementByXpathAndClick(driver, "//*[@id=\"application\"]/div[1]/a");
		
		
		//Click Credit Card Button
		findElementByXpathAndClick(driver, "//*[@id=\"payment-list\"]/div[1]/a");
		
		
		//Enter Card Number
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[1]/input", "4811 1111 1111 1114");
		
		//Enter Expiration date
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[2]/input", "12/24");
        
	
		//Enter CVV Number
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[3]/input", "123");
     
		
		//Click Pay Now Button 
		findElementByXpathAndClick(driver, "//*[@id=\"application\"]/div[1]/a");
		
		WebElement iframeElement = driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/iframe"));
		
		driver.switchTo().frame(iframeElement);
		
		WebDriverWait iframeWait = new WebDriverWait(driver, 10);
		iframeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PaRes")));
        
		
		WebElement enterPasswordOtp = driver.findElement(By.id("PaRes"));
		enterPasswordOtp.sendKeys("112233");

		
		//Click OK
		findElementByXpathAndClick(driver,"//*[@id=\"acsForm\"]/div[6]/div/button[1]");
		
		
		
		Thread.sleep(7000);
		Assert.assertTrue((driver.findElements(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/iframe"))).size()==0);
		
		
		driver.close();
		
		
		
	}
	
	@Test
	public void checkoutFailTest() throws InterruptedException {
		WebDriver driver =new ChromeDriver();
		
		driver.get("https://demo.midtrans.com/");
		
		//Click Buy Now Button
		findElementByXpathAndClick(driver, "//*[@id=\"container\"]/div/div/div[1]/div[2]/div/div/a");
		
	
		Thread.sleep(1000);
	    
		//Click Checkout Button
		findElementByXpathAndClick(driver, "//*[@id=\"container\"]/div/div/div[2]/div[2]/div[1]");
				
		
		driver.switchTo().frame("snap-midtrans");
		
	
		//Click Continue button
		findElementByXpathAndClick(driver, "//*[@id=\"application\"]/div[1]/a");
		
		
		//Click Credit Card Button
		findElementByXpathAndClick(driver, "//*[@id=\"payment-list\"]/div[1]/a");
		
		
		//Enter Card Number
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[1]/input", "4911 1111 1111 1113");
		
		//Enter Expiration date
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[2]/input", "02/21");
        
	
		//Enter CVV Number
		enterData(driver, "//*[@id=\"application\"]/div[3]/div/div/div/form/div[2]/div[3]/input", "123");
     
		
		//Click Pay Now Button 
		findElementByXpathAndClick(driver, "//*[@id=\"application\"]/div[1]/a");
		
		WebElement iframeElement = driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/iframe"));
		
		driver.switchTo().frame(iframeElement);
		
		WebDriverWait iframeWait = new WebDriverWait(driver, 10);
		iframeWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PaRes")));
        
		
		WebElement enterPasswordOtp = driver.findElement(By.id("PaRes"));
		enterPasswordOtp.sendKeys("112233");

		
		//Click OK
		findElementByXpathAndClick(driver,"//*[@id=\"acsForm\"]/div[6]/div/button[1]");
		
		
		
		System.out.println("Hello\n\n\n");
		WebElement test = (driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/div/div/div[3]/div")));
		System.out.println((test).getAttribute("innerHTML"));
		System.out.println("Hello\n\n\n");
		Thread.sleep(1115000);
		
		Assert.assertEquals((driver.findElement(By.xpath("//*[@id=\"application\"]/div[3]/div/div/div/div/div/div[1]/span"))).getAttribute("innerHTML"), "Transaction failed");
		
		
		
		driver.close();
		
	}
	
	
    //Enter Data in Input Field
	private void enterData(WebDriver driver, String xpathValue, String data) {
		
	waitIfElementNotFound(driver, xpathValue);
		
		WebElement inputField = driver.findElement(By.xpath(xpathValue));
		inputField.sendKeys(data);
	}
	
	//Explicit Wait until Element is Found
	private void waitIfElementNotFound(WebDriver driver, String xpathValue) {
		
		if(driver.findElements(By.xpath(xpathValue)).size()==0) {
			WebDriverWait wait = new WebDriverWait(driver, 10);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValue)));
		}
		
	}
	
	//Finding and Clicking Elements using Xpath
	private void findElementByXpathAndClick(WebDriver driver, String xpathValue) {
		
	waitIfElementNotFound(driver, xpathValue);
		
		
		WebElement button = driver.findElement(By.xpath(xpathValue));
		button.click();
	}
	


}
