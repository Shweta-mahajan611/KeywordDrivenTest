package com.keyworddriven.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.keyworddriven.base.Base;

public class KeywordEngineTest 
{
	public WebDriver driver;
	public Properties prop;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public Base base;
	public WebElement element;
	
	public final String SCENARIO_SHEET_PATH = "C:\\Users\\shwetamahajan\\Documents\\Fb_scenarios.xlsx";
	
	public void startExecution(String sheetName) throws InterruptedException 
	{
		String locatorName = null;
		String locatorVal = null;
		
		FileInputStream file = null;
		try {
			 file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		int k=0;
		
		for (int i = 0; i < sheet.getLastRowNum(); i++) {

			try {

				String locatorColValue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				if (!locatorColValue.equalsIgnoreCase("NA")) {
					// Find By Id
					locatorName = locatorColValue.toString().split("=")[0].trim();
					// Value of the locator
					locatorVal = locatorColValue.toString().split("=")[1].trim();
				}

				String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();

				System.out.println("Action :"+action);
				System.out.println("values :" + value);
				
				switch (action) 
				{
				case "open browser":
					base = new Base();
					prop = base.init_properties();
					if(value.isEmpty() || value.equalsIgnoreCase("NA")) {
					driver = base.init_driver(prop.getProperty("browser"));
					}else {
						driver = base.init_driver(value);
					}
					break;

				case "enter url":
					if (value.isEmpty() || value.equals("NA")) {
						driver.get(prop.getProperty("url"));

					} else {
						driver.get(value);
					}

				case "get title":
					if(value.isEmpty() || value.equals("NA")) {
						String title = driver.getTitle();
						System.out.println("Title = "+title);
					}
					locatorColValue = null;
					break;
					
				case "quit":
					driver.quit();
					break;

				default:
					break;
				}
				
				switch (locatorName) {
				case "id":
					Thread.sleep(2000);
					element = driver.findElement(By.id(locatorVal));
					Thread.sleep(2000);
					System.out.println(element);
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						Thread.sleep(2000);
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(1000);
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						element.getText();
					}
					break;
					
				case "xpath":
					Thread.sleep(2000);
					element = driver.findElement(By.xpath(locatorVal));
					Thread.sleep(2000);
					System.out.println(element);
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						Thread.sleep(2000);
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(1000);
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						String text = element.getText();
						System.out.println("Text = "+text);
					}
					break;
				
				case "cssSelector":
					Thread.sleep(2000);
					element = driver.findElement(By.cssSelector((locatorVal)));
					Thread.sleep(2000);
					System.out.println(element);
					if (action.equalsIgnoreCase("sendkeys")) {
						element.clear();
						Thread.sleep(2000);
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						Thread.sleep(1000);
						element.click();
					}else if (action.equalsIgnoreCase("isDisplayed")) {
						element.isDisplayed();
					}else if(action.equalsIgnoreCase("getText")) {
						String text = element.getText();
						System.out.println("Text = "+text);
					}
					break;
				
					 
				 case "name":
					 element = driver.findElement(By.name(locatorVal));
					 Thread.sleep(2000);
					 if(action.equalsIgnoreCase("sendkeys"))
					 {
						 element.clear();
						 Thread.sleep(2000);
						 element.sendKeys(value);
					 }
					 else if (action.equalsIgnoreCase("click")) 
					 {
							Thread.sleep(1000);
							element.click();
					 }else if(action.equalsIgnoreCase("getText")) {
							element.getText();
					}
					 break;
					 
				 case "className":
						Thread.sleep(2000);
						element = driver.findElement(By.className(locatorVal));
						Thread.sleep(2000);
						System.out.println(element);
						if (action.equalsIgnoreCase("sendkeys")) {
							element.clear();
							Thread.sleep(2000);
							element.sendKeys(value);
						} else if (action.equalsIgnoreCase("click")) {
							Thread.sleep(1000);
							element.click();
						}else if (action.equalsIgnoreCase("isDisplayed")) {
							element.isDisplayed();
						}else if(action.equalsIgnoreCase("getText")) {
							element.getText();
						}
						break;
					
					 
				 case "linkText":
					 element = driver.findElement(By.linkText(locatorVal));
					 element.click();
					 locatorName = null;
					 break;
					 
				 case "partialLinkText":
					 element = driver.findElement(By.partialLinkText(locatorVal));
					 element.click();
					 locatorName = null;
					 break;
					 
				default:
					break;
				}

			} catch (Exception e) {

			}
		

			}
    }//for
	}	

