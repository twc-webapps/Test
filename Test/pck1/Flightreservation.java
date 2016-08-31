/*###############################################################################
'-------------------------------------------------------------------------------
' Case Study: Case Study 1
' Script Name: TS_BookFlight
' Brief Functional Description: Book a return Journey
' Created On: 30-05-2016
' Created By: k.y.sadashivappa
' Comments/Remmark: 
'-------------------------------------------------------------------------------
'###############################################################################*/
package CS1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import jxl.*;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Flightreservation {
	
   public static void main(String[] args) throws BiffException, IOException, InterruptedException
	 {
	    String currentdir = System.getProperty("user.dir");
	    System.out.println(currentdir);
	    
	    WebDriver driver = new FirefoxDriver();
	    
	    File data = new File("C:\\Users\\k.y.sadashivappa\\workspace\\CaseStudy1\\Input.xls");
		WorkbookSettings ws = new WorkbookSettings();
		ws.setLocale(new Locale("er", "ER"));
		Workbook wb = Workbook.getWorkbook(data, ws);	    
	    Sheet sh = wb.getSheet(0);
	    
	    File file = new File("out.txt");
	 	FileOutputStream fos = new FileOutputStream(file);
	 	PrintStream ps = new PrintStream(fos);
	 	PrintStream console = System.out;
	 	System.setOut(ps);
	    
	    String url = sh.getCell(2,1).getContents();//"http://newtours.demoaut.com";
	    String Uname =sh.getCell(4,1).getContents();//"mercury";
	    String Pwrd = sh.getCell(6,1).getContents();//"mercury";
	    String DepFrm =sh.getCell(8,1).getContents();//"Paris";
	    String On = sh.getCell(10,1).getContents();//"8";
	    String Arrin = sh.getCell(12,1).getContents();//"Seattle";
	    String retin = sh.getCell(14,1).getContents();//"10";
	    System.out.println(url);
	    System.out.println(Uname);
	    System.out.println(Pwrd);
	    
	    driver.get(url);
	    Thread.sleep(1000);
	    
	    if(driver.findElement(By.name("userName")).isDisplayed())
	    {
	    	System.out.println("Username present");
	    }
	    else
	    {
	    	System.out.println("Username not present");
	    }
	    
	    driver.findElement(By.name("userName")).sendKeys(Uname);
	    driver.findElement(By.name("password")).sendKeys(Pwrd);	    
		driver.findElement(By.name("login")).click();
		
		Thread.sleep(3000);
	    	
		if(driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[8]/td/font/font/b/font/font")).getText().equals("Preferences"))
		{
			System.out.println("The Flight Finder page is loaded");
		}
		else
		{
			System.out.println("The Flight Finder is not loaded");
		}
		
		int count=driver.findElements(By.xpath("//html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select/option")).size();
		System.out.println("Departure count"+count);
		String[] Decity =new String[count+2];
		for(int i=1;i<=count;i++)
		{
			Decity[i]=driver.findElement(By.xpath("//html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select/option["+i+"]")).getText();
			System.out.println("Departure city names"+Decity[i]);
		}
		
		driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")).click();
 	   	driver.findElement(By.name("fromPort")).sendKeys(DepFrm);
 	   	
 	   	driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")).click();
	   	driver.findElement(By.name("fromDay")).sendKeys(On);
	   	
	   	driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")).click();
 	   	driver.findElement(By.name("toPort")).sendKeys(Arrin);
 	   	
 	   	driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td[2]/select")).click();
	   	driver.findElement(By.name("toDay")).sendKeys(retin);
		Thread.sleep(2000);
		
		System.out.println("Flight Info"+DepFrm +On +Arrin +retin);
		driver.findElement(By.name("findFlights")).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[1]/td/table/tbody/tr[2]/td[1]/b/font")).isDisplayed())
		{
			System.out.println("The Select Flight page is loaded");
		}
		else
		{
			System.out.println("The Select Flight is not loaded");
		}
		
		driver.findElement(By.name("reserveFlights")).click();
		Thread.sleep(2000);
		                                 
		if(driver.findElement(By.xpath("//html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td")).isDisplayed())
		{
			System.out.println("The Book A Flight page is displayed");
		}
		else
		{
			System.out.println("The Book A Flight page is not displayed");
		}
				
 	   	driver.findElement(By.name("passFirst0")).sendKeys("Test");
 	   	driver.findElement(By.name("passLast0")).sendKeys("A");
 	   	driver.findElement(By.name("creditnumber")).sendKeys("123456789");
 	   	driver.findElement(By.name("buyFlights")).click();
 	   
 	   	Thread.sleep(2000);
 	   	if(driver.findElement(By.xpath("//font[2]")).isDisplayed())
 	   	{
			System.out.println("Your itinerary has been booked! is displayed");
		}
		else
		{
			System.out.println("Your itinerary has been booked! is not loaded");
		}
 	   		
 	   //Thread.sleep(2000);
 	   System.setOut(console);
 	   
 	  driver.close();
	}
   
}
