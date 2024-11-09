package hackathon;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Excel.outputExcel;
import utilityFile.captureScreenshot;

public class usedCars {
	WebDriver driver;
	public String filePath = null;
	captureScreenshot cs=new captureScreenshot();
    
	//Constructor to initialize WebElements
	public usedCars(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// WebElements for used cars page
	
	@FindBy(xpath = "//ul[@class=\"h-d-nav fnt-16 pl-0 txt-c \"]//a[@href=\"/used-car\"]")
	WebElement usedCars;
	
	@FindBy(xpath = "//a[text()='Used Cars in Chennai']")
	WebElement chennaiUsedCars;
	
	@FindBy(xpath = "//ul[@class='zw-sr-secLev usedCarMakeModelList popularModels ml-20 mt-10']/li")
	List<WebElement> carModel;
	
	@FindBy(xpath ="//*[@id=\"Header\"]/div/div[1]/div[1]/a/img")
	WebElement zigWheels;
	
	
	public void scroll() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,-2000);");
	}
	
	// Mouse hover on 'Used cars' menu from the main menu bar
	
	public void usedCarsMenu() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Actions action = new Actions(driver);
		action.moveToElement(usedCars).perform();
	    
		filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsoption.png";
		cs.captureTestScreenshot(driver,filePath);
		
	}
	
	public void selectUsedCars() {
		
	//usedCars.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].click();", usedCars);
	}
	
	// Click on 'Used cars in Chennai' link
   public void moveChennaicars(){
	   
	   try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Actions action = new Actions(driver);
		action.moveToElement(chennaiUsedCars).perform(); 
		
		filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/UsedCarsChennai.png";
		cs.captureTestScreenshot(driver,filePath);
	}
	
	
	public void selectChennaiUsedCars() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		chennaiUsedCars.click();

	}
	
	// Get list of all popular models of used cars in Chennai and write to excel file
	
	public void modelList() throws InterruptedException, IOException {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		// Scroll down to make all elements visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("scroll(0, 500)");
	    
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(30));
	    waits.until(ExpectedConditions.visibilityOfAllElements(carModel));
	    
	    filePath = System.getProperty("user.dir") + "/Screenshots/UsedCars/AvailableCars.png";
		cs.captureTestScreenshot(driver,filePath);
		
		
	    // Display the list of popular models on the console
	    System.out.println("Following is the list of Popular models:");
		for (int i = 0; i < carModel.size(); i++) {
			System.out.println(carModel.get(i).getText());
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		public void carExcel() throws Throwable{
			outputExcel.getUsedCar(carModel);
		}
	
	
	 public void handleWindow() {
		 String childwin=driver.getWindowHandle();
		 System.out.println("Window Id of Used Cars: "+childwin);
		 Set<String> windowIds = driver.getWindowHandles();
			
			List<String> listIds = new ArrayList<>(windowIds);
			
			for(String id:listIds) {
				if(id.equals(childwin)) {
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					driver.switchTo().window(id);	
				}
			}
			System.out.println();
//			driver.close();
			JavascriptExecutor js = (JavascriptExecutor) driver;
		    js.executeScript("window.scrollBy(0,-1000);");   
	 }
	 
	 public void moveToLogin() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Actions action = new Actions(driver);
			action.moveToElement(zigWheels).perform();
		
			filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/login.png";
			cs.captureTestScreenshot(driver,filePath);
		}
	 public void clickLogin() {
			zigWheels.click();
		}
}

