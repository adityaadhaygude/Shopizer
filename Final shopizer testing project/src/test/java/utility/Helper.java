package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.io.FileHandler;

public class Helper {
	public String excelPath;
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	public Helper(String path)
	{
		excelPath=path;
	}
	
	public int getRowCount(String sheetName) 
	{
		try {
			fi=new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook=new XSSFWorkbook(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		try {
			workbook.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rowcount;		
	}
	public int getCellCount(String sheetName,int rownum)
	{
		try {
			fi=new FileInputStream(excelPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook=new XSSFWorkbook(fi);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		try {
			workbook.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cellcount;
	}
	public String getCellData(String sheetName,int rownum,int colnum)
	{
		try {
			fi=new FileInputStream(excelPath);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			workbook=new XSSFWorkbook(fi);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		DataFormatter formatter = new DataFormatter();
		String data;
		try{
		data = formatter.formatCellValue(cell); //Returns the formatted value of a cell as a String regardless of the cell type.
		}
		catch(Exception e)
		{
			data="";
		}
		try {
			workbook.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	
	
	public static String captureScreenshot(WebDriver driver,String name)
	{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/"+name+" "+getCurrentTime()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
		} catch (Exception e) {
		}
		return screenshotPath;
	}
	public static String getCurrentTime()
	{
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}

}
