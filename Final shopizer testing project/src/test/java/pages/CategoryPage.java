package pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ConfigFileReader;

public class CategoryPage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader = new ConfigFileReader();
	//Finding path to the elements
	public By hbItem1 = By.cssSelector(".col-md-4:nth-child(1) h3");
	public By hbItem2 = By.cssSelector(".col-md-4:nth-child(2) h3");
	public By hbItem3 = By.cssSelector(".col-md-4:nth-child(3) h3");
	public By hbItem4 = By.cssSelector(".col-md-4:nth-child(4) h3");
	public By hbItem5 = By.cssSelector(".col-md-4:nth-child(5) h3");
	public By hbItem6 = By.cssSelector(".col-md-4:nth-child(6) h3");
	public By chicItem1 = By.cssSelector(".col-md-4:nth-child(1) h3");
	public By chicItem2 = By.cssSelector(".col-md-4:nth-child(2) h3");
	public By viewItem1 = By.cssSelector("#productsContainer > .col-md-4:nth-child(1) h3");
	public By addToCartBtn = By.linkText("Add to cart");
	public By collection1 = By.linkText("Chic");
	public By cartItem1 = By.cssSelector(".product-name > a");
	public By cart = By.xpath("//li[3]/a/span");
	public By sortDropdown = By.xpath("//*[@id=\"filter\"]");
	public By sortName = By.xpath("//option[. = 'Name']");
	public By sortPrice = By.xpath("//option[. = 'Price']");
	public By sortedItem1 = By.cssSelector(".col-md-4:nth-child(1) h3");
	public By sortedItem2 = By.cssSelector(".col-md-4:nth-child(2) h3");
	public By sortedItem3 = By.cssSelector(".col-md-4:nth-child(3) h3");
	public By sortedItem4 = By.cssSelector(".col-md-4:nth-child(4) h3");
	public By sortedItem5 = By.cssSelector(".col-md-4:nth-child(5) h3");
	public By sortedItem6 = By.cssSelector(".col-md-4:nth-child(6) h3");
	public By sortedPrice1 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[1]/div[2]/h4[1]/span[1]");
	public By sortedPrice2 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[2]/div[2]/h4[1]/span[1]");
	public By sortedPrice3 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[3]/div[2]/h4[1]/span[1]");
	public By sortedPrice4 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[4]/div[2]/h4[1]/span[1]");
	public By sortedPrice5 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[5]/div[2]/h4[1]/span[1]");
	public By sortedPrice6 = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/section[1]/div[1]/div[6]/div[2]/h4[1]/span[1]");
	
	public CategoryPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void getTextAndAssert(By elementName,String stringName) 
	{
		assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
	}
	
	public void clickBtn(By elementName) 
	{
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(elementName));
		getWebElement(elementName).click();
	}
	
	public void jsClickBtn(By elementName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].click()", getWebElement(elementName));  
	}
	
	//method to assert all elements under handbags category
	public void categoryElements()
	{
		getTextAndAssert(hbItem1, "handbagsTitle1");
		getTextAndAssert(hbItem2, "handbagsTitle2");
		getTextAndAssert(hbItem3, "handbagsTitle3");
		getTextAndAssert(hbItem4, "handbagsTitle4");
		getTextAndAssert(hbItem5, "handbagsTitle5");
		getTextAndAssert(hbItem6, "handbagsTitle6");
	}
	
	//method to assert all elements under chic collection
	public void collectionElements()
	{
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(chicItem1));
		getTextAndAssert(chicItem1, "chicTitle1");
		getTextAndAssert(chicItem2, "chicTitle2");
	}
	
	public String getPageTitle() 
	{
		return driver.getTitle();
	}
	
	//method to assert the title of page
	public void assertTitle()
	{
		assertThat(getPageTitle(), is(objConfigFileReader.getString("vcbTitle")));
	}
	
	public String getPageURL()
	{
		return driver.getCurrentUrl();
	}
	
	//method to assert the URL of page
	public void checkURL()
	{
		assertThat(getPageURL(), is(objConfigFileReader.getString("vcbURL")));
	}
	
	//method to assert product added to cart
	public void assertCart()
	{
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
		jsClickBtn(cart);
		getTextAndAssert(cartItem1,"cartItemTitle");
	}
	
	public void selectSortByName()
	{
		clickBtn(sortDropdown);
		{
			WebElement dropdown = driver.findElement(By.id("filter"));
		    dropdown.findElement(sortName).click();
		}
	}
	
	//method to sort elements by name and assert
	public void sortedElementsByName()
	{
		selectSortByName();
		List<String> sortedList=new ArrayList<String>();
		sortedList.add(driver.findElement(sortedItem1).getText());
		sortedList.add(driver.findElement(sortedItem2).getText());
		sortedList.add(driver.findElement(sortedItem3).getText());
		sortedList.add(driver.findElement(sortedItem4).getText());
		sortedList.add(driver.findElement(sortedItem5).getText());
		sortedList.add(driver.findElement(sortedItem6).getText());
		List<String> expectedList=sortedList;
		Collections.sort(expectedList);
		assert(expectedList.equals(sortedList));		
	}
	
	public void selectSortByPrice() 
	{	
	
			WebElement dropdown = driver.findElement(By.id("filter"));
		    dropdown.findElement(sortPrice).click();
		
	}
	
	//method to sort elements by price and assert
	public void sortedElementsByPrice()
	{
		selectSortByPrice();
		
		List<Float> sortedList=new ArrayList<Float>();
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice1).getText().substring(1)));
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice2).getText().substring(1)));
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice3).getText().substring(1)));
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice4).getText().substring(1)));
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice5).getText().substring(1)));
		sortedList.add(Float.parseFloat(driver.findElement(sortedPrice6).getText().substring(1)));
		List<Float> expectedList=sortedList;
		Collections.sort(expectedList);
		assert(expectedList.equals(sortedList));		
	}
	
	public WebElement getWebElement(By elementName) 
	{
		return driver.findElement(elementName);
	}
}
