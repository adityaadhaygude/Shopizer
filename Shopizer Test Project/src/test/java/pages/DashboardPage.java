package pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utility.ConfigFileReader;



public class DashboardPage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader;
	WebDriverWait wait;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public By cookieBtn=By.className("cc-btn");
	public By customerAccount =By.id("customerAccount");
	public By signInBtn=By.linkText("Sign in");
	public By userNameField=By.id("signin_userName");
	public By passwordField=By.id("signin_password");
	public By logInBtn=By.id("genericLogin-button");
	public By myAccountLink=By.linkText("My Account");
	public By billingAndShippingInformationLink =By.linkText("Billing & shipping information");
	public By changePasswordLink=By.linkText("Change password");
	public By editAddressLink=By.linkText("Edit");
	public By logoutLink=By.linkText("Logout");
	public By recentOrdersLink=By.linkText("Recent orders");
	public By firstNameField=By.id("firstName");
	public By lastNameField=By.id("lastName");
	public By companyField=By.id("company");
	public By addressField=By.id("address");
	public By cityField=By.id("city");
	public By countryField=By.name("country");
	public By stateField=By.id("customer_zones");
	public By postalCodeField=By.id("billingPostalCode");
	public By contactNoField=By.id("phone");
	public By changeAddressBtn=By.id("submitAddress");
	public By currentPasswordField=By.id("currentPassword");
	public By newPasswordField=By.id("password");
	public By repeatPasswordField=By.id("checkPassword");
	public By submitPasswordBtn=By.id("submitChangePassword");

	public void waitForElement(By elementName) {
		wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(elementName));
	}
	
	public void mouseHoverOnElement(By elementName) {
		WebElement element = getWebElement(elementName);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();
	}
	
	public void clickElement(By elementName) {
		getWebElement(elementName).click();
		
	}
	
	public void sendDataToElement(By elementName,String attributeName) {
		objConfigFileReader=new ConfigFileReader();
	
		getWebElement(elementName).sendKeys(objConfigFileReader.getString(attributeName));
	}
	
	
	public void sendDataToField(By elementName,String attributeName) {
	
		getWebElement(elementName).sendKeys(attributeName);
	}
	
	public WebElement getWebElement(By elementName) {
		return driver.findElement(elementName);
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	public void assertTitle(){
		objConfigFileReader = new ConfigFileReader();
		
		assertThat(getPageTitle(), is(objConfigFileReader.getString("homeTitle")));
		
	}
	
	public void assertText(By elementName,String stringName) {
		 assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
	}
	
	
	
	
}
