package buildVerificationTest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

//import com.fasterxml.jackson.databind.deser.Deserializers.Base;


import hackathon.googleSignIn;
import hackathon.upComingBikes;
import hackathon.usedCars;

public class smokeTest {
	public static WebDriver driver;
	 
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
    
	@Test(priority = 1)
	public void upComingBikes() throws Exception{
		
		upComingBikes up = new upComingBikes(driver);
		up.newBikesMenu(); // Clicks on the New Bikes menu
		up.clickNewBikes(); // Clicks on the Upcoming Bikes
		System.out.println("Upcoming Bikes submenu test passed");
		
	}
	
	@Test(priority = 2)
	public void usedCars() throws InterruptedException {
		
		usedCars userCars = new usedCars(driver);
		userCars.usedCarsMenu(); // Clicks on the Find Used Cars menu
		System.out.println("Find Used Cars submenu test passed");
	}
   
	@Test(priority = 3)
	public void testGoogleSignIn() throws InterruptedException {
		
		googleSignIn signin = new googleSignIn(driver);
		signin.clickSignIn(); // Clicks on the Sign In button
		System.out.println("Sign In Button functionality passed");
	}
	
	@AfterMethod
	public void closeDriver() {
		
		driver.close();
	}

}
