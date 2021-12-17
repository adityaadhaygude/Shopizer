package testcases;

import org.testng.annotations.Test;
import pages.BaseClass;

public class HomeTest extends BaseClass{
	int cartQuantity;
	String mouseHoverStatus,textAssert;
	
	@Test
	public void verifyTranslator() {		
		driver.get(objConfigFileReader.getString("homePageURL"));	
		
		logger.info("Test Case : Translating webpage from english to french and viceversa");
		
		logger.info("Asserting home page title");
		String assertionStatus = objHomePage.assertTitle("homeTitle");
		logger.info(assertionStatus);
		
		logger.info("Asserting image present on the home page");
		String imageAssertionStatus = objHomePage.assertHomePageImage();
		logger.info(imageAssertionStatus);
		
		logger.info("Mouse hovering on English button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.frenchXpath);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on the french button to translate the page into french");
		objHomePage.clickBtn(objHomePage.frenchBtn);
		
		logger.info("Asserting french text of home");
		textAssert=objHomePage.getTextAndAssert(objHomePage.englishXpath,"homeInFrench");
		logger.info(textAssert);
		
		logger.info("Successfully translated webpage from english to french and asserted");
		
		logger.info("Mouse hovering on French button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.frenchXpath);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on the english button to translate the page into english");
		objHomePage.clickBtn(objHomePage.englishBtn);
		
		logger.info("Asserting home text in english");
		textAssert = objHomePage.getTextAndAssert(objHomePage.homeBtn, "homeInEnglish");
		logger.info(textAssert);
		
		logger.info("Successfully translated webpage from french to english and asserted");
	}

	@Test
	public void verifyMouseHoverEffect() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Test Case : Verifying mouse hover effect on products, myaccount and cart");
		
