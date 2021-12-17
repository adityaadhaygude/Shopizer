package pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigFileReader;

public class HomePage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader;

	//Finding webelements
	public By cockies = By.xpath("/html/body/div[1]/div/a");
	public By homePageImage = By.cssSelector(".image-content > img");
	public By frenchXpath = By.xpath("//a/span ");
	public By frenchBtn = By.linkText("French");
	public By englishBtn = By.linkText("Anglais");
	public By englishXpath = By.linkText("Page d\'accueil");
	public By homeBtn = By.linkText("Home");
	public By products = By.linkText("Products");
	public By handBags = By.linkText("Handbags");
	public By handBagsText = By.cssSelector(".shop-banner-title");
	public By myAccount = By.xpath("//li[2]/a/span");
	public By signInBtn = By.partialLinkText("Sign");
	//public By signInBtn = By.id("registerLink");
	public By registerBtn = By.linkText("Register");
	public By register=By.partialLinkText("Register");
	public By cartBtn = By.xpath("//li[3]/a/span");
	public By productName = By.cssSelector(".col-md-3:nth-child(1) h3");
	public By originalPrice = By.cssSelector("del");
	public By discountedPrice = By.cssSelector(".specialPrice");
	public By beachBags = By.partialLinkText("Beach");
	public By beachBagsText = By.cssSelector(".shop-banner-title");
	public By laptopBags = By.partialLinkText("Laptop");
	public By laptopBagsText = By.cssSelector(".shop-banner-title");
	public By bags = By.linkText("Bags");
	public By bagsText = By.cssSelector(".shop-banner-title");
	public By email = By.xpath("//strong[contains(.,'john@test.com')]");
	public By phone = By.xpath("//strong[contains(.,'888-888-8888')]");
	public By registerPageText = By.cssSelector(".login-title:nth-child(1) > h3");
	public By signInPageText = By.cssSelector(".col-lg-6:nth-child(1) h3");
	public By page = By.linkText("Page");
	public By addToCartBtn = By.linkText("Add to cart");
	public By username = By.id("signin_userName");
	public By password = By.id("signin_password");
	public By loginBtn = By.id("genericLogin-button");
	public By myAcc = By.linkText("My Account");
	//	public By logoutBtn = By.linkText("Logout");
	public By logoutBtn = By.cssSelector(".w-150 > li:nth-child(2) > a:nth-child(1)");
	public By cartQuantity = By.id("miniCartSummary");
	public By removeBtn = By.xpath("//*[@id=\"miniCartDetails\"]/li[1]/div/ol/li/div[2]/div/button");
	public By checkoutBtn = By.xpath("//*[@id=\"miniCartDetails\"]/li[2]/div/a[2]");
	public By checkoutAssertText = By.tagName("h2");
	//public By totalText = By.xpath("//thead/tr/th[4]");
	public By totalText = By.tagName("h2");
	public By myAccountForLogout = By.xpath("//li[2]/a/span");

	//Avinee elements
	public By item1=By.cssSelector("#featuredItemsContainer > div:nth-child(1) > div.product-content.text-center > div.store-btn > div > a");
	public By item2=By.cssSelector("#featuredItemsContainer > div:nth-child(3) > div.product-content.text-center > div.store-btn > div > a");
	public By shoppingCart=By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/a");
	public By checkout=By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/ul/span/li[2]/div/a[2]");

	//Aditya jadhav elements
	public By collection1 = By.cssSelector("div.container:nth-child(19) div.bedroom-all-product-area.ptb-80 div.container div.row div.col-lg-3.col-md-3.col-sm-3.col-xs-12 ul.nav.nav-list:nth-child(4) li:nth-child(2) > a:nth-child(1)");
	public By checkoutTable = By.xpath("//td");

	//constructor
	public HomePage(WebDriver driver){
		this.driver = driver;
		objConfigFileReader = new ConfigFileReader();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String assertTitle(String titleName){
		objConfigFileReader = new ConfigFileReader();
		try{
			assertThat(getTitle(), is(objConfigFileReader.getString(titleName)));
			{
				WebDriverWait wait = new WebDriverWait(driver, 15);
				wait.until(ExpectedConditions.presenceOfElementLocated(homePageImage));
			}
		}catch(AssertionError e) {
			return e.getMessage();
		}
		return objConfigFileReader.getString("successMessage");
	}

	//This method assert the image present on the home page of the shopizer.
	public String assertHomePageImage() {
		List<WebElement> elements =  driver.findElements(homePageImage);
		try {
			assert(elements.size() > 0);
		}catch(AssertionError e) {
			return e.getMessage();
		}
		return objConfigFileReader.getString("successMessageForImageAssertion");
	}

	//This method hover mouse on the element called during invocation.
	public String mouseHoverOnElement(By elementName) {
		try {
			WebElement element = getWebElement(elementName);
			Actions builder = new Actions(driver);
			builder.moveToElement(element).perform();
		}catch(AssertionError e) {
			return e.getMessage();	
		}
		return objConfigFileReader.getString("successMessageForMouseHover");
	}

	//This method is used to click the button of element passed suring invocation.
	public void clickBtn(By elementName) {
		getWebElement(elementName).click();
	}

	public String getTextAndAssert(By elementName,String stringName) {
		try {
			assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
		}catch(AssertionError e) {
			return e.getMessage();
		}
		return objConfigFileReader.getString("successMessageForTextAssertion");
	}

	//To get particular css property this method is used. It will return property in string format.
	public String verifyCssProperty(By elementName,String propertyName) {
		String cssPropertyText = getWebElement(elementName).getCssValue(objConfigFileReader.getString(propertyName));
		return cssPropertyText;	
	}

	public WebElement getWebElement(By elementName) {
		return driver.findElement(elementName);
	}

	public Boolean isClickEnabled(By elementName) {
		return getWebElement(elementName).isEnabled();
	}

	public String getAttribute(By elementName,String attributeName) {
		String checkBrocken = getWebElement(elementName).getAttribute(objConfigFileReader.getString(attributeName));
		try {
			assert(checkBrocken.equalsIgnoreCase(objConfigFileReader.getString("pageBrockenLink")));
		}catch(AssertionError  e) {
			return objConfigFileReader.getString("failureMessageForGetAttribute");
		}
		return objConfigFileReader.getString("successMessageForGetAttribute");
	}

	//This method returns the quantity of the cart products.
	public int verifyItemInCart(By elementName) {
		String quantity =  getWebElement(elementName).getText();
		return Integer.parseInt(quantity);

	}

	//To click js driven buttons this method is used.
	public void jsClickBtn(By elementName) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].click()", getWebElement(elementName));  
	}


	//To perform logout functionality user must be signed in using this method.
	public void signIn() {
		getWebElement(username).sendKeys(objConfigFileReader.getString("user"));
		getWebElement(password).sendKeys(objConfigFileReader.getString("pass"));
		getWebElement(loginBtn).click();
	}

	public void waitForElementPresent(By elementName) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(elementName));
	}
	//methods by AJ
	public void clickProduct()
	{
		By category1=By.linkText(objConfigFileReader.getString("handbags"));
		driver.findElement(category1).click();
	}
	//method to select chic collection
	public void clickCollection()
	{
		driver.findElement(collection1).click();
	}
}
