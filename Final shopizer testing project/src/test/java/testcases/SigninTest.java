package testcases;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.BaseClass;

public class SigninTest extends BaseClass {


	//Test Case for verifying if all fields are present 
	@Test
	public void TC1_verifyAllFieldsPresent() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		objHomePage.clickBtn(objHomePage.cockies);
		logger.info("Mouse hovered on myAccount button");
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info("Clicked on the sign in button");
		objHomePage.clickBtn(objHomePage.signInBtn);
		driver.get(objConfigFileReader.getString("signinPageURL"));
		objSigninPage.verifyAllFieldsPresent(objSigninPage.email);
		objSigninPage.verifyAllFieldsPresent(objSigninPage.password);

	}

	//Test case for verifying if correct labels are displayed
	@Test
	public void TC2_verifyAllLabels() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovered on myAccount button");
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info("Clicked on the sign in button");
		objHomePage.clickBtn(objHomePage.signInBtn);
		driver.get(objConfigFileReader.getString("signinPageURL"));
		objSigninPage.textAndAssert(objSigninPage.emailTitle, "emailSigninTitle");   
		objSigninPage.textAndAssert(objSigninPage.passwordTitle, "passwordTitle");
		objSigninPage.textAndAssert(objSigninPage.signInTitle, "signInTitle");
		objSigninPage.textAndAssert(objSigninPage.registerTitle, "registerTitle");
		logger.info("textAssert");
	}

	//Test case for ensuring that the signInBtn Page loads without any data
	@Test
	public void TC3_verifySigninPageLoadsWithoutData() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovered on myAccount button");
		objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info("Clicked on the sign in button");
		objHomePage.clickBtn(objHomePage.signInBtn);
		driver.get(objConfigFileReader.getString("signinPageURL"));
		objSigninPage.verifyEmpty(objSigninPage.email);
		objSigninPage.verifyEmpty(objSigninPage.password);
		logger.info("verifyifEmpty");

	}

	//Test case for verifying the Tab functionality
	@Test
	public void TC4_verifyTabFunctionality() throws InterruptedException 
	{
		WebElement tab;
		driver.get(objConfigFileReader.getString("signinPageURL"));
		logger.info("Inserted the input text for email");
		objSigninPage.inputText(objSigninPage.email,objConfigFileReader.getString("Test1"));
		logger.info("Inserted the keyboard input Tab key");
		objSigninPage.keyboardInput(objSigninPage.email, Keys.TAB);
		if(driver.switchTo().activeElement().equals(objSigninPage.getWebElement(objSigninPage.password)))
			System.out.println("you can now enter your password");
		else
			System.out.println("You cannot enter your password");
		tab=driver.switchTo().activeElement();
		tab.sendKeys(objConfigFileReader.getString("Test2"));
	}

	//Test case for verifying that the user can signInBtn with valid data
	@Test(dataProvider="signinData")
	public void TC5_verifySigninWithValidData(String email,String password) {
		driver.get(objConfigFileReader.getString("signinPageURL"));
		logger.info("Inserted the input text for email");
		objSigninPage.inputText(objSigninPage.email,email);
		logger.info("Inserted the input text for password");
		objSigninPage.inputText(objSigninPage.password,password);
		logger.info("Clicked the sign in button");
		objSigninPage.clickBtn(objSigninPage.SignIN);
		driver.get(objConfigFileReader.getString("dashboardPageURL"));
	}

	//Test case for verifying the Enter functionality
	@Test(dataProvider="signinData")
	public void TC6_verifyEnterfunctionality(String email,String password) {
		driver.get(objConfigFileReader.getString("signinPageURL"));
		logger.info("Inserted the input text for email");
		objSigninPage.inputText(objSigninPage.email, email);
		logger.info("Inserted the input text for password");
		objSigninPage.inputText(objSigninPage.password, password);
		logger.info("Insert the Keyboard input ENTER key");
		objSigninPage.keyboardInput(objSigninPage.password, Keys.ENTER); //if else update
	}

	//Test case for verifying the Register Button
	@Test
	public void TC7_verifyTheRegisterButton() {
		driver.get(objConfigFileReader.getString("signinPageURL"));
		logger.info("clicked the register button");
		objSigninPage.clickBtn(objSigninPage.Register);
		driver.get(objConfigFileReader.getString("registrationPageURL"));

	}

	@Test(dataProvider="signinData")
	public void ddt(String email,String password)
	{
		System.out.println(email+password);
	}

}


