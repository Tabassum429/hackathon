package utilityFile;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class captureScreenshot {
	public static WebDriver driver;
	public  void captureTestScreenshot(WebDriver driver, String filePath){
		//Take the screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Copy the file  to a location and use try catch block to handle exception
		 try {
	           FileUtils.copyFile(screenshot, new File(filePath));
	        } catch (IOException e) {
	            System.out.println(e.getMessage());
	       }
	} 
	/* TakesScreenshot ts=(TakesScreenshot)driver;
		 File src=ts.getScreenshotAs(OutputType.FILE);
		 File trg=new File(filePath);
		 src.renameTo(trg);*/
	


/*googleSignIn gs=new googleSignIn(driver);

String fileName=System.getProperty("user.dir")+File.separator+"screenShots";
File filePath = ((TakesScreenshot)gs.driver).getScreenshotAs(OutputType.FILE);
FileUtils.copyFile(filePath,new File(fileName +".png"));*/
}