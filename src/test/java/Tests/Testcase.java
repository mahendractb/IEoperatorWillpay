package Tests;

import java.io.IOException;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.Bookingpage;
import Pages.Exceldata;
import Pages.Loginpage;

public class Testcase {

	public WebDriver driver;
	
	@SuppressWarnings("deprecation")
	@BeforeMethod
	public void Test1() throws Exception{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("ignoreProtectedModeSettings", true);
		capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR,UnexpectedAlertBehaviour.ACCEPT);
		capabilities.setCapability("browserstack.ie.enablePopups", "false");
		System.setProperty("webdriver.ie.driver", "C:\\Users\\HOME\\Desktop\\IEDriverServer1.exe");
		
		 driver=new InternetExplorerDriver(capabilities);
		
	}
	
	@Test
	public void Willpay() throws Exception{
		new Loginpage(driver).Login();
		new Bookingpage(driver).bookTickets();
	}
}
