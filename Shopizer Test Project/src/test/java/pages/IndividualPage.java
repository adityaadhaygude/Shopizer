package pages;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utility.ConfigFileReader;

public class IndividualPage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader;
	
	//Finding webelements
	
	public By indivTitle = By.tagName("title");
	public By text = By.cssSelector(".col-md-3:nth-child(2)");
	public By productName = By.cssSelector("div.container:nth-child(16) div.all-hyperion-page div.container div.row div.col-lg-9.col-md-9.col-sm-12.col-xs-12:nth-child(2) div.product-simple-area.ptb-80.ptb-40-md.ptb-20-xs:nth-child(1) div.row div.col-lg-7.col-md-5.col-sm-5.col-xs-12 div.product-simple-content div.sinple-c-title:nth-child(1) > h1:nth-child(1)");
	public By productImage = By.tagName("img");
	public By originalPrice = By.cssSelector("div.container:nth-child(16) div.all-hyperion-page div.container div.row div.col-lg-9.col-md-9.col-sm-12.col-xs-12:nth-child(2) div.product-simple-area.ptb-80.ptb-40-md.ptb-20-xs:nth-child(1) div.row div.col-lg-7.col-md-5.col-sm-5.col-xs-12 div.product-simple-content h4:nth-child(5) span:nth-child(1) span.price:nth-child(3) > span:nth-child(1)");
	public By addToCart = By.cssSelector("div.container:nth-child(16) div.all-hyperion-page div.container div.row div.col-lg-9.col-md-9.col-sm-12.col-xs-12:nth-child(2) div.product-simple-area.ptb-80.ptb-40-md.ptb-20-xs:nth-child(1) div.row div.col-lg-7.col-md-5.col-sm-5.col-xs-12 div.product-simple-content form.options-form:nth-child(9) div.store-btn.form-inline:nth-child(2) > button.btn.addToCart.addToCartButton.btn-buy");
	public By productHeader = By.linkText("Vintage laptop bag");
	public By productTitle = By.cssSelector("h1");
	public By ratingBtn = By.cssSelector("#productRating > img:nth-child(4)");	
		
	//constructor
		public IndividualPage(WebDriver driver){
			this.driver = driver;
			objConfigFileReader = new ConfigFileReader();
		}
		
		public String getPageTitle() {
			return driver.getTitle();
		}
		//Assert home page title
		public void assertTitle(){
			objConfigFileReader = new ConfigFileReader();
			assertThat(getPageTitle(),is(objConfigFileReader.getString("indivTitle")));
			{
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.presenceOfElementLocated(indivTitle));
			}
		}	
		
		//This method hover mouse on the element called during invocation.
		public void mouseHoverOnElement1(By elementName) {
				WebElement element = getWebElement(elementName);
				Actions builder = new Actions(driver);
				builder.moveToElement(element).perform();
		}
		
		
	
		//This method is used to click the button of element passed during invocation.
		public void clickBtn(By elementName) {
			getWebElement(elementName).click();
		}
		
		
		//This method is used to get the text and assert.
		public void getTextAndAssert(By elementName,String stringName) {
			 assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
		}
		
		//To get particular css property this method is used. It will return property in string format.
		public String verifyCssProperty(By elementName,String propertyName) {
			String cssPropertyText = getWebElement(elementName).getCssValue(objConfigFileReader.getString(propertyName));
			return cssPropertyText;	
		}
		
		public WebElement getWebElement(By elementName) {
			return driver.findElement(elementName);
		}
		
		public void ratingButtonIsEditable(By elementName) {
			 WebElement element = driver.findElement(elementName);    
		     Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
		     assertTrue(isEditable);
		}
}
