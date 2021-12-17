package testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BaseClass;

public class RegistrationTest extends BaseClass{
	@Test
	public void TC1_verifyAllFieldsPresent()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.firstName);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.lastName);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.country);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.state);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.email);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.password);
		objRegistrationPage.verifyFieldPresent(objRegistrationPage.repeatPassword);
		logger.info("All fields are present");
	}

	@Test
	public void TC3_verifyAllLabels()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.firstNameTitle, "firstNameTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.lastNameTitle, "lastNameTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.countryTitle, "countryTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.stateTitle, "stateTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.emailTitle, "emailTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.passwordTitle, "passwordTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.repeatPasswordTitle, "repeatPasswordTitle");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.createAnAccountTitle, "createAccountTitle");
		logger.info("All labels are verified");
	}
	
	@Test
	public void TC4_verifyPageLoadsWithoutData()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		objRegistrationPage.verifyEmpty(objRegistrationPage.firstName);
		objRegistrationPage.verifyEmpty(objRegistrationPage.lastName);
		objRegistrationPage.verifyEmpty(objRegistrationPage.state);
		objRegistrationPage.verifyEmpty(objRegistrationPage.email);
		objRegistrationPage.verifyEmpty(objRegistrationPage.password);
		objRegistrationPage.verifyEmpty(objRegistrationPage.repeatPassword);
		logger.info("Page loads without any data");
	}
	
	@Test
	public void TC5_verifyTabFunctionality()
	{
		WebElement current;
		driver.get(objConfigFileReader.getString("registrationPageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("registrationPageURL"));
		objRegistrationPage.inputText(objRegistrationPage.firstName,objConfigFileReader.getString("Test1"));
		logger.info("Clicked on first name and sent input:"+objConfigFileReader.getString("Test1"));
		objRegistrationPage.keyboardInput(objRegistrationPage.firstName, Keys.TAB);
		logger.info("Tab key input given");
		if(driver.switchTo().activeElement().equals(objRegistrationPage.getWebElement(objRegistrationPage.lastName)))
		{
			logger.info("Focus switched to LastName field");
		}  
		else
		{
			logger.info("Focus not switched to LastName field");
		}
		current=driver.switchTo().activeElement();
		current.sendKeys(objConfigFileReader.getString("Test2"));
		logger.info("Input sent to current object in focus:"+objConfigFileReader.getString("Test2"));
	}
	@Test
	public void TC6_registerWithoutData() 
	{  
		driver.get(objConfigFileReader.getString("registrationPageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("registrationPageURL"));
		objRegistrationPage.clickBtn(objRegistrationPage.createAnAccount);
		logger.info("Clicked on Create An Account");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.registrationError,"registrationError");
		logger.info("Registration error asserted");
	}
	
	@Test(dataProvider="registrationData")
	public void TC7_registrationWithValidData(String fName,String lName,String country,String state,String email,String password,String rPassword)
	{
		driver.get(objConfigFileReader.getString("registrationPageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("registrationPageURL"));
		objRegistrationPage.inputText(objRegistrationPage.firstName,fName);
		objRegistrationPage.inputText(objRegistrationPage.lastName,lName);
		objRegistrationPage.selectFromDropDown(objRegistrationPage.country, country);
		objRegistrationPage.inputText(objRegistrationPage.state,state);
		objRegistrationPage.inputText(objRegistrationPage.email,email);
		objRegistrationPage.inputText(objRegistrationPage.password, password);
		objRegistrationPage.inputText(objRegistrationPage.repeatPassword, rPassword);
		logger.info("Inputs given: "+fName+" "+lName+" "+country+" "+state+" "+email+" "+password);
		objRegistrationPage.clickBtn(objRegistrationPage.createAnAccount);
		logger.info("Clicked on Create An Account");
		objRegistrationPage.verifyAccountLogin(fName);
		logger.info("Account registered for "+fName);
	}
	
	@Test
	public void TC8_verifyEnterFunctionality()
	{
		driver.get(objConfigFileReader.getString("registrationPageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("registrationPageURL"));
		objRegistrationPage.inputText(objRegistrationPage.firstName,"TestFN");
		objRegistrationPage.inputText(objRegistrationPage.lastName,"TestLN");
		objRegistrationPage.selectFromDropDown(objRegistrationPage.country, "China");
		objRegistrationPage.inputText(objRegistrationPage.state,"TestState");
		objRegistrationPage.inputText(objRegistrationPage.email,"TestFN@gmail.com");
		objRegistrationPage.inputText(objRegistrationPage.password, "Test@password");
		objRegistrationPage.inputText(objRegistrationPage.repeatPassword, "Test@password");
		logger.info("Input data sent");
		objRegistrationPage.keyboardInput(objRegistrationPage.repeatPassword, Keys.ENTER);
		logger.info("Enter key pressed");
		objRegistrationPage.verifyAccountLogin("TestFN");
		logger.info("Account registered for TestFN");
	}
	
	@Test
	public void TC14_verifyPasswordsMismatchError()
	{
		driver.get(objConfigFileReader.getString("registrationPageURL"));
		objHomePage.clickBtn(objHomePage.cockies);
		logger.info("Navigated to "+objConfigFileReader.getString("registrationPageURL"));
		objRegistrationPage.inputText(objRegistrationPage.firstName,"TestFN2");
		objRegistrationPage.inputText(objRegistrationPage.lastName,"TestLN2");
		objRegistrationPage.selectFromDropDown(objRegistrationPage.country, "China");
		objRegistrationPage.inputText(objRegistrationPage.state,"TestState2");
		objRegistrationPage.inputText(objRegistrationPage.email,"function@gmail.com");
		objRegistrationPage.inputText(objRegistrationPage.password, "Test@password");
		objRegistrationPage.inputText(objRegistrationPage.repeatPassword, "Test@wrongpassword");
		logger.info("Different Password and Repeat password input given");
		objRegistrationPage.clickBtn(objRegistrationPage.createAnAccount);
		logger.info("Clicked on Create An Account");
		objRegistrationPage.getTextAndAssert(objRegistrationPage.passwordMismatchError,"passwordMismatchError");
		logger.info("Password mismatch error asserted");
	}
}
