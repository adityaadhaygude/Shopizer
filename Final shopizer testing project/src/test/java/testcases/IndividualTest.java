package testcases;

import org.testng.annotations.Test;
import pages.BaseClass;

public class IndividualTest extends BaseClass {

	@Test
	public void verifyOpenProductPage() {

		driver.get(objConfigFileReader.getString("IndividualPageURL"));	

		//individual page title
		logger.info("Asserted home page title");
		objIndividualPage.assertTitle();
		
		//Mouse Over Effect
		logger.info("Mouse hovered on products image");
		objIndividualPage.mouseHoverOnElement1(objIndividualPage.productImage);

		//Get&Assert the text
		logger.info("Asserted product name on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.productName, "productName");

		logger.info("Asserted product price on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.originalPrice, "originalPrice");

		logger.info("Asserted product add to cart on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.addToCart, "addToCart");

		logger.info("Asserted product name on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.productHeader, "productHeader");

		logger.info("Asserted product name on individual product page");
		objIndividualPage.verifyCssProperty(objIndividualPage.productTitle, "productHeader");

		//To Click product button
		logger.info("Clicked on product title");
		objIndividualPage.clickBtn(objIndividualPage.productName);
	}

	@Test
	public void verifyRating() 	
	{
		driver.get(objConfigFileReader.getString("IndividualPageURL"));	
		logger.info("Asserted home page title");
		objIndividualPage.assertTitle();

		logger.info("Asserted product name on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.productName, "productName");

		//To check the rating
		logger.info("Asserted Product Rating");
		objIndividualPage.ratingButtonIsEditable(objIndividualPage.ratingBtn);
	}

	@Test
	public void verifyCart()
	{
		driver.get(objConfigFileReader.getString("IndividualPageURL"));	
		objHomePage.clickBtn(objHomePage.cockies);
		logger.info("Asserted home page title");
		objIndividualPage.assertTitle();

		logger.info("Asserted product name on home page featured list");
		objIndividualPage.getTextAndAssert(objIndividualPage.productName, "productName");

		logger.info("Asserted cart in the cartlog");
		objIndividualPage.getTextAndAssert(objIndividualPage.addToCart,"addToCart");
	}
}
