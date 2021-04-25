package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage 
{

	WebDriver driver;
	
	public LoginPage(WebDriver ldriver)
	{
		this.driver = ldriver; 	
	}
	
	
	@FindBy(name="username")
	WebElement uname;
	
	@FindBy(name="password")
	WebElement passwd;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginbtn;
	
	public void loginToCRM(String appUname, String appPass)
	{
		
		try
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uname.sendKeys(appUname);
		passwd.sendKeys(appPass);
		loginbtn.click();
	}
	
	
}
