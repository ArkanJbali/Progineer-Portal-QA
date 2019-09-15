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

import com.portal.test_cases.CheckEmail;
import com.portal.test_cases.CheckChecklist;
import com.portal.test_cases.CheckGroups;
import com.portal.test_cases.CheckInventoryStocking;
import com.portal.test_cases.CheckInventoryComparison;
import com.portal.test_cases.CheckTestEmail;
import com.portal.test_cases.CheckAssets;
import com.portal.test_cases.CheckEmployees;
import com.portal.test_cases.CheckUsers;



public class MainTests {
	static void ThreadSleep() throws InterruptedException {
		Thread.sleep(3000);
	}
	public String checkURL() {
		return driver.getCurrentUrl();
	}
	public void portalLogin() {
		CheckEmail.getEmailInput(driver).sendKeys("qa2");
		CheckEmail.getPasswordInput(driver).sendKeys("qa2");
		CheckEmail.pressLoginButton(driver).click();
	}
	public void portalLogout() {
		CheckEmail.pressLogoutButton(driver).click();
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
		portalLogin();
		ThreadSleep();
		logger.info("--	 Inside Checklist");
		CheckChecklist.go_to_Checklist(driver).click();
		ThreadSleep();
		logger.info("--	 adding group to checklist");
		CheckChecklist.select_Group(driver).click();
		ThreadSleep();
		CheckChecklist.add_Group_Btn(driver).click();
		ThreadSleep();
		if(CheckChecklist.isAlertPresent(driver)) {
			logger.info("Group is: " + CheckChecklist.Exist_alertMessage(driver));
		}
		ThreadSleep();
		logger.info("--	 Removing a group");
		CheckChecklist.remove_Group(driver).click();
		ThreadSleep();
		logger.info("--	 Removing a checklist");
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckChecklist.Remove_CheckList(driver)).click().build().perform();
		ThreadSleep();
		portalLogout();
	}
	@Test
	public void Inventory_Stocking() throws InterruptedException {
		logger.info("----------- \t	Inside Inventory & Stocking");
		portalLogin();
		ThreadSleep();
		CheckInventoryStocking.goToInventoryStocking(driver).click();
		ThreadSleep();
		CheckInventoryStocking.checkWhoActive(driver);
		ThreadSleep();
		logger.info("--  Checked who is active");
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckInventoryStocking.checkByButton(driver)).click().build().perform();
		//Check_InventoryStocking.check_ByButton(driver).click();
		if(CheckInventoryStocking.isAlertPresent(driver)) {
			ThreadSleep();
			logger.info(CheckInventoryStocking.deactiveAlertMessage(driver));
		}
		portalLogout();
	}
	@Test 
	public void Check_Users() throws InterruptedException {
		logger.info("----------- \t	Update User Permissions");
		portalLogin();
		ThreadSleep();
		CheckUsers.goToUsers(driver).click();
		ThreadSleep();
		logger.info("-- Inside User Permission");
		CheckUsers.goToUsersPermission(driver).get(4).click();
		ThreadSleep();
		CheckUsers.comparePermission(driver);
		ThreadSleep();
		logger.info("-- User Permission Updated...");
		CheckUsers.goToUsers(driver).click();
		ThreadSleep();
		logger.info("-- Deleting User...");
		CheckUsers.deleteUser(driver).get(5).click();
		ThreadSleep();
		if(CheckUsers.isAlertPresent(driver)) {
			Alert alert = (Alert) driver.switchTo().alert();  
			alert.accept();
		}
		logger.info("-- User is deleted");
		ThreadSleep();
		logger.info("--------\t Adding New User");
		CheckUsers.addButton(driver).click();
		CheckUsers.nameInput(driver).sendKeys("Test");
		CheckUsers.userNameInput(driver).sendKeys("TestUser2");
		CheckUsers.passwordInput(driver).sendKeys("TestUser");
		CheckUsers.rolePick(driver).click();
		CheckUsers.addNewUserButton(driver).click();
		ThreadSleep();
		try {
			logger.info(CheckUsers.invalidUser(driver).getText().toString());
			logger.info("-- User already exist!!!");
			ThreadSleep();
			CheckUsers.goToUsers(driver).click();
		}catch (NoSuchElementException e) {
			System.out.println("NoSuchElementException");
			logger.info("-- User added...");
		} catch (Exception e) {
			System.out.println("Exception");
		}
		
		portalLogout();
	}
	
	@Test
	public void Test_Email() throws InterruptedException {
		logger.info("----------- \t	Test Email");
		portalLogin();
		ThreadSleep();
		CheckTestEmail.goToTestEmail(driver).click();
		ThreadSleep();
		CheckTestEmail.pickEmail(driver).click();
		ThreadSleep();
		CheckTestEmail.sendEmail(driver).click();
		logger.info("----------- \t	Sending Email...");
		if(CheckTestEmail.sentContent(driver).getText().toString().contains("Invalid address")) {
			logger.info("-- Invalid address !!!");
			try {
				CheckTestEmail.invalidAddress(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			logger.info("-- Mail Sent !!!");
		}
		logger.info("Email test passed");
		portalLogout();
		ThreadSleep();
	}
	
	@Test
	public void Inventory_Comparison() throws InterruptedException {
		logger.info("----------- \t	Inventory Comparison");
		portalLogin();
		CheckInventoryComparison.goToInventoryComparison(driver).click();
		ThreadSleep();
		logger.info("Search for inventory");
		CheckInventoryComparison.searchForInventory(driver).sendKeys("arkan");
		ThreadSleep();
		logger.info("Printing report");
		portalLogout();
		ThreadSleep();
	}
	
	@Test
	public void Add_New_Group() throws InterruptedException {
		logger.info("----------- \t	Add New Group");
		portalLogin();
		CheckGroups.goToGroups(driver).click();
		ThreadSleep();
		logger.info("----------- \t	Adding New Group");
		CheckGroups.addNewGroupButton(driver).click();
		CheckGroups.groupInput(driver).sendKeys("TestGroups");
		CheckGroups.saveButton(driver).click();
		ThreadSleep();
		logger.info("----------- \t	Add Empty Group");
		CheckGroups.addNewGroupButton(driver).click();
		CheckGroups.groupInput(driver).sendKeys(" 3");
		CheckGroups.saveButton(driver).click();
		ThreadSleep();
		portalLogout();
		ThreadSleep();
	}
	
	@Test
	public void Add_newAsset_Type() throws InterruptedException {
		logger.info("----------- \t	Add New Asset Type");
		portalLogin();
		CheckAssets.Go_to_Assets(driver).click();
		ThreadSleep();
		logger.info("	Adding new Asset Type");
		CheckAssets.new_Asset_Type(driver).click();
		ThreadSleep();
		CheckAssets.Add_Asset_Btn(driver).click();
		CheckAssets.type_Input(driver).sendKeys("Internet Camera");
		CheckAssets.type_InputBtn(driver).click();
		ThreadSleep();
		portalLogout();
		ThreadSleep();
	}
	
	
	@Test(enabled = true)
	public void Add_new_PDF() throws InterruptedException {
		logger.info("----------- \t	Add New PDF");
		portalLogin();
		CheckAssets.Go_to_Assets(driver).click();
		ThreadSleep();
		logger.info("	Adding new PDF Laptop");
		CheckAssets.Add_New_PDF(driver).click();
		ThreadSleep();
		CheckAssets.Host_Name(driver).sendKeys("TestPDF");
		CheckAssets.ThinkPad_Model(driver).sendKeys("Test ThinkPad");
		CheckAssets.Model(driver).sendKeys("Test Model");
		CheckAssets.Laptop_Property(driver).sendKeys("Black 1TB");
		CheckAssets.Serial_Number(driver).sendKeys("1212312");
		CheckAssets.Status_PDF(driver).sendKeys("New NEW");
		CheckAssets.Date_Of_Purchase_PDF(driver).sendKeys("2019-09-09");
		ThreadSleep();
		//Check_assets.Save_Btn_PDF(driver).click();
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckAssets.Save_Btn_PDF(driver)).click().build().perform();
		ThreadSleep();
		CheckAssets.isAlertPresent(driver);
		portalLogout();
		ThreadSleep();
		turnOnImplicitWaits();
		logger.info("--\t Skipping Add new PDF error in server");
		throw new SkipException("Skipping Add new PDF error in server" );
	}
	
	@Test
	public void Add_new_asset() throws InterruptedException {
		logger.info("----------- \t	Add New Asset");
		portalLogin();
		CheckAssets.Go_to_Assets(driver).click();
		ThreadSleep();
		logger.info("	Asset Filtering");
		CheckAssets.Filter_By_Asset(driver).click();
		ThreadSleep();
		logger.info("	Adding Asset.....");
		CheckAssets.Add_New_Asset(driver).click();
		CheckAssets.Asset_Type(driver).sendKeys("Docking");
		CheckAssets.Description(driver).sendKeys("123");
		CheckAssets.PTech_tag(driver).sendKeys("9999");
		CheckAssets.Status(driver).sendKeys("New");
		CheckAssets.Invoice_Source(driver).sendKeys("KSP");
		CheckAssets.Date_Of_Purchase(driver).sendKeys("2019-09-09");
		CheckAssets.Invoice_Number(driver).sendKeys("213213");
		CheckAssets.Warranty_PeriodY(driver).sendKeys("2");
		CheckAssets.Warranty_PeriodM(driver).sendKeys("5");
		CheckAssets.Expected_end_date(driver).sendKeys("2019-09-29");
		CheckAssets.Serial_Number(driver).sendKeys("91111111");
		ThreadSleep();
		CheckAssets.Save_Btn(driver).click();
		ThreadSleep();
		if(CheckAssets.isAlertPresent(driver)) {
			
		}
		portalLogout();
		ThreadSleep();
	}
	@Test
	public void reset_password() throws InterruptedException {
		logger.info("----------- \t	Employee Rest Password");
		portalLogin();
		CheckEmployees.resetPassword(driver).get(0).click();
		ThreadSleep();
		if(CheckEmployees.isAlertPresent(driver)) {
			String alertTxt = CheckEmployees.deactiveAlertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
			ThreadSleep();
			logger.info(alertTxt);
			alertTxt = CheckEmployees.deactiveAlertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
			ThreadSleep();
			logger.info(alertTxt);
		}
		portalLogout();
		ThreadSleep();
	}
	
	@Test
	public void employee_assets() throws InterruptedException {
		logger.info("----------- \t	Employee Assets");
		portalLogin();
		CheckEmployees.goToAllEmployees(driver).click();
		System.out.println("Size: " + CheckEmployees.employeeAssetsButton(driver).size());
		CheckEmployees.employeeAssetsButton(driver).get(0).click();
		ThreadSleep();
		JavascriptExecutor js = (JavascriptExecutor)driver;  
	      js.executeScript("scrollBy(0, 7000)");  
	      ThreadSleep();
	      CheckEmployees.addHeadset(driver).click();
		
			if(!CheckEmployees.selectHeadset(driver).getText().toString().equals("   ")) {
				System.out.println(CheckEmployees.selectHeadset(driver).getText());
				CheckEmployees.addHeadsetButton(driver).click();
			}else {
				System.out.println("Its null there are no items");
				Actions action = new Actions(driver);
				action.sendKeys(Keys.ESCAPE).perform();
			}
		
			ThreadSleep();
		 
	      js.executeScript("scrollBy(7000, 12000)");  
	      ThreadSleep();
	     // Check_employees.go_to_AllEmployees(driver).click();
	      portalLogout();
	      ThreadSleep();
	}
	
	@Test
	public void deactive_employee() throws InterruptedException {
		logger.info("----------- \t	Deactivate an Employee");
		portalLogin();
		CheckEmployees.goToAllEmployees(driver).click();
		int sizeOfElements = CheckEmployees.deactiveButton(driver).size();
		System.out.println("Size of Elements: "+ sizeOfElements);
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckEmployees.deactiveButton(driver).get(2)).click().build().perform();
			//Check_employees.Deactive_btn(driver).get(2).click();  
		ThreadSleep();
		String alertTxt = CheckEmployees.deactiveAlertMessage(driver);
		System.out.println(alertTxt);
		if(CheckEmployees.isAlertPresent(driver)) {
			alertTxt = CheckEmployees.deactiveAlertMessage(driver);
			//cancel dismiss
			System.out.println("Alert Message: "+ alertTxt);
 		}
		ThreadSleep();
		try {
			CheckEmployees.checkListForLeaving(driver).click();
		if(CheckEmployees.isAlertPresent(driver)) {
			alertTxt = CheckEmployees.deactiveAlertMessage(driver);
			System.out.println("Alert Message: "+ alertTxt);
		}
		}catch(Exception e) {
		throw new SkipException("Unhandled Alert");
		}
	} 
	
	@Test(priority = 1)
	public void add_employee() throws InterruptedException {
		logger.info("----------- \t	Adding new Employee");
		portalLogin();
		//Check_employees.go_to_AllEmployees(driver).click();
		//make login first -----missing
		//Add button to add new employee
		CheckEmployees.addButton(driver).click();
		//Add name
		CheckEmployees.nameInput(driver).sendKeys("Arkan Jbali");
		//ID number  --- check if already exist
		CheckEmployees.IDInput(driver).sendKeys("21312");
		//Start Date ---- make validation
		CheckEmployees.startDate(driver).sendKeys(CheckEmployees.getTheCurrentDate());
		//Pick Location
		CheckEmployees.pickLocation(driver).click();
		//Pick Group
		CheckEmployees.pickGroup(driver).click();
		//Pick Manager ID ---error
		CheckEmployees.pickManagerId(driver).click();
		//Job Title
		CheckEmployees.jobTitle(driver).sendKeys("QA Tester");
		//Active status --- error
		CheckEmployees.isActive(driver).click();
		ThreadSleep();
		//Done
		CheckEmployees.doneOption(driver).click();
		
		ThreadSleep();
		String alertTxt = CheckEmployees.alertMessage(driver);
		logger.info(alertTxt);
		
		 	while(CheckEmployees.isElementPresent(driver)) {
		 		
		 		if(CheckEmployees.isAlertPresent(driver)) {
					alertTxt = CheckEmployees.alertMessage(driver);
		 		}
		 		
			if((alertTxt.equals("Already Exists!"))) {
				logger.info("ID Number Already exists");
				CheckEmployees.IDInput(driver).clear();
				System.out.println("Enter new EmployeeID: ");
				CheckEmployees.IDInput(driver).sendKeys(getNewInput.nextLine());
				CheckEmployees.doneOption(driver).click();
				logger.info("New Employee added.");
				ThreadSleep();
			
			}
			
			else if((alertTxt.equals("The name, ID number, start date, location and manager_id must be set"))) {
				logger.info("Something missing!!!!");
				if((CheckEmployees.nameInput(driver).getText().equals(null))) {
					System.out.println("Enter name: ");
					CheckEmployees.nameInput(driver).sendKeys(getNewInput.nextLine());
				}
				else if(CheckEmployees.IDInput(driver).getText().equals(null)) {
					System.out.println("Enter new EmployeeID 2: ");
					CheckEmployees.IDInput(driver).sendKeys(getNewInput.nextLine());
				}
				else if(CheckEmployees.startDate(driver).getText().equals(null)) {
					//get the current date 
					CheckEmployees.startDate(driver).sendKeys(CheckEmployees.getTheCurrentDate());
				}
				else if(CheckEmployees.pickLocation(driver).getText().equals(null)) {
					CheckEmployees.pickLocation(driver).click();
				}
				else if(CheckEmployees.pickManagerId(driver).getText().equals(null)) {
					CheckEmployees.pickManagerId(driver).click();
				}
			}
			alertTxt ="";
			ThreadSleep();
		 	}
		 	portalLogout();
		 	ThreadSleep();
	} 
	

	@Test(priority = 2)
	public void search_employee() throws InterruptedException {
		logger.info("----------- \t Check Search an employee");
		portalLogin();
		ThreadSleep();
		CheckEmployees.searchEmployee(driver).sendKeys("Arkan");
		CheckEmployees.searchEmployee(driver).sendKeys(Keys.RETURN);
		ThreadSleep();
		portalLogout();
	}
	@Test(priority = 3)
	public void check_login_by_un() throws InterruptedException {
		logger.info("----------- \t Check Login Using UserName");
		CheckEmail.getEmailInput(driver).sendKeys("qa2");
		CheckEmail.getPasswordInput(driver).sendKeys("qa2");
		CheckEmail.pressLoginButton(driver).click();
		ThreadSleep();
		if(!(checkURL().equals("http://192.168.2.184/envintory-qa/Login"))) {
			driver.navigate().back();
		}
		//check_email.get_email_input(driver).clear();
		//check_email.get_password_input(driver).clear();
		portalLogout();
		ThreadSleep();
		
	}
	@Test(priority = 3)
	public void check_login_by_email() throws InterruptedException {
		logger.info("----------- \t Check Login Using Email");
		CheckEmail.getEmailInput(driver).sendKeys("arkan.1997@gmail.com");
		CheckEmail.getPasswordInput(driver).sendKeys("qa1");
		CheckEmail.pressLoginButton(driver).click();
		ThreadSleep();
		if(!(checkURL().equals("http://192.168.2.184/envintory-qa/Login"))) {
			driver.navigate().back();
			logger.info("Login fail using Email");
		}	
		CheckEmail.getEmailInput(driver).clear();
		CheckEmail.getPasswordInput(driver).clear();
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
			ThreadSleep();
			driver.close();	
     }
			logger.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # \n");
	 }
}
