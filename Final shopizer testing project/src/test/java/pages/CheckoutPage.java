package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utility.ConfigFileReader;

public class CheckoutPage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader;
	
	By gotIt_btn=By.linkText("Got it!");
	By addToCart_btn=By.cssSelector(".col-md-3:nth-child(2) .addToCart");
	By shoppingcart_dropdown=By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]");
	By checkout_btn=By.linkText("Checkout");
	By proceedToCheckout_btn=By.cssSelector(".wc-proceed-to-checkout > a");
	By firstName=By.id("customer.firstName");
	By lastName=By.id("customer.lastName");
	By countryDropdown=By.id("customer.billing.country");
	By phoneNumber=By.id("customer.billing.phone");
	By shipToDifferentAddress_checkbox=By.xpath("//input[@id='shipToDeliveryAddress']");
	By stateDropdown= By.id("billingStateList");
	

	public CheckoutPage(WebDriver driver) {
		this.driver=driver;
		objConfigFileReader = new ConfigFileReader();
	}
	
	public void shipToDiffAdd() {

	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();
	  
	    getPause();
	   
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();
	    driver.findElement(By.id("customer.billing.country")).click();
	    
	    driver.findElement(By.xpath("//input[@id='shipToDeliveryAddress']")).click();

	}
	
	public void phoneNumberTestCase(String number) {
	
	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();
	  
	    getPause();
	   
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();
	    driver.findElement(By.id("customer.billing.country")).click();
	    
	    
	    driver.findElement(By.id("customer.billing.phone")).sendKeys(number);

	}
	
	public void textInLowerCase(String text) {
		getPause();
		
	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();
	  
	    getPause();
	   
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();
	    
	    driver.findElement(By.id("customer.firstName")).sendKeys(text);
	    driver.findElement(By.id("customer.lastName")).sendKeys(text);


		
	}
	
	public void textInUpperCase(String text) {
		
		getPause();
		
	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();
	  
	    getPause();
	   
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();
	    
	    driver.findElement(By.id("customer.firstName")).sendKeys(text);
	    driver.findElement(By.id("customer.lastName")).sendKeys(text);

	}
	
	public void getPause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setStateName(String stateName,String countryName ) {
		getPause();
	
	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();
	  
	   getPause();
	
		
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();
	    driver.findElement(By.id("customer.billing.country")).click();
	    
	    WebElement testDropDown = driver.findElement(By.id("customer.billing.country"));  
	    Select dropdown = new Select(testDropDown);  
	    dropdown.selectByVisibleText(countryName);  
	    getPause();
	    testDropDown.click();
	    
	    WebElement e = driver.findElement(By.id("billingStateList"));  
	    Select states = new Select(e);  
	    states.selectByVisibleText(stateName);  		
	}
	
	public void setCountryName(String countryName) {
		
		getPause();
		 
		driver.findElement(By.linkText("Got it!")).click();
	    driver.findElement(By.cssSelector(".col-md-3:nth-child(2) .addToCart")).click();  
	 
	    getPause();
	   
	    Actions action = new Actions(driver);
	    WebElement we = driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"));
	    action.moveToElement(we).moveToElement(driver.findElement(By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[3]/a[1]/span[1]"))).click().build().perform();
	    
	    driver.findElement(By.linkText("Checkout")).click();
	    driver.findElement(By.cssSelector(".wc-proceed-to-checkout > a")).click();  
		
		driver.findElement(By.id("customer.billing.country")).click();
		getPause();
	    WebElement testDropDown = driver.findElement(By.id("customer.billing.country"));  
	    Select dropdown = new Select(testDropDown);  
	    System.out.println(countryName);
	    dropdown.selectByVisibleText(countryName); 
	    testDropDown.click();
	}
	
}
