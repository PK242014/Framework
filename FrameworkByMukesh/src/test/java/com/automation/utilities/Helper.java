package com.automation.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper
{
	
	//screenshot, alerts, frames,windows,sync issues, javascript executor
	
	public static String captureScreenshot(WebDriver driver)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String sreenshotpath=  System.getProperty("user.dir")+"./Sreenshots/FreeCRM_" +getCurrentDateTime()+ ".png";
		
		try 
		{
			FileHandler.copy(src,new File(sreenshotpath));
			System.out.println("Screenshot captured Successfully");
		} 
		catch (IOException e) 
		{
			System.out.println("unable to take Screenshot" +e.getMessage());
		}
		
		return  sreenshotpath;
	} 
	
	public static String getCurrentDateTime()
	{
		DateFormat customFormat= new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentdate=  new Date();
		return customFormat.format(currentdate);
	}

}
