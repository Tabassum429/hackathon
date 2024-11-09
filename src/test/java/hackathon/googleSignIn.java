package hackathon;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilityFile.captureScreenshot;


public class googleSignIn {
	public WebDriver driver;
	public String filePath = null;
	captureScreenshot cs=new captureScreenshot();
     
	//Constructor to initialize the web driver and page objects
	
	 public googleSignIn(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	
	 // page objects
	 
	 @FindBy(xpath ="//*[@id=\"Header\"]/div/div[1]/div[1]/a/img")
	 WebElement zigWheels;
	 
	 @FindBy(xpath ="//div[@id='des_lIcon']")
	 WebElement signInButton;
	 
	 @FindBy(xpath = "//div[@class='lgn-sc c-p txt-l pl-30 pr-30 googleSignIn']")
	 WebElement google;
	 
	 @FindBy(xpath="//input[@type='email']")
	 WebElement email;
	 
	 @FindBy(xpath="//span[normalize-space()='Next']")
	 WebElement emailNextButton;
	 
	 @FindBy(xpath = "//*[@id=\"yDmH0d\"]/c-wiz/div/div[2]/div/div/div[1]/form/span/section/div/div/div[1]/div/div[2]/div[2]")
	 WebElement errorMessage;
	
	// method to click on Sign In button 
	public void clickSignIn() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		signInButton.click();	
	}
	
	// method to sign in with Google
	
	public void googleSignin() throws InterruptedException {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		google.click();
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/GoogleSignInButton.png";
		cs.captureTestScreenshot(driver,filePath);
	}
     
	// method to enter invalid email ID
	
    public void emailInput(String emailId) {
		  
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	String parentId = driver.getWindowHandle();
    	
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
        Set<String> windowIds = driver.getWindowHandles();
		
		List<String> listIds = new ArrayList<>(windowIds);
		
		for(String id:listIds) {
			if(!id.equals(parentId)) {
				driver.switchTo().window(id);
			}
		}
		
		email.sendKeys(emailId);
    }
		// method to click on Next button after entering email ID
	    
	    public void emailNext() {
			
			Actions action = new Actions(driver);
			action.moveToElement(emailNextButton).click().perform();
			
			filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/NextButton.png";
			cs.captureTestScreenshot(driver,filePath);
		}
    // method to capture the error message
    
    public void getErrorMessage() {
		
    	try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
		String message= errorMessage.getText();
		
		filePath = System.getProperty("user.dir") + "/Screenshots/GoogleSignIn/ErrorMessage.png";
		cs.captureTestScreenshot(driver,filePath);
		
		System.out.println("Error message: "+message);
		
	}
    
  
}
