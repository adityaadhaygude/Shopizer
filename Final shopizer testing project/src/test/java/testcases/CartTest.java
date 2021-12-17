package testcases;

import org.testng.annotations.Test;
import pages.BaseClass;

public class CartTest extends BaseClass {

	@Test (priority=1, description = "Modify quantity present in the cart")
	public void TC1_modifyQuantity() {				
		driver.get(objConfigFileReader.getString("homePageURL"));
		objHomePage.clickBtn(objHomePage.item1);

		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		objHomePage.clickBtn(objHomePage.item2);

		driver.navigate().to(objConfigFileReader.getString("cartPageURL"));
		objCartPage.clearBtn(objCartPage.quantity);
		objCartPage.clickBtn(objCartPage.quantity);
		objCartPage.inputText(objCartPage.quantity, "2");
		objCartPage.clickBtn(objCartPage.quantity);
		logger.info("Item modified from 1 to 2");
	}

	@Test (priority=2, description = "Checking Recalculate Button Functionality")
	public void TC2_checkRecalculateButton() {
		driver.get(objConfigFileReader.getString("cartPageURL"));
		objCartPage.clearBtn(objCartPage.quantity);
		objCartPage.clickBtn(objCartPage.quantity);
		objCartPage.inputText(objCartPage.quantity, "4");
		objCartPage.clickBtn(objCartPage.quantity);
		logger.info("Item modified from 1 to 4");
		objCartPage.clickBtn(objCartPage.recalculate);
		logger.info("The total amount gets updated");
	}

	@Test (priority=3, description = "Checking continue button functionality")
	public void TC3_checkContinueShopping() {
		driver.get(objConfigFileReader.getString("cartPageURL"));
		objCartPage.clickBtn(objCartPage.continueShopping);
		logger.info("Clicked on continue shopping navigated to home Page to add more products");
	}

	@Test (priority=4, description = "Check that order can be confirmed and checked")
	public void TC4_orderConfirmCheckout() {
		driver.get(objConfigFileReader.getString("cartPageURL"));
		logger.info("Navigated to Cart");
		objCartPage.clickBtn(objCartPage.proceedToCheckout);
	}
	
	@Test (priority=5, description = "Cancel Item present in cart")
	public void TC5_cancelItem() {
		driver.get(objConfigFileReader.getString("cartPageURL"));
		objCartPage.clickBtn(objCartPage.remove);
		logger.info("Item got canceled");
	}

}