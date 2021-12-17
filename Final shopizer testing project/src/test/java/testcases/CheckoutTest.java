package testcases;

import org.testng.annotations.Test;
import pages.BaseClass;

public class CheckoutTest extends BaseClass {

	// test method to Verify wheather the selected country is selected in country field
	@Test
	public void CountryDropDownTestCase() {

		logger.info("Country Drop Down Test Case has started");
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Moving to the checkout page");
		objCheckoutPage.setCountryName(objConfigFileReader.getString("countryName"));
		logger.pass("Country Selected Sucessfully");
	}

	// test method to Verify wheather the selected state is selected in state field	
	@Test
	public void stateDropDownTestCase() {
		logger.info("State Drop Down Test Case has started");
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Moving to the checkout page");
		objCheckoutPage.setStateName(objConfigFileReader.getString("stateName"),objConfigFileReader.getString("countryName")); 
		logger.pass("State Selected Sucessfully");
	}

	// test method to Verify wheather User can enter the First Name  & Last Name only in Upper Case 
	@Test
	public void upperCaseTestCase() {
		logger.info("FirstName in Upper Case test has started ");
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Moving to the checkout page");
		objCheckoutPage.textInUpperCase(objConfigFileReader.getString("firstName")); 
		logger.pass("Text in Upper Case Passed Sucessfully");

	}

	// test method to Verify wheather User can enter the First Name & Last Name only in Lower Case 
	@Test
	public void lowerCaseTestCase() {
		logger.info("FirstName in Lower Case test has started ");
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Moving to the checkout page");
		objCheckoutPage.getPause();
		objCheckoutPage.textInLowerCase(objConfigFileReader.getString("lowercase")); 
		logger.pass("Text in Lower Case Passed Sucessfully");

	}

	// test method to Verify wheather Ship to Different address checkbox is clickable or not 
	@Test
	public void shipToDifferentAddTC() {
		logger.info("Ship to Diff  Addr test case has started");
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Moving to the checkout page");
		objCheckoutPage.getPause();
		objCheckoutPage.shipToDiffAdd();
		logger.pass("Ship to Diff Addr Case Passed Sucessfully");

	}
}
