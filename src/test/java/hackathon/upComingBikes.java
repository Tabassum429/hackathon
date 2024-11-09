package hackathon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Excel.outputExcel;
import utilityFile.captureScreenshot;

public class upComingBikes {
	WebDriver driver;
	public String filePath = null;
	captureScreenshot cs=new captureScreenshot();
	public ArrayList<String> upcomingBikes;

	 
	//Constructor to initialize WebElements
	
	public upComingBikes(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);  //to initialize the web elements present in the UpcomingBikes class.
	}
	
	// WebElements for Upcoming Bikes Page
	
	@FindBy(xpath= "//a[@title='New Bikes']")
	WebElement newBikesElement;
	
	@FindBy(xpath="//li[normalize-space()='Upcoming']")
	WebElement upcomingbikes;
	
	@FindBy(xpath="//a[@title='All Upcoming Bikes']")
	WebElement allUpcomingBikes;
	
	@FindBy(xpath="//select[@id='makeId']")
	WebElement manufacturer;
	
	@FindBy(xpath = "//span[@class='zw-cmn-loadMore']")
	WebElement viewMore;
	
	@FindBy(xpath = "//ul[@id='modelList']")
	WebElement hondaBikeModels;
	
	int count = 0;

	// Hover over 'New Bikes' menu to view 'Upcoming Bikes'
	
	public void newBikesMenu() throws Exception {
		
		Actions action = new Actions(driver);
		action.moveToElement(newBikesElement).perform(); // Move mouse to 'New Bikes' menu
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/NewBikesMenu.png";
		cs.captureTestScreenshot(driver,filePath);
	}
	
	public void clickNewBikes() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		newBikesElement.click(); // Click 'Upcoming Bikes'
	}
	
   public void upComingbikes() throws Exception {
	   try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Actions action = new Actions(driver);
		action.moveToElement(upcomingbikes).perform(); // Move mouse to 'New Bikes' menu
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/upcomingBike.png";
		cs.captureTestScreenshot(driver,filePath);
	}
	// Select 'Upcoming Bikes'
	
	public void selectUpcomingBike() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/UpcomingBikeMenu.png";
		cs.captureTestScreenshot(driver,filePath);
		
		upcomingbikes.click(); // Click 'Upcoming Bikes'
	}
	
    public void allUpComingbikes() throws Exception {
		
		Actions action = new Actions(driver);
		action.moveToElement(allUpcomingBikes).perform(); // Move mouse to 'New Bikes' menu
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/AllupcomingBike.png";
		cs.captureTestScreenshot(driver,filePath);
	}
	
	// Select 'All upcoming Bikes'
	public void selectAllUpcomingBikes() throws Exception {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/AllUpcomingBikeMenu.png";
		cs.captureTestScreenshot(driver,filePath);
		
		allUpcomingBikes.click(); // Click 'Upcoming Bikes'
	}
	
	// Select 'Honda' as manufacturer
	
	public void selectManufacturer() throws Exception {
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UpcomingBikes/ManufacturerDropdown.png";
		cs.captureTestScreenshot(driver,filePath);
		
//		obj.wait_Explicit(30,manufacturer,driver);	
		
		WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement drop=myWait.until(ExpectedConditions.elementToBeClickable(manufacturer));
		
		Select select = new Select(drop);
		select.selectByVisibleText("Honda");  // Select 'Honda'
		
	}
     
	
	// Click 'View More Bikes'
	public void viewMoreBikes() throws Exception {
		
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", viewMore);
		} catch (Exception e) {
			e.printStackTrace();
		} 
//		Actions action = new Actions(driver);
//		action.moveToElement(viewMore).click().build().perform();
		viewMore.click();
		
	}
	
	// Get bike models, prices, and expected launch date
	
	public void bikeModels() throws Exception {
		Actions action = new Actions(driver);
		action.moveToElement(viewMore).click().build().perform();
		// Get text of all bike models available on the page
		String bikeData = hondaBikeModels.getText();
		
		// Split the text by new line character and store it in an ArrayList
		ArrayList<String> bikeModelsElements = new ArrayList<String>();
		Collections.addAll(bikeModelsElements, bikeData.split("\n"));
        
		// Initialize ArrayLists to store names, launch dates and prices of bikes
		ArrayList<String> nameList = new ArrayList<String>();
		ArrayList<String> dateList = new ArrayList<String>();
		ArrayList<String> priceList = new ArrayList<String>();
		String[] arr = null;
		
		// Iterate through the list of bike models and extract the required information
		for (int i = 0; i < bikeModelsElements.size(); i++) {
			String s = bikeModelsElements.get(i);
			if (s.contains("Honda")) {
				nameList.add(s);
			}
			
			if (s.contains("Rs. ")) {
				arr = s.split(" ");
				if(arr.length<3) {
					String replacedValue="";
					if(arr[1].contains(",")) {
						replacedValue = arr[1].replace(",","");
					}
					double price = Double.parseDouble(replacedValue); 
					price = price/100000;
					arr[1] = ""+price+"";
				}
				priceList.add(arr[1]);
			}
			if (s.contains("Expected Launch : ") || s.contains("Expected Launch Date :")) {
				dateList.add(s);
			}
		}
		
		// Wait for page elements to load
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
		// Initialize an ArrayList to store upcoming bikes with price less than 4 Lakhs
		upcomingBikes = new ArrayList<String>();
		
		System.out.println(nameList.size());
		System.out.println(priceList.size());
		System.out.println(dateList.size());
		if(nameList.size()>0) {
			for (int i = 0; i <nameList.size(); i++) {
			String temp = nameList.get(i);
			
			// Convert bike price to a double value
			NumberFormat format = NumberFormat.getInstance(Locale.FRANCE); // parse numbers in French-style format
			Number number = format.parse(priceList.get(i));
			double price = number.doubleValue();
			
			// Combine bike name, price and launch date to a single string
			String info = temp + "    " + priceList.get(i) + " Lakh    " + dateList.get(i);
			
			// Check if bike name is present in the string and price is less than 4 Lakhs
			if (info.contains(temp)) {
				if (price < 4.0) {
					upcomingBikes.add(info);
				}
			}
		  }
		}
			
		
		// Print the list of upcoming bikes to the console
		System.out.println("Upcoming Honda Bikes Below 4 Lakhs are as follows:");
		for (int i = 0; i < upcomingBikes.size(); i++) {
			System.out.println(upcomingBikes.get(i));
			
		}
		
		System.out.println();
	}	
	public void bikesExcel() throws Throwable {
		outputExcel.getBikeExcel(upcomingBikes);
	}

}
