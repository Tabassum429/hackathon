package buildVerificationTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import hackathon.googleSignIn;
import hackathon.upComingBikes;
import hackathon.usedCars;

public class regressionTest {
	public static WebDriver driver;
	 
	// Setting up the driver before each test method
	@BeforeMethod
	@Parameters({"browser"})
	public void setup(String br) {

		switch(br.toLowerCase()) {
		case "chrome" :
			driver=new ChromeDriver();
			break;
		case "edge" :
			driver=new EdgeDriver();
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(" https://www.zigwheels.com");
		driver.manage().window().maximize();
	}
	
	// Test method for Google Sign In
		@Test(priority = 1)
		public void testGoogleSignIn() throws InterruptedException {
			
			googleSignIn signin = new googleSignIn(driver);
			signin.clickSignIn();
			signin.googleSignin();
			signin.emailInput("pavi@gmal");
			signin.emailNext();
			signin.getErrorMessage();
			System.out.println();
			System.out.println("Signin Test Passed");
		}
			
	// Test method for Upcoming Bikes
	@Test(priority = 2)
	public void upComingBikes() throws Exception{
		
		upComingBikes up = new upComingBikes(driver);
		up.newBikesMenu();
		up.clickNewBikes();
		up.upComingbikes();
		up.selectUpcomingBike();
		System.out.println("Bikes Test passed");
/*		up.selectAllUpcomingBikes();
		up.selectManufacturer();
		up.viewMoreBikes();
//		up.bikeModels();
*/		
	}
	
	// Test method for Used Cars
	@Test(priority = 3)
	public void usedCars() throws InterruptedException, IOException {
		
		usedCars userCars = new usedCars(driver);
		userCars.usedCarsMenu();
		userCars.selectUsedCars();
		userCars.moveChennaicars();
		userCars.selectChennaiUsedCars();
//		userCars.modelList();
		System.out.println("UsedCars Test passed");
	}
   
	
	
	// Closing the driver after each test method
	@AfterMethod
	public void closeDriver() {
		driver.quit();
	}

}
