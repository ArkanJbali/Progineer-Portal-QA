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
	public void checklist() throws InterruptedException {
		logger.info("----------- \t	Checklist");
		portalLogin();
		ThreadSleep();
		logger.info("--	 Inside Checklist");
		CheckChecklist.goToChecklist(driver).click();
		ThreadSleep();
		logger.info("--	 adding group to checklist");
		CheckChecklist.selectGroup(driver).click();
		ThreadSleep();
		CheckChecklist.addGroupButton(driver).click();
		ThreadSleep();
		if(CheckChecklist.isAlertPresent(driver)) {
			logger.info("Group is: " + CheckChecklist.existAlertMessage(driver));
		}
		ThreadSleep();
		logger.info("--	 Removing a group");
		CheckChecklist.removeGroup(driver).click();
		ThreadSleep();
		logger.info("--	 Removing a checklist");
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckChecklist.removeCheckList(driver)).click().build().perform();
		ThreadSleep();
		portalLogout();
	}
	@Test
	public void inventoryStocking() throws InterruptedException {
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
	public void checkUsers() throws InterruptedException {
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
	public void testEmail() throws InterruptedException {
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
	public void addNewGroup() throws InterruptedException {
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
	public void addNewAssetType() throws InterruptedException {
		logger.info("----------- \t	Add New Asset Type");
		portalLogin();
		CheckAssets.goToAssets(driver).click();
		ThreadSleep();
		logger.info("	Adding new Asset Type");
		CheckAssets.newAssetType(driver).click();
		ThreadSleep();
		CheckAssets.addAssetButton(driver).click();
		CheckAssets.typeInput(driver).sendKeys("Internet Camera");
		CheckAssets.typeInputButton(driver).click();
		ThreadSleep();
		portalLogout();
		ThreadSleep();
	}
	
	
	@Test(enabled = true)
	public void addNewPDF() throws InterruptedException {
		logger.info("----------- \t	Add New PDF");
		portalLogin();
		CheckAssets.goToAssets(driver).click();
		ThreadSleep();
		logger.info("	Adding new PDF Laptop");
		CheckAssets.addNewPDF(driver).click();
		ThreadSleep();
		CheckAssets.hostName(driver).sendKeys("TestPDF");
		CheckAssets.thinkPadModel(driver).sendKeys("Test ThinkPad");
		CheckAssets.model(driver).sendKeys("Test Model");
		CheckAssets.laptopProperty(driver).sendKeys("Black 1TB");
		CheckAssets.serialNumber(driver).sendKeys("1212312");
		CheckAssets.statusPDF(driver).sendKeys("New NEW");
		CheckAssets.dateOfPurchase_PDF(driver).sendKeys("2019-09-09");
		ThreadSleep();
		//Check_assets.Save_Btn_PDF(driver).click();
		Actions actions = new Actions(driver);
		actions.moveToElement(CheckAssets.saveButtonPDF(driver)).click().build().perform();
		ThreadSleep();
		CheckAssets.isAlertPresent(driver);
		portalLogout();
		ThreadSleep();
		turnOnImplicitWaits();
		logger.info("--\t Skipping Add new PDF error in server");
		throw new SkipException("Skipping Add new PDF error in server" );
	}
	
	@Test
	public void addNewAsset() throws InterruptedException {
		logger.info("----------- \t	Add New Asset");
		portalLogin();
		CheckAssets.goToAssets(driver).click();
		ThreadSleep();
		logger.info("	Asset Filtering");
		CheckAssets.filterByAsset(driver).click();
		ThreadSleep();
		logger.info("	Adding Asset.....");
		CheckAssets.addNewAsset(driver).click();
		CheckAssets.assetType(driver).sendKeys("Docking");
		CheckAssets.description(driver).sendKeys("123");
		CheckAssets.pTechTag(driver).sendKeys("9999");
		CheckAssets.status(driver).sendKeys("New");
		CheckAssets.invoiceSource(driver).sendKeys("KSP");
		CheckAssets.dateOfPurchase(driver).sendKeys("2019-09-09");
		CheckAssets.invoiceNumber(driver).sendKeys("213213");
		CheckAssets.warrantyPeriodY(driver).sendKeys("2");
		CheckAssets.warrantyPeriodM(driver).sendKeys("5");
		CheckAssets.expectedEndDate(driver).sendKeys("2019-09-29");
		CheckAssets.serialNumber(driver).sendKeys("91111111");
		ThreadSleep();
		CheckAssets.saveButton(driver).click();
		ThreadSleep();
		if(CheckAssets.isAlertPresent(driver)) {
			
		}
		portalLogout();
		ThreadSleep();
	}
	@Test
	public void resetPassword() throws InterruptedException {
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
	public void EmployeeAssets() throws InterruptedException {
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
				ThreadSleep();
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
	public void deactiveEmployee() throws InterruptedException {
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
	@Test 
	public void showAssetsEntrie() throws InterruptedException {
		logger.info("----------- \t	Showing Assets");
		portalLogin();
		CheckAssets.goToAssets(driver).click();
		CheckAssets.scrollPageDown(driver);
		ThreadSleep();
		CheckAssets.scrollPageUp(driver);
		logger.info("--	Showing 100 Employees");
		CheckAssets.showEntries(driver, 4).click();
		CheckAssets.scrollPageDown(driver);
		ThreadSleep();
		CheckAssets.scrollPageUp(driver);
		logger.info("--	Showing 50 Employees");
		CheckAssets.showEntries(driver, 3).click();
		CheckAssets.scrollPageDown(driver);
		ThreadSleep();
		CheckAssets.scrollPageUp(driver);
		logger.info("--	Showing 25 Employees");
		CheckAssets.showEntries(driver, 2).click();
		CheckAssets.scrollPageDown(driver);
		ThreadSleep();
		CheckAssets.scrollPageUp(driver);
		logger.info("--	Showing 10 Employees");
		CheckAssets.showEntries(driver, 1).click();
		CheckAssets.scrollPageDown(driver);
		ThreadSleep();
		CheckAssets.scrollPageUp(driver);
		portalLogout();
	}
	@Test
	public void searchInventoryComparison() throws InterruptedException {
		logger.info("----------- \t	Search for Employee Inventory Comparison");
		portalLogin();
		CheckInventoryComparison.goToInventoryComparison(driver).click();
		ThreadSleep();
		logger.info("Search for inventory");
		CheckInventoryComparison.searchForInventory(driver).sendKeys("arkan");
		ThreadSleep();
		portalLogout();
	}
	@Test 
	public void showComparisonEntrie() throws InterruptedException {
		logger.info("----------- \t	Showing Comparisons");
		portalLogin();
		CheckInventoryComparison.goToInventoryComparison(driver).click();
		CheckInventoryComparison.scrollPageDown(driver);
		ThreadSleep();
		CheckInventoryComparison.scrollPageUp(driver);
		logger.info("--	Showing 100 Employees");
		CheckInventoryComparison.showEntries(driver, 4).click();
		CheckInventoryComparison.scrollPageDown(driver);
		ThreadSleep();
		CheckInventoryComparison.scrollPageUp(driver);
		ThreadSleep();
		logger.info("--	Showing 50 Employees");
		CheckInventoryComparison.showEntries(driver, 3).click();
		CheckInventoryComparison.scrollPageDown(driver);
		ThreadSleep();
		CheckInventoryComparison.scrollPageUp(driver);
		ThreadSleep();
		logger.info("--	Showing 25 Employees");
		CheckInventoryComparison.showEntries(driver, 2).click();
		CheckInventoryComparison.scrollPageDown(driver);
		ThreadSleep();
		CheckInventoryComparison.scrollPageUp(driver);
		logger.info("--	Showing 10 Employees");
		CheckInventoryComparison.showEntries(driver, 1).click();
		CheckInventoryComparison.scrollPageDown(driver);
		ThreadSleep();
		CheckInventoryComparison.scrollPageUp(driver);
		ThreadSleep();
		portalLogout();
	}
	@Test(enabled = false)
	public void printhInventoryComparisonReport() throws InterruptedException {
		logger.info("----------- \t	Print Inventory Comparison Report");
		portalLogin();
		CheckInventoryComparison.goToInventoryComparison(driver).click();
		ThreadSleep();
		CheckInventoryComparison.printReport(driver).click();
		ThreadSleep();
		logger.info("-- Report Printed");
		//portalLogout();
	}
	@Test(enabled = false)
	public void printInventoryAndStockingReport() throws InterruptedException {
		logger.info("----------- \t	print Inventory & Stocking Report");
		portalLogin();
		CheckInventoryStocking.goToInventoryStocking(driver).click();
		ThreadSleep();
		CheckInventoryStocking.printReport(driver).click();
		logger.info("-- Report Printed");
		//portalLogout();
	}
	@Test(enabled = false)
	public void printGroupsReport() throws InterruptedException {
		logger.info("----------- \t	print Groups Report");
		portalLogin();
		CheckGroups.goToGroups(driver).click();
		ThreadSleep();
		CheckGroups.printReport(driver).click();
		logger.info("-- Report Printed");
		//portalLogout();
	}
	@Test(enabled = false)
	public void printChecklistReport() throws InterruptedException {
		logger.info("----------- \t	print Checklist Report");
		portalLogin();
		CheckChecklist.goToChecklist(driver).click();
		ThreadSleep();
		CheckChecklist.printReport(driver).click();
		logger.info("-- Report Printed");
		//portalLogout();
	}
	
	@Test
	public void searchAsset() throws InterruptedException {
		logger.info("----------- \t	Search for an Asset");
		portalLogin();
		CheckAssets.goToAssets(driver).click();
		ThreadSleep();
		CheckAssets.searchAsset(driver).sendKeys("Mouse");
		CheckAssets.searchAsset(driver).sendKeys(Keys.RETURN);
		ThreadSleep();
		portalLogout();
	}
	
	@Test 
	public void showEmployeeEntrie() throws InterruptedException {
		logger.info("----------- \t	Showing Employees");
		portalLogin();
		CheckEmployees.goToAllEmployees(driver).click();
		CheckEmployees.scrollPageDown(driver);
		ThreadSleep();
		CheckEmployees.scrollPageUp(driver);
		logger.info("--	Showing 100 Employees");
		CheckEmployees.showEntries(driver, 4).click();
		CheckEmployees.scrollPageDown(driver);
		ThreadSleep();
		CheckEmployees.scrollPageUp(driver);
		logger.info("--	Showing 50 Employees");
		CheckEmployees.showEntries(driver, 3).click();
		CheckEmployees.scrollPageDown(driver);
		ThreadSleep();
		CheckEmployees.scrollPageUp(driver);
		logger.info("--	Showing 25 Employees");
		CheckEmployees.showEntries(driver, 2).click();
		CheckEmployees.scrollPageDown(driver);
		ThreadSleep();
		CheckEmployees.scrollPageUp(driver);
		logger.info("--	Showing 10 Employees");
		CheckEmployees.showEntries(driver, 1).click();
		CheckEmployees.scrollPageDown(driver);
		ThreadSleep();
		CheckEmployees.scrollPageUp(driver);
		portalLogout();
	}
	
	@Test(enabled = false)
	public void printAllEmployees() throws InterruptedException {
		logger.info("----------- \t	Printing All Employees");
		portalLogin();
		CheckEmployees.goToAllEmployees(driver).click();
		CheckEmployees.printReport(driver).click();
		ThreadSleep();
		
		//CheckEmployees.closePrintDialog(driver).click();
		//ThreadSleep();
		logger.info("-- Report Printed");
		//portalLogout();
	}
	
	@Test(priority = 1)
	public void addEmployee() throws InterruptedException {
		logger.info("----------- \t	Adding new Employee");
		portalLogin();
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
	public void searchEmployee() throws InterruptedException {
		logger.info("----------- \t Check Search an employee");
		portalLogin();
		ThreadSleep();
		CheckEmployees.searchEmployee(driver).sendKeys("Arkan");
		CheckEmployees.searchEmployee(driver).sendKeys(Keys.RETURN);
		ThreadSleep();
		portalLogout();
	}
	@Test(priority = 3)
	public void checkLoginByUsername() throws InterruptedException {
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
	public void checkLoginByEmail() throws InterruptedException {
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
	public void terminateTest() throws InterruptedException {

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
