package pages;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utility.ConfigFileReader;

public class SigninPage {
	
			WebDriver driver;
			ConfigFileReader objConfigFileReader;
			
			public By email=By.id("signin_userName");
			public By password=By.id("signin_password");
			public By SignIN=By.cssSelector(".login-btn");
			public By SignInErrors=By.id("loginError");
			public By Register = By.cssSelector(".login-btn");		
			
			public By emailTitle=By.cssSelector(".form-group:nth-child(1)");
			public By passwordTitle = By.cssSelector(".form-group:nth-child(2)");
			public By signInTitle=By.cssSelector(".login-btn");
			public By registerTitle=By.cssSelector("body > div.login-area.ptb-80 > div > div > div:nth-child(2) > a");
			public By passwordMismatchError=By.id("customer.errors");
			public By signInErrors=By.id("customer.errors");
			
			public SigninPage(WebDriver driver)
			{
				this.driver=driver;
				objConfigFileReader = new ConfigFileReader();
			}
			
			//method for verifying if all fields are present 
			public void verifyAllFieldsPresent(By elementName)
			{
				
				List<WebElement> Elements = driver.findElements(elementName);
				assert(Elements.size()>0);
				
			}
			
			//method for get text and assert
			public void textAndAssert(By elementName, String stringName) {
				assertThat(getWebElement(elementName).getText(),is(objConfigFileReader.getString(stringName)));
			}
			
			//method to get web element
			public WebElement getWebElement(By elementName) {
				return driver.findElement(elementName);
			}
			
			//method for click button
			public void clickBtn(By elementName) {
				getWebElement(elementName).click();
			}
			
			//method for input text
			public void inputText(By elementName, String s) {
				getWebElement(elementName).sendKeys(s);
			}
			
			//method for keyboard input
			public void keyboardInput(By elementName, Keys k) {
				getWebElement(elementName).sendKeys(k);
			}
			
			//method to verify if the field is empty
			public void verifyEmpty(By elementName) {
				String content = getWebElement(elementName).getAttribute("value");
				assertTrue(content.isEmpty());
			}
				
		}
			


