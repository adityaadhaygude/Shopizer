package pages;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.ConfigFileReader;

public class RegistrationPage {
	WebDriver driver;
	ConfigFileReader objConfigFileReader;

	public By firstName=By.id("firstName");
	public By lastName=By.id("lastName");
	public By country=By.id("registration_country");
	public By state=By.id("hidden_zones");
	public By email=By.id("emailAddress");
	public By password=By.id("password");
	public By repeatPassword=By.id("passwordAgain");
	public By createAnAccount=By.cssSelector("div.login-area.ptb-80:nth-child(20) div.container div.row div.col-lg-6.col-md-6.col-sm-8.col-xs-12:nth-child(1) div.login-form form:nth-child(1) > button.btn.btn-default.login-btn:nth-child(9)");
	public By myAccount=By.id("genericLogin-button");
	public By accountName=By.xpath("/html[1]/body[1]/nav[1]/div[1]/ul[2]/li[2]/a[1]/span[1]");
	public By firstNameTitle=By.cssSelector(".form-group:nth-child(1) > label");
	public By lastNameTitle=By.cssSelector(".form-group:nth-child(2) > label");
	public By countryTitle=By.cssSelector(".form-group:nth-child(3) > label");
	public By stateTitle=By.cssSelector(".form-group:nth-child(4) > label");
	public By emailTitle=By.cssSelector(".form-group:nth-child(6) > label");
	public By passwordTitle=By.cssSelector(".form-group:nth-child(7) > label");
	public By repeatPasswordTitle=By.cssSelector(".form-group:nth-child(8) > label");
	public By createAnAccountTitle=By.cssSelector(".login-btn");
	public By passwordMismatchError=By.id("customer.errors");
	public By registrationError=By.id("customer.errors");

	//Initializes Registration page and config file reader.
	public RegistrationPage(WebDriver driver)
	{
		this.driver=driver;
		objConfigFileReader = new ConfigFileReader();
	}

	//Verifies if a field is present by accepting element
	public void verifyFieldPresent(By elementName)
	{
		List<WebElement> Elements=driver.findElements(elementName);
		assert(Elements.size() > 0);
	}

	// Verifies text from the element
	public void getTextAndAssert(By elementName,String stringName) {
		assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
	}

	//Returns web element
	public WebElement getWebElement(By elementName) {
		return driver.findElement(elementName);
	}

	// Clicks on the element
	public void clickBtn(By elementName) {
		getWebElement(elementName).click();
	}

	// Enters text in given element
	public void inputText(By elementName,String data)
	{
		getWebElement(elementName).sendKeys(data);
	}

	//Passes keyboard inputs to the element

	public void keyboardInput(By elementName,Keys k)
	{
		getWebElement(elementName).sendKeys(k);
	}

	// Verifies if element is empty
	public void verifyEmpty(By elementName)
	{
		String content=getWebElement(elementName).getAttribute("value");
		assertTrue(content.isEmpty());
	}

	//Selects option from dropdown
	public void selectFromDropDown(By elementName,String country)
	{
		getWebElement(elementName).findElement(By.xpath("//option[. = '"+country+"']")).click();
	}

	// Verifies if account is logged in for a particular user	 
	public void verifyAccountLogin(String name)
	{
		assertThat(driver.findElement(accountName).getText(),is("Welcome "+name));
	}
}
