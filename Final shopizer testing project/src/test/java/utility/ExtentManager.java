package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	private static ExtentReports report;
	
	public static ExtentReports createInstance(String browserName)
	{
		ExtentHtmlReporter htmlReporter=new ExtentHtmlReporter("./Reports/Shopizer_"+browserName+"_"+Helper.getCurrentTime()+".html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Shopizer Test Result");
		htmlReporter.config().setReportName("Automation Tests");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		report=new ExtentReports();
		report.setSystemInfo("Team", "1");
		report.setSystemInfo("AUT", "Shopizer");
		report.attachReporter(htmlReporter);
		return report;
	}
}
