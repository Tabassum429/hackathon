package testNgClass;

import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.ObjectInputFilter.Status;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import Excel.outputExcel;
import hackathon.googleSignIn;
import hackathon.upComingBikes;
import hackathon.usedCars;


public class testClass {
	public static WebDriver driver;
	public static ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/ExtentReport/extentReportFile.html");
	public static ExtentReports extent = new ExtentReports();
	

	@BeforeClass
	@Parameters({"browser"})
	public void setup(String br) throws FileNotFoundException {
//		driver =driverSetUp.getDriver();
		switch(br.toLowerCase()) {
		case "chrome" :
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-notifications");
			driver=new ChromeDriver(options);
			break;
		case "edge" :
			EdgeOptions opt = new EdgeOptions();
		    opt.addArguments("disable-notifications");
			driver=new EdgeDriver(opt);
			break;
		default:
			System.out.println("Invalid Browser");
			return;
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(" https://www.zigwheels.com");
		driver.manage().window().maximize();
		
		extent.attachReporter(reporter);
		reporter.config().setDocumentTitle("Automation Result");
		reporter.config().setReportName("Indentify New Bikes Automation Test");
		reporter.config().setTheme(Theme.DARK);
		
		ExtentTest test = extent.createTest("Driver Test");
		test.log(Status.INFO, "Browser launched");
		test.log(Status.INFO, "Navigated to https://www.zigwheels.com/");
		
		outputExcel.openExcel();
	}
    
	@Test(priority=1)
	public void testUpComingBikes() throws Throwable{
		
		extent.attachReporter(reporter);
		ExtentTest test = extent.createTest("Upcoming Bikes Test");
		
		upComingBikes up = new upComingBikes(driver);
		
		up.newBikesMenu();
		test.log(Status.INFO, "Mouse Hover on 'New Bikes'");
		
		up.clickNewBikes();
		test.log(Status.INFO, "new bikes option is clicked ");
		
		up.upComingbikes();
		test.log(Status.INFO, "Mouse Hover on 'Upcoming bikes'");
		
		up.selectUpcomingBike();
		test.log(Status.INFO, "Upcoming Bikes is clicked");
		
		up.allUpComingbikes();
		test.log(Status.INFO, "Mouse Hover on 'All Upcoming bikes'");
		
		up.selectAllUpcomingBikes();
		test.log(Status.INFO, "All Upcoming Bikes menu is selected");
		
		up.selectManufacturer();
		test.log(Status.INFO, "Manufacturer name is selected as 'Honda'");
		
		up.bikeModels();
		test.log(Status.INFO, "Upcoming Bikes under 4 lakhs is displayed on console ");
		
		test.log(Status.PASS, "Upcoming Bikes test is passed");
		
		up.bikesExcel();
		
		extent.flush();
		
	}

	@Test(priority=2)
	public void testUsedCars() throws Throwable {
		extent.attachReporter(reporter);
		ExtentTest test = extent.createTest("Used Cars in Chennai Test");
		
		usedCars userCars = new usedCars(driver);
		
		userCars.scroll();
		
		userCars.usedCarsMenu();
		test.log(Status.INFO, "Mouse Hover on 'Used Cars'");
		
		userCars.selectUsedCars();
		test.log(Status.INFO, "Used cars are clicked");
		
		userCars.moveChennaicars();
		test.log(Status.INFO, "Find Used Cars in Chennai option");
		
		userCars.selectChennaiUsedCars();
		test.log(Status.INFO, "Find Used Cars in Chennai is selected as Location");
		
		userCars.modelList();
		test.log(Status.INFO, "Popular Models List is Displayed on console");
		
		userCars.carExcel();
		
		userCars.handleWindow();
		
		userCars.moveToLogin();
		
		userCars.clickLogin();
		test.log(Status.PASS, "Used cars in chennai test is passed");
		
		extent.flush();
	}
   
	@Test(priority = 3)
	public void testGoogleSignIn() throws InterruptedException {
		extent.attachReporter(reporter);
		ExtentTest test = extent.createTest("Google Sign In Test");
		
		googleSignIn signin = new googleSignIn(driver);
		
//		signin.moveToLogin();
		
		signin.clickSignIn();
		test.log(Status.INFO, "Sign In button is clicked");
		
		signin.googleSignin();
		test.log(Status.INFO, "Continue with Google button is clicked");
		
		signin.emailInput("abc@abc");
		test.log(Status.INFO, "An invalid email id is entered into Email field");
		
		signin.emailNext();
		test.log(Status.INFO, "Next button is clicked after entering the invalid email id");
		
		signin.getErrorMessage();
		test.log(Status.INFO, "Error message is captured successfully and displayed on console");
		
		test.log(Status.PASS, "Google Sign In test is passed");

		extent.flush();
		
	}

	@AfterClass
	public void closeDriver() throws IOException {
		
		outputExcel.closeExcel();
		driver.quit();
	}


}