		logger.info("Mouse hovering on products button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.products);
		logger.info(mouseHoverStatus);
		
		logger.info("Asserting handbags text in products dropdown menu");
		textAssert = objHomePage.getTextAndAssert(objHomePage.handBags, "handbags");
		logger.info(textAssert);
		
		logger.info("Mouse hovering on English button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.frenchXpath);
		logger.info(mouseHoverStatus);
		
		logger.info("Asserting french text in the English button dropdown menu");
		textAssert = objHomePage.getTextAndAssert(objHomePage.frenchBtn, "frenchBtnText");
		logger.info(textAssert);
		
		logger.info("Mouse hovering on my account button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(mouseHoverStatus);
		
		logger.info("Asserting signin button text in myaccount dropdown menu");
		textAssert = objHomePage.getTextAndAssert(objHomePage.signInBtn, "signInBtnText");
		logger.info(textAssert);
		
		logger.info("Mouse hovering on cart button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.cartBtn);
		logger.info(mouseHoverStatus);
	}
	
	@Test
	public void verifyDiscountedPriceAndColor() {		
		driver.get(objConfigFileReader.getString("homePageURL"));			
		
		logger.info("Test Case : Verifying discounted price is visible in the red color");
		
		logger.info("Asserting product name present on the home page");
		textAssert = objHomePage.getTextAndAssert(objHomePage.productName, "productTitle");
		logger.info(textAssert);
		
		logger.info("Asserting original price of the product");
		textAssert = objHomePage.getTextAndAssert(objHomePage.originalPrice, "productOriginalPrice");
		logger.info(textAssert);
		
		logger.info("Asserting discounted price of the product");
		textAssert = objHomePage.getTextAndAssert(objHomePage.discountedPrice, "productDiscountedPrice");
		logger.info(textAssert);
		
		String discountedPriceColor = objHomePage.verifyCssProperty(objHomePage.discountedPrice,"bagProperty");
		if(discountedPriceColor.equals("rgba(255, 0, 0, 1)")) {
			logger.info("Discounted price is visible in the red color");
		}
	}
	
	@Test
	public void verifyProductsHyperLinks() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		
		logger.info("Mouse hovering on products button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.products);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on HandBags button");
		objHomePage.clickBtn(objHomePage.handBags);
		textAssert = objHomePage.getTextAndAssert(objHomePage.handBagsText, "hadnBagText");
		logger.info(textAssert);
		
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovering on products button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.products);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on BeachBags button");
		objHomePage.clickBtn(objHomePage.beachBags);
		textAssert = objHomePage.getTextAndAssert(objHomePage.beachBagsText, "beachBagText");
		logger.info(textAssert);
	
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovering on products button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.products);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on LaptopBags button");
		objHomePage.clickBtn(objHomePage.laptopBags);
		textAssert = objHomePage.getTextAndAssert(objHomePage.laptopBagsText, "laptopBagText");
		logger.info(textAssert);
	
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovering on products button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.products);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on Bags button");
		objHomePage.clickBtn(objHomePage.bags);
		textAssert = objHomePage.getTextAndAssert(objHomePage.bagsText, "bagText");
		logger.info(textAssert);
	}
	
	@Test
	public void verifyEmailAndPhone() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Checking whether email is clickable");
		if(objHomePage.isClickEnabled(objHomePage.email))
			logger.info("Email is clickable");
	
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		logger.info("Checking whether phone number is clickable");
		if(objHomePage.isClickEnabled(objHomePage.phone))
			logger.info("Phone number is clickable");
	}
	
	@Test
	public void verifyMyAccountLinks() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovering on my account button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked Register button");
		objHomePage.clickBtn(objHomePage.registerBtn);
		
		logger.info("Asserting text present on register page");
		textAssert = objHomePage.getTextAndAssert(objHomePage.registerPageText, "verifyRegisterPageText");
		logger.info(textAssert);

		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		logger.info("Mouse hovering on my account button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked sign in button");
		objHomePage.clickBtn(objHomePage.signInBtn);
		
		logger.info("Asserting text present on sign in page");
		textAssert = objHomePage.getTextAndAssert(objHomePage.signInPageText, "verifySignInPageText");
		logger.info(textAssert);
	}
	
	@Test
	public void verifyPageLink() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Checking for brocken link of page button");
		String getAttributeStatus = objHomePage.getAttribute(objHomePage.page,"pageAttribute");
		logger.info(getAttributeStatus);
	}

	@Test
	public void verifyAddToCartButton(){
		driver.get(objConfigFileReader.getString("homePageURL"));	
		objHomePage.clickBtn(objHomePage.cockies);
		
		logger.info("Adding product to the cart");

		logger.info("Asserting product name present on the home page");
		textAssert = objHomePage.getTextAndAssert(objHomePage.productName, "productTitle");
		logger.info(textAssert);
	
		logger.info("Clicked on add to cart button");
		objHomePage.clickBtn(objHomePage.addToCartBtn);

		cartQuantity = objHomePage.verifyItemInCart(objHomePage.cartQuantity);
		if(cartQuantity>0)
			logger.info("Product added to the cart successfully");
	}	
	
	@Test
	public void verifyCheckoutButton() throws InterruptedException{
		driver.get(objConfigFileReader.getString("homePageURL"));	
		logger.info("Clicked on cart button");
		objHomePage.clickBtn(objHomePage.cartBtn);	
		
		logger.info("Clicked on checkout button of the cart");
		objHomePage.jsClickBtn(objHomePage.checkoutBtn);	
		
		objHomePage.waitForElementPresent(objHomePage.checkoutTable);		
		logger.info("Asserting text present on checkout page");
		textAssert = objHomePage.getTextAndAssert(objHomePage.totalText, "totalText");
		logger.info(textAssert);		
	}
	
	@Test
	public void verifyRemoveFromCartButton(){
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Removing product from the cart");
		
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));	
		logger.info("Mouse hovering on cart button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.cartBtn);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on remove from cart button");
		objHomePage.jsClickBtn(objHomePage.removeBtn);
		
		int cartQuantityAfterRemove = objHomePage.verifyItemInCart(objHomePage.cartQuantity);
		if(cartQuantityAfterRemove<cartQuantity || cartQuantityAfterRemove==0)
			logger.info("Product removed from the cart successfully");	
	}
	
	@Test
	public void verifylogout() {
		driver.get(objConfigFileReader.getString("homePageURL"));
		logger.info("Test Case : Verifying all the buttons working correctly");
		
		logger.info("Mouse hovering on my account button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.myAccount);
		logger.info(mouseHoverStatus);
		
		logger.info("Clicked on sign in button filled details");
		objHomePage.clickBtn(objHomePage.signInBtn);
	
		logger.info("Clicked on login button");
		objHomePage.signIn();

		logger.info("Asserting My Account button to assure login");
		textAssert = objHomePage.getTextAndAssert(objHomePage.myAcc, "welcomeMessage");
		logger.info(textAssert);
		
		driver.navigate().to(objConfigFileReader.getString("homePageURL"));
		
		objHomePage.waitForElementPresent(objHomePage.myAccountForLogout);
		
		logger.info("Mouse hovering on my account button");
		mouseHoverStatus = objHomePage.mouseHoverOnElement(objHomePage.myAccountForLogout);
		logger.info(mouseHoverStatus);
		
		logger.info("loggerged out successfully");
		objHomePage.jsClickBtn(objHomePage.logoutBtn);
	}	
}
