package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.BaseClass;

public class CategoryTest extends BaseClass{

	//Test method to check the bags of Handbags category
	@Test
	public void selectCategory() 
	{		  
		driver.get(objConfigFileReader.getString("homePageURL"));

		logger.info("Clicked on handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Asserted all elements under handbags category");
		objCategoryPage.categoryElements();
	}
	//Test method to check the bags of chic collection
	@Test
	public void selectCollection()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));

		logger.info("Clicked on Handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Chic collection clicked");
		objCategoryPage.jsClickBtn(objCategoryPage.collection1);

		logger.info("Asserted all elements under Chic collection");
		objCategoryPage.collectionElements();	  
	}
	//test method to check the product link
	@Test
	public void productLink() 
	{
		driver.get(objConfigFileReader.getString("homePageURL"));

		logger.info("Clicked on handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Click on Vintage Courier Bag");
		objCategoryPage.jsClickBtn(objCategoryPage.viewItem1);

		logger.info("Assert title of the page");
		objCategoryPage.assertTitle();

		logger.info("Assert URL");
		objCategoryPage.checkURL();
	}
	//test method to check add to cart button functionality
	@Test
	public void addToCart() 
	{
		driver.get(objConfigFileReader.getString("homePageURL"));
		driver.findElement(By.linkText("Got it!")).click();

		logger.info("Clicked on handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Clicked on add to cart button");
		objCategoryPage.jsClickBtn(objCategoryPage.addToCartBtn);

		logger.info("Assert that product is added to cart");
		objCategoryPage.assertCart();
	}
	//test method to check sort by name functionality
	@Test
	public void sortByName()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));

		logger.info("Clicked on handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Assert that elements are sorted by name");
		objCategoryPage.sortedElementsByName();
	}
	//test method to check sort by price functionality
	@Test
	public void sortByPrice()
	{
		driver.get(objConfigFileReader.getString("homePageURL"));

		logger.info("Clicked on handbags category");
		objHomePage.clickBtn(objHomePage.handBags);

		logger.info("Assert that elements are sorted by name");
		objCategoryPage.sortedElementsByPrice();
	}

}
