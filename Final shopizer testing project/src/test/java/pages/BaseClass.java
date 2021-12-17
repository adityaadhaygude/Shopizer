package pages;

import java.io.IOException;
import java.util.Arrays;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ConfigFileReader;
import utility.ExtentManager;
import utility.Helper;

public class BaseClass implements ITestListener{
	public static WebDriver driver;
	public ConfigFileReader objConfigFileReader;
	public RegistrationPage objRegistrationPage;
	public HomePage objHomePage;
	public SigninPage objSigninPage;
	public DashboardPage objDashboardPage;		
	public IndividualPage objIndividualPage;
	public CategoryPage objCategoryPage;
	public CartPage objCartPage;	
	public CheckoutPage objCheckoutPage;
	public static String browserName;
	private static ExtentReports chromeReport=ExtentManager.createInstance("Chrome");
	private static ExtentReports firefoxReport=ExtentManager.createInstance("Firefox");
	public static ExtentTest logger;

	// Initializes drivers, extent reporter and config files.
	@BeforeClass
	@Parameters("browser")
	public void setup(String browser) 
	{
		browserName=browser;
		if(browser.equalsIgnoreCase("Firefox")){ 		//Check if parameter passed from TestNG is 'firefox'       
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();				//create firefox instance
			//browserName="firefox";
		}
		else if(browser.equalsIgnoreCase("Chrome")){    //Check if parameter passed as 'chrome'
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);			//create chrome instance
			//browserName="chrome";
		}
		
		//Maximize driver windows
		driver.manage().window().maximize();		
		
		//Initialize config file reader
		objConfigFileReader = new ConfigFileReader();	
		
		//Initializing instance of each page
		objHomePage=new HomePage(driver);				
		objRegistrationPage=new RegistrationPage(driver);
		objSigninPage =new SigninPage(driver);	
		objDashboardPage=new DashboardPage(driver);	
		objIndividualPage =new IndividualPage(driver);	
		objCategoryPage=new CategoryPage(driver);	
		objCartPage=new CartPage(driver);	
		objCheckoutPage=new CheckoutPage(driver);	

	}

	//Creates test dynamically respect to the browser
	public void onTestStart(ITestResult result) {
		if(browserName.equalsIgnoreCase("chrome"))
		{
			logger=chromeReport.createTest(result.getName());
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			logger=firefoxReport.createTest(result.getName());
		}
	}

	// Auto runs if test case is passed and generates report
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getName();
		if(browserName.equalsIgnoreCase("chrome"))
		{
			try
			{
				String path=Helper.captureScreenshot(driver,methodName);
				logger.pass("<b><font color=green>"+"Screenshot of Passed Test "+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			catch (IOException e) {
				logger.pass("Test Passed, cannot attach screenshot");
			}
			logger.pass(methodName+" passed");
			chromeReport.flush();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			try
			{
				String path=Helper.captureScreenshot(driver,methodName);
				logger.pass("<b><font color=green>"+"Screenshot of Passed Test "+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			catch (IOException e) {
				logger.pass("Test Passed, cannot attach screenshot");
			}
			logger.pass(methodName+" passed");
			firefoxReport.flush();
		}
	}

	//Auto runs if test case is failed and generates report
	public void onTestFailure(ITestResult result) {
		String methodName=result.getName();
		String exceptionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		if(browserName.equalsIgnoreCase("chrome"))
		{
			logger.fail("<details><summary><b><font color=red>Exception Occured, click to see details:"+"</font></b></summary>"+exceptionMessage.replaceAll(",","<br>")+"</details> \n");
			try
			{
				String path=Helper.captureScreenshot(driver,methodName);
				logger.fail("<b><font color=red>"+"Screenshot of failure"+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			catch (IOException e) {
				logger.fail("Test failed, cannot attach screenshot");
			}
			logger.fail(methodName+" failed");
			chromeReport.flush();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			logger.fail("<details><summary><b><font color=red>Exception Occured, click to see details:"+"</font></b></summary>"+exceptionMessage.replaceAll(",","<br>")+"</details> \n");
			try
			{
				String path=Helper.captureScreenshot(driver,methodName);
				logger.fail("<b><font color=red>"+"Screenshot of failure"+"</font></b>",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
			}
			catch (IOException e) {
				logger.fail("Test failed, cannot attach screenshot");
			}
			logger.fail(methodName+" failed");
			firefoxReport.flush();
		}			
	}

	//Auto runs if test case is skipped and generates report
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getName();	
		if(browserName.equalsIgnoreCase("chrome"))
		{
			String logText="<b>Test "+methodName+" Skipped</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
			chromeReport.flush();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			String logText="<b>Test "+methodName+" Skipped</b>";
			Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
			logger.log(Status.SKIP, m);
			firefoxReport.flush();
		}
	}

	// Closes driver after execution
	@AfterClass
	public void teardown() {
		driver.close();
	}

	//Provides registration data to registration tests
	@DataProvider
	public String[][] registrationData(){
		String path = "Datafiles/registrationData.xlsx";
		Helper excelHelper = new Helper(path);
		int totalrows = excelHelper.getRowCount("Sheet1");
		int totalcols = excelHelper.getCellCount("Sheet1", 1);
		String registrationData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				registrationData[i - 1][j] = excelHelper.getCellData("Sheet1", i, j);
			}
		}
		return registrationData;
	}

	//Provides signin data to signin tests
	@DataProvider
	public String[][] signinData(){
		String path = "Datafiles/signinData.xlsx";
		Helper excelHelper = new Helper(path);
		int totalrows = excelHelper.getRowCount("Sheet1");
		int totalcols = excelHelper.getCellCount("Sheet1", 1);
		String signinData[][] = new String[totalrows][totalcols];
		for (int i = 1; i <= totalrows; i++) // 1
		{
			for (int j = 0; j < totalcols; j++) // 0
			{
				signinData[i - 1][j] = excelHelper.getCellData("Sheet1", i, j);
			}
		}
		return signinData;
	}

	
	//Provides dashboard data to dashboard tests
		@DataProvider
		public String[][] dashboardData(){
			String path = "Datafiles/dashboardData.xlsx";
			Helper excelHelper = new Helper(path);
			int totalrows = excelHelper.getRowCount("Sheet1");
			int totalcols = excelHelper.getCellCount("Sheet1", 1);
			String dashboardData[][] = new String[totalrows][totalcols];
			for (int i = 1; i <= totalrows; i++) // 1
			{
				for (int j = 0; j < totalcols; j++) // 0
				{
					dashboardData[i - 1][j] = excelHelper.getCellData("Sheet1", i, j);
				}
			}
			return dashboardData;
		}
	//Unimplemented methods from ITestListener which are not used
	public void onFinish(ITestContext tc) {}
	public void onStart(ITestContext tc) {}
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {}
}
