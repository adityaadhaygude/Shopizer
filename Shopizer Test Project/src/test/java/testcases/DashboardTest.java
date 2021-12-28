package testcases;

import org.testng.annotations.Test;
import pages.BaseClass;

public class DashboardTest extends BaseClass{ 

	@Test
	public void verifyMyAccountLink() {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");


		objDashboardPage.waitForElement(objDashboardPage.myAccountLink);
		objDashboardPage.assertText(objDashboardPage.myAccountLink,"myAccountLinkText");
		logger.info("Clicked on My Account Link");


		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}

	@Test 
	public void verifyBillingAndShippingInformationLink(){
	
		driver.get(objConfigFileReader.getString("signinPageURL"));
		objHomePage.clickBtn(objHomePage.cockies);
		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");

		objDashboardPage.waitForElement(objDashboardPage.billingAndShippingInformationLink);
		objDashboardPage.assertText(objDashboardPage.billingAndShippingInformationLink,"billingAndShippingInformationLinkText");
		logger.info("Clicked on Billing And Shipping Information Link ");
		objDashboardPage.clickElement(objDashboardPage.billingAndShippingInformationLink);

		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}


	@Test 
	public void verifyChangePasswordLink() {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");

		objDashboardPage.waitForElement(objDashboardPage.changePasswordLink);
		objDashboardPage.assertText(objDashboardPage.changePasswordLink,"changePasswordText");
		logger.info("Clicked on Change Password Link ");
		objDashboardPage.clickElement(objDashboardPage.changePasswordLink);


		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}


	@Test 
	public void verifyEditAddressLink(){
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");

		objDashboardPage.waitForElement(objDashboardPage.billingAndShippingInformationLink);
		objDashboardPage.assertText(objDashboardPage.billingAndShippingInformationLink,"billingAndShippingInformationLinkText");
		logger.info("Clicked on Billing And Shipping Information Link ");
		objDashboardPage.clickElement(objDashboardPage.billingAndShippingInformationLink);
		objDashboardPage.waitForElement(objDashboardPage.editAddressLink);
		objDashboardPage.assertText(objDashboardPage.editAddressLink,"editAddressText");
		logger.info("Clicked on Edit Address Link ");
		objDashboardPage.clickElement(objDashboardPage.editAddressLink);

		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}


	@Test
	public void verifyLogoutLink()  {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");    

		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}

	@Test
	public void verifyRecentOrdersLink() {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in ");  

		objDashboardPage.waitForElement(objDashboardPage.recentOrdersLink);
		objDashboardPage.assertText(objDashboardPage.recentOrdersLink, "recentOrdersText");
		logger.info("Clicked on Recent Orders Link");
		objDashboardPage.clickElement(objDashboardPage.recentOrdersLink);


		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	}



	@Test(dataProvider="dashboardData")
	public void verifyChangeAddressButton(String fName,String lName,String companyName,String streetAddress,String city,String country,String state,String postalCode,String phone,String userName,String password) {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in "); 

		objDashboardPage.waitForElement(objDashboardPage.billingAndShippingInformationLink);
		objDashboardPage.assertText(objDashboardPage.billingAndShippingInformationLink,"billingAndShippingInformationLinkText");
		logger.info("Clicked on Billing And Shipping Information Link ");
		objDashboardPage.clickElement(objDashboardPage.billingAndShippingInformationLink);

		objDashboardPage.waitForElement(objDashboardPage.editAddressLink);
		objDashboardPage.assertText(objDashboardPage.editAddressLink,"editAddressText");
		logger.info("Clicked on Edit Address Link ");
		objDashboardPage.clickElement(objDashboardPage.editAddressLink);
		objDashboardPage.getWebElement(objDashboardPage.firstNameField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.firstNameField, fName);
		objDashboardPage.getWebElement(objDashboardPage.lastNameField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.lastNameField, lName);
		objDashboardPage.getWebElement(objDashboardPage.companyField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.companyField, companyName);
		objDashboardPage.getWebElement(objDashboardPage.addressField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.addressField, streetAddress); 
		objDashboardPage.getWebElement(objDashboardPage.cityField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.cityField, city);
		objDashboardPage.sendDataToField(objDashboardPage.countryField,country);
		objDashboardPage.sendDataToField(objDashboardPage.stateField,state);
		objDashboardPage.getWebElement(objDashboardPage.postalCodeField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.postalCodeField,postalCode);
		objDashboardPage.getWebElement(objDashboardPage.contactNoField).clear();
		objDashboardPage.sendDataToField(objDashboardPage.contactNoField,phone);

		objDashboardPage.waitForElement(objDashboardPage.changeAddressBtn);
		objDashboardPage.clickElement(objDashboardPage.changeAddressBtn);


		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	} 

	@Test(dataProvider="dashboardData")
	public void verifyChangePasswordButton(String fName,String lName,String companyName,String streetAddress,String city,String country,String state,String postalCode,String phone,String userName,String password) {
		driver.get(objConfigFileReader.getString("signinPageURL"));

		logger.info("Asserted Page title");
		objDashboardPage.assertTitle();

		objDashboardPage.sendDataToElement(objDashboardPage.userNameField,("userName"));
		objDashboardPage.sendDataToElement(objDashboardPage.passwordField,("userPassword"));
		logger.info("Successfully Entered User Data(Username/Password)");
		logger.info("Clicked on Log in button");
		objDashboardPage.clickElement(objDashboardPage.logInBtn);
		logger.info("Sucessfully Logged in "); 

		objDashboardPage.waitForElement(objDashboardPage.changePasswordLink);
		objDashboardPage.assertText(objDashboardPage.changePasswordLink,"changePasswordText");
		logger.info("Clicked on Change Password Link ");
		objDashboardPage.clickElement(objDashboardPage.changePasswordLink);

		objDashboardPage.sendDataToField(objDashboardPage.currentPasswordField,password);
		objDashboardPage.sendDataToField(objDashboardPage.newPasswordField,password);
		objDashboardPage.sendDataToField(objDashboardPage.repeatPasswordField,password);

		objDashboardPage.waitForElement(objDashboardPage.submitPasswordBtn);
		objDashboardPage.clickElement(objDashboardPage.submitPasswordBtn);

		objDashboardPage.waitForElement(objDashboardPage.customerAccount);
		objDashboardPage.mouseHoverOnElement(objDashboardPage.customerAccount);	
		logger.info("Clicked on logout button");
		objDashboardPage.clickElement(objDashboardPage.logoutLink);
		logger.info("Sucessfully Logged out");
	} 
}
