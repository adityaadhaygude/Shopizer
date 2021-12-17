package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
	WebDriver driver;
	
	public By proceedToCheckout=By.cssSelector(".wc-proceed-to-checkout > a:nth-child(1)");
	public By quantity=By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr[1]/td[2]/input");
	public By remove=By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[5]/div/a/i");
	public By recalculate=By.cssSelector(".buttons-cart > a:nth-child(1)");
	public By continueShopping=By.cssSelector(".buttons-cart > a:nth-child(2)");
	
	public CartPage(WebDriver driver){
		this.driver=driver;
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void clickBtn(By elementName) {
		getWebElement(elementName).click();
	}
	
	public WebElement getWebElement(By elementName) {
		return driver.findElement(elementName);
	}
			
	public void inputText(By elementName,String data){
		getWebElement(elementName).sendKeys(data);
	}
	
	public void clearBtn(By elementName) {
		driver.findElement(elementName).clear();
	}
}