package testcases;

import org.testng.annotations.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BaseClass;

public class RegistrationTest extends BaseClass{
	String textAssert;
	@Test
	public void TC1_verifyAllFieldsPresent()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		textAssert=objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(textAssert);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.firstName);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.lastName);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.country);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.state);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.email);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.password);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyFieldPresent(objRegistrationPage.repeatPassword);
		logger.info(textAssert);
		logger.info("All fields are present");
	}

	@Test
	public void TC3_verifyAllLabels()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		textAssert=objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(textAssert);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.firstNameTitle, "firstNameTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.lastNameTitle, "lastNameTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.countryTitle, "countryTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.stateTitle, "stateTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.emailTitle, "emailTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.passwordTitle, "passwordTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.repeatPasswordTitle, "repeatPasswordTitle");
		logger.info(textAssert);
		textAssert=objRegistrationPage.getTextAndAssert(objRegistrationPage.createAnAccountTitle, "createAccountTitle");
		logger.info(textAssert);
		logger.info("All labels are verified");
	}
	
	@Test
	public void TC4_verifyPageLoadsWithoutData()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Navigated to "+objConfigFileReader.getString("homePageURL"));
		textAssert=objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(textAssert);
		objHomePage.clickBtn(objHomePage.register);
		logger.info("Clicked on Register Button");
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.firstName);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.lastName);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.state);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.email);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.password);
		logger.info(textAssert);
		textAssert=objRegistrationPage.verifyEmpty(objRegistrationPage.repeatPassword);
		logger.info(textAssert);
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
		textAssert=objRegistrationPage.verifyAccountLogin(fName);
		logger.info(textAssert+" "+fName);
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
		textAssert=objRegistrationPage.verifyAccountLogin("TestFN");
		logger.info(textAssert+" TestFN");
	}
	
	@Test
	public void TC14_verifyPasswordsMismatchError()
	{
		driver.get(objConfigFileReader.getString("registrationPageURL"));
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
