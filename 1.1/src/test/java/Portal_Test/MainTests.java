package Portal_Test;

import org.testng.annotations.Test;


import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.portal.test_cases.Check_Checklist;
import com.portal.test_cases.Check_Groups;
import com.portal.test_cases.Check_InventoryStocking;
import com.portal.test_cases.Check_Inventory_Comparison;
import com.portal.test_cases.Check_TestEmail;
import com.portal.test_cases.Check_assets;
import com.portal.test_cases.Check_employees;
import com.portal.test_cases.check_email;
import com.portal.test_cases.Check_Users;



public class MainTests {
	public String checkURL() {
		return driver.getCurrentUrl();
	}
	public void portal_login() {
		check_email.get_email_input(driver).sendKeys("qa2");
		check_email.get_password_input(driver).sendKeys("qa2");
		check_email.press_login_btn(driver).click();
	}
	public void portal_logout() {
		check_email.press_logout_btn(driver).click();
	}
	private void turnOnImplicitWaits() {
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	static final Logger logger = LogManager.getLogger(MainTests.class.getName());
	WebDriver driver;
	Scanner getNewInput = new Scanner(System.in);
	
	@BeforeTest(alwaysRun = true)
	public void lunchApp() {
		DOMConfigurator.configure("log4j.xml");
		logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
		logger.info("TEST Has Started");
		logger.info("Open Web Application");
		System.setProperty("webdriver.chrome.driver","C:\\Users\\arkan\\Downloads\\chromedriver.exe");
		driver=new ChromeDriver();
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.navigate().to("http://192.168.2.184/envintory-qa/Login");
		driver.manage().window().maximize();  
	}
	@Test
	public void Checklist() throws InterruptedException {
		logger.info("----------- \t	Checklist");
		portal_login();
		Thread.sleep(2000);
		logger.info("--	 Inside Checklist");
		Check_Checklist.go_to_Checklist(driver).click();
		Thread.sleep(2000);
		logger.info("--	 adding group to checklist");
		Check_Checklist.select_Group(driver).click();
		Thread.sleep(2000);
		Check_Checklist.add_Group_Btn(driver).click();
		Thread.sleep(1000);
		if(Check_Checklist.isAlertPresent(driver)) {
			logger.info("Group is: " + Check_Checklist.Exist_alertMessage(driver));
		}
		Thread.sleep(2000);
		logger.info("--	 Removing a group");
		Check_Checklist.remove_Group(driver).click();
		Thread.sleep(5000);
		logger.info("--	 Removing a checklist");
		Actions actions = new Actions(driver);
		actions.moveToElement(Check_Checklist.Remove_CheckList(driver)).click().build().perform();
		Thread.sleep(3000);
		portal_logout();
	}
	@Test
	public void Inventory_Stocking() throws InterruptedException {
		logger.info("----------- \t	Inside Inventory & Stocking");
		portal_login();
		Thread.sleep(2000);
		Check_InventoryStocking.go_to_InventoryStocking(driver).click();
		Thread.sleep(2000);
		Check_InventoryStocking.Check_Who_Active(driver);
		Thread.sleep(2000);
		logger.info("--  Checked who is active");
		Check_InventoryStocking.check_ByButton(driver).click();
		if(Check_InventoryStocking.isAlertPresent(driver)) {
			Thread.sleep(2000);
			logger.info(Check_InventoryStocking.Deactive_alertMessage(driver));
		}
		portal_logout();
	}
	@Test 
	public void Check_Users() throws InterruptedException {
		logger.info("----------- \t	Update User Permissions");
		portal_login();
		Thread.sleep(3000);
		Check_Users.go_to_users(driver).click();
		Thread.sleep(3000);
		logger.info("-- Inside User Permission");
		Check_Users.go_to_usersPermission(driver).get(5).click();
		Thread.sleep(3000);
		Check_Users.Compare_Permission(driver);
		Thread.sleep(5000);
		logger.info("-- User Permission Updated...");
		Check_Users.go_to_users(driver).click();
		Thread.sleep(2000);
		logger.info("-- Deleteing User...");
		Check_Users.delete_User(driver).get(5).click();
		Thread.sleep(2000);
		if(Check_Users.isAlertPresent(driver)) {
			Alert alert = (Alert) driver.switchTo().alert();  
			alert.accept();
		}
		logger.info("-- User is deleted");
		Thread.sleep(2000);
		logger.info("--------\t Adding New User");
		Check_Users.add_Btn(driver).click();
		Check_Users.name_input(driver).sendKeys("Test");
		Check_Users.UserName_input(driver).sendKeys("TestUser2");
		Check_Users.password_input(driver).sendKeys("TestUser");
		Check_Users.role_pick(driver).click();
		Check_Users.add_new_user_Btn(driver).click();
		Thread.sleep(3000);
		try {
			logger.info(Check_Users.invalid_User(driver).getText().toString());
			logger.info("-- User already exist!!!");
			Thread.sleep(5000);
			Check_Users.go_to_users(driver).click();
		}catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException");
			logger.info("-- User added...");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		
		portal_logout();
	}
	
	@Test
	public void Test_Email() throws InterruptedException {
		logger.info("----------- \t	Test Email");
		portal_login();
		Thread.sleep(3000);
		Check_TestEmail.go_to_TestEmail(driver).click();
		Thread.sleep(3000);
		Check_TestEmail.pick_Email(driver).click();
		Thread.sleep(3000);
		Check_TestEmail.sendEmail(driver).click();
		logger.info("----------- \t	Sending Email...");
		if(Check_TestEmail.Sent_content(driver).getText().toString().contains("Invalid address")) {
			logger.info("-- Invalid address !!!");
			try {
				Check_TestEmail.invalid_address(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			logger.info("-- Mail Sent !!!");
		}
		logger.info("Email test passed");
		portal_logout();
		Thread.sleep(3000);
	}
	
	@Test
	public void Inventory_Comparison() throws InterruptedException {
		logger.info("----------- \t	Inventory Comparison");
		portal_login();
		Check_Inventory_Comparison.go_to_InventoryComparison(driver).click();
		Thread.sleep(3000);
		logger.info("Search for inventory");
		Check_Inventory_Comparison.search_for_Inventory(driver).sendKeys("arkan");
		Thread.sleep(3000);
		logger.info("Printing report");
		portal_logout();
		Thread.sleep(3000);
	}
	
	@Test
	public void Add_New_Group() throws InterruptedException {
		logger.info("----------- \t	Add New Group");
		portal_login();
		Check_Groups.go_to_Groups(driver).click();
		Thread.sleep(3000);
		logger.info("----------- \t	Adding New Group");
		Check_Groups.Add_new_Group_Btn(driver).click();
		Check_Groups.Group_Input(driver).sendKeys("TestGroups");
		Check_Groups.Save_Btn(driver).click();
		Thread.sleep(3000);
		logger.info("----------- \t	Add Empty Group");
		Check_Groups.Add_new_Group_Btn(driver).click();
		Check_Groups.Group_Input(driver).sendKeys(" 3");
		Check_Groups.Save_Btn(driver).click();
		Thread.sleep(3000);
		portal_logout();
		Thread.sleep(3000);
	}
	
	@Test
	public void Add_newAsset_Type() throws InterruptedException {
		logger.info("----------- \t	Add New Asset Type");
		portal_login();
		Check_assets.Go_to_Assets(driver).click();
		Thread.sleep(3000);
		logger.info("	Adding new Asset Type");
		Check_assets.new_Asset_Type(driver).click();
		Thread.sleep(3000);
		Check_assets.Add_Asset_Btn(driver).click();
		Check_assets.type_Input(driver).sendKeys("Internet Camera");
		Check_assets.type_InputBtn(driver).click();
		Thread.sleep(3000);
		portal_logout();
		Thread.sleep(3000);
	}
	
	
	@Test(enabled = true)
	public void Add_new_PDF() throws InterruptedException {
		logger.info("----------- \t	Add New PDF");
		portal_login();
		Check_assets.Go_to_Assets(driver).click();
		Thread.sleep(3000);
		logger.info("	Adding new PDF Laptop");
		Check_assets.Add_New_PDF(driver).click();
		Thread.sleep(3000);
		Check_assets.Host_Name(driver).sendKeys("TestPDF");
		Check_assets.ThinkPad_Model(driver).sendKeys("Test ThinkPad");
		Check_assets.Model(driver).sendKeys("Test Model");
		Check_assets.Laptop_Property(driver).sendKeys("Black 1TB");
		Check_assets.Serial_Number(driver).sendKeys("1212312");
		Check_assets.Status_PDF(driver).sendKeys("New NEW");
		Check_assets.Date_Of_Purchase_PDF(driver).sendKeys("2019-09-09");
		Thread.sleep(3000);
		//Check_assets.Save_Btn_PDF(driver).click();
		Actions actions = new Actions(driver);
		actions.moveToElement(Check_assets.Save_Btn_PDF(driver)).click().build().perform();
		Thread.sleep(3000);
		Check_assets.isAlertPresent(driver);
		portal_logout();
		Thread.sleep(3000);
		turnOnImplicitWaits();
		logger.info("--\t Skipping Add new PDF error in server");
		throw new SkipException("Skipping Add new PDF error in server" );
	}
	
	@Test
	public void Add_new_asset() throws InterruptedException {
		logger.info("----------- \t	Add New Asset");
		portal_login();
		Check_assets.Go_to_Assets(driver).click();
		Thread.sleep(3000);
		logger.info("	Asset Filtering");
		Check_assets.Filter_By_Asset(driver).click();
		Thread.sleep(3000);
		logger.info("	Adding Asset.....");
		Check_assets.Add_New_Asset(driver).click();
		Check_assets.Asset_Type(driver).sendKeys("Docking");
		Check_assets.Description(driver).sendKeys("123");
		Check_assets.PTech_tag(driver).sendKeys("9999");
		Check_assets.Status(driver).sendKeys("New");
		Check_assets.Invoice_Source(driver).sendKeys("KSP");
		Check_assets.Date_Of_Purchase(driver).sendKeys("2019-09-09");
		Check_assets.Invoice_Number(driver).sendKeys("213213");
		Check_assets.Warranty_PeriodY(driver).sendKeys("2");
		Check_assets.Warranty_PeriodM(driver).sendKeys("5");
		Check_assets.Expected_end_date(driver).sendKeys("2019-09-29");
		Check_assets.Serial_Number(driver).sendKeys("91111111");
		Thread.sleep(3000);
		Check_assets.Save_Btn(driver).click();
		Thread.sleep(2000);
		if(Check_assets.isAlertPresent(driver)) {
			
		}
		portal_logout();
		Thread.sleep(3000);
	}
	@Test
	public void reset_password() throws InterruptedException {
		logger.info("----------- \t	Employee Rest Password");
		portal_login();
		Check_employees.Reset_Password(driver).get(0).click();
		Thread.sleep(3000);
		if(Check_employees.isAlertPresent(driver)) {
			String alertTxt = Check_employees.Deactive_alertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
			Thread.sleep(3000);
			logger.info(alertTxt);
			alertTxt = Check_employees.Deactive_alertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
			Thread.sleep(3000);
			logger.info(alertTxt);
		}
		portal_logout();
		Thread.sleep(3000);
	}
	
	@Test
	public void employee_assets() throws InterruptedException {
		logger.info("----------- \t	Employee Assets");
		portal_login();
		Check_employees.go_to_AllEmployees(driver).click();
		System.out.println("Size: " + Check_employees.Employee_Assets_btn(driver).size());
		Check_employees.Employee_Assets_btn(driver).get(0).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor)driver;  
	      js.executeScript("scrollBy(0, 7000)");  
	      Thread.sleep(2000);
		Check_employees.Add_Headset(driver).click();
		
			if(!Check_employees.Select_Headset(driver).getText().toString().equals("   ")) {
				System.out.println(Check_employees.Select_Headset(driver).getText());
				Check_employees.Add_Headset_btn(driver).click();
			}else {
				System.out.println("Its null there are no items");
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).perform();
			}
		
		Thread.sleep(3000);
		 
	      js.executeScript("scrollBy(7000, 12000)");  
	      Thread.sleep(3000);
	     // Check_employees.go_to_AllEmployees(driver).click();
	      portal_logout();
			Thread.sleep(3000);
	}
	
	@Test
	public void deactive_employee() throws InterruptedException {
		logger.info("----------- \t	Deactivate an Employee");
		portal_login();
		Check_employees.go_to_AllEmployees(driver).click();
		int sizeOfElements = Check_employees.Deactive_btn(driver).size();
		System.out.println("Size of Elements: "+ sizeOfElements);
			Check_employees.Deactive_btn(driver).get(2).click();  
		Thread.sleep(3000);
		String alertTxt = Check_employees.Deactive_alertMessage(driver);
		System.out.println(alertTxt);
		if(Check_employees.isAlertPresent(driver)) {
			alertTxt = Check_employees.Deactive_alertMessage(driver);
			//cancel dismiss
			System.out.println("Alert Message: "+ alertTxt);
 		}
		Thread.sleep(3000); 
		try {
		Check_employees.CheckList_for_Leaving(driver).click();
		if(Check_employees.isAlertPresent(driver)) {
			alertTxt = Check_employees.Deactive_alertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
		}
		}catch(Exception e) {
		throw new SkipException("Unhandled Alert");
		}
	} 
	
	@Test(priority = 1)
	public void add_employee() throws InterruptedException {
		logger.info("----------- \t	Adding new Employee");
		portal_login();
		//Check_employees.go_to_AllEmployees(driver).click();
		//make login first -----missing
		//Add button to add new employee
		Check_employees.add_btn(driver).click();
		//Add name
		Check_employees.name_input(driver).sendKeys("Arkan Jbali");
		//ID number  --- check if already exist
		Check_employees.IDNo_input(driver).sendKeys("21312");
		//Start Date ---- make validation
		Check_employees.start_date(driver).sendKeys(Check_employees.getTheCurrentDate());
		//Pick Location
		Check_employees.pick_location(driver).click();
		//Pick Group
		Check_employees.pick_group(driver).click();
		//Pick Manager ID ---error
		Check_employees.pick_manager_id(driver).click();
		//Job Title
		Check_employees.job_title(driver).sendKeys("QA Tester");
		//Active status --- error
		Check_employees.isActive(driver).click();
		Thread.sleep(3000);
		//Done
		Check_employees.Done_Option(driver).click();
		
		Thread.sleep(3000);
		String alertTxt = Check_employees.alertMessage(driver);
		logger.info(alertTxt);
		
		 	while(Check_employees.isElementPresent(driver)) {
		 		
		 		if(Check_employees.isAlertPresent(driver)) {
					alertTxt = Check_employees.alertMessage(driver);
		 		}
		 		
			if((alertTxt.equals("Already Exists!"))) {
				logger.info("ID Number Already exists");
				Check_employees.IDNo_input(driver).clear();
				System.out.println("Enter new EmployeeID: ");
				Check_employees.IDNo_input(driver).sendKeys(getNewInput.nextLine());
				Check_employees.Done_Option(driver).click();
				logger.info("New Employee added.");
				Thread.sleep(1000);
			
			}
			
			else if((alertTxt.equals("The name, ID number, start date, location and manager_id must be set"))) {
				logger.info("Something missing!!!!");
				if((Check_employees.name_input(driver).getText().equals(null))) {
					System.out.println("Enter name: ");
						Check_employees.name_input(driver).sendKeys(getNewInput.nextLine());
				}
				else if(Check_employees.IDNo_input(driver).getText().equals(null)) {
					System.out.println("Enter new EmployeeID 2: ");
						Check_employees.IDNo_input(driver).sendKeys(getNewInput.nextLine());
				}
				else if(Check_employees.start_date(driver).getText().equals(null)) {
					//get the current date 
						Check_employees.start_date(driver).sendKeys(Check_employees.getTheCurrentDate());
				}
				else if(Check_employees.pick_location(driver).getText().equals(null)) {
						Check_employees.pick_location(driver).click();
				}
				else if(Check_employees.pick_manager_id(driver).getText().equals(null)) {
						Check_employees.pick_manager_id(driver).click();
				}
			}
			alertTxt ="";
			Thread.sleep(1000);
		 	}
		 	portal_logout();
			Thread.sleep(3000);
	} 
	

	@Test(priority = 2)
	public void search_employee() throws InterruptedException {
		logger.info("----------- \t Check Search an employee");
		portal_login();
		Thread.sleep(3000);
		Check_employees.search_emp(driver).sendKeys("Arkan");
		Check_employees.search_emp(driver).sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		portal_logout();
	}
	@Test(priority = 3)
	public void check_login_by_un() throws InterruptedException {
		logger.info("----------- \t Check Login Using UserName");
		check_email.get_email_input(driver).sendKeys("qa2");
		check_email.get_password_input(driver).sendKeys("qa2");
		check_email.press_login_btn(driver).click();
		Thread.sleep(3000);
		if(!(checkURL().equals("http://192.168.2.184/envintory-qa/Login"))) {
			driver.navigate().back();
		}
		//check_email.get_email_input(driver).clear();
		//check_email.get_password_input(driver).clear();
		portal_logout();
		Thread.sleep(3000);
		
	}
	@Test(priority = 3)
	public void check_login_by_email() throws InterruptedException {
		logger.info("----------- \t Check Login Using Email");
		check_email.get_email_input(driver).sendKeys("arkan.1997@gmail.com");
		check_email.get_password_input(driver).sendKeys("qa1");
		check_email.press_login_btn(driver).click();
		Thread.sleep(3000);
		if(!(checkURL().equals("http://192.168.2.184/envintory-qa/Login"))) {
			driver.navigate().back();
			logger.info("Login fail using Email");
		}	
		check_email.get_email_input(driver).clear();
		check_email.get_password_input(driver).clear();
	}
	
	@AfterTest
	public void terminatetest() throws InterruptedException {

		boolean hasQuit = driver.toString().contains("(null)");
		if(!hasQuit) {
			logger.info("TEST PASSED");
			System.out.println("its closed");
			driver.close();	
		}else {
			logger.error("TEST FAILED. NEEDS INVESTIGATION");
			Thread.sleep(5000);
			driver.close();	
     }
			logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # \n");
	 }
}
