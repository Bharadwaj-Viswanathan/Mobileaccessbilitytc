package com.Driver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.ExtentReport.ExtentReport;
import com.configure.Factory;
import com.configure.config;
import com.tests.BaseClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DBConnection {

	public static AndroidDriver driver;
	public static SoftAssert Softassert;
	public static Properties prop;

	@BeforeSuite
	public static void initApplication() throws IOException
	{
		config.Getdatafrompropertiesfile();
		Softassert = new SoftAssert();
		DesiredCapabilities dc = new DesiredCapabilities();
		System.out.println(prop.getProperty("Platformname"));
		System.out.println(prop.getProperty("deviceName"));
		System.out.println(prop.getProperty("appPackage"));
		System.out.println( prop.getProperty("noReset"));
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("Platformname"));
		//				dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, prop.getProperty(""));
		//				dc.setCapability(MobileCapabilityType.UDID, prop.getProperty(""));
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		dc.setCapability("appPackage",  prop.getProperty("appPackage"));
		dc.setCapability("appActivity", prop.getProperty("appActivity"));
		dc.setCapability("noReset",Boolean.valueOf( prop.getProperty("noReset")));
		dc.setCapability("autoGrantPermissions",prop.getProperty("autoGrantPermissions"));

		// Set up Appium driver
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		//				Deviceinfo();
		BaseClass.implictwait(10);
		Factory.initelements();
	}
	/**
	 * Close the Browser
	 * 
	 * @throws IOException
	 */
	@AfterClass
	public void CloseBrowser() throws IOException {
		driver.quit();
		Softassert.assertAll();
	}

	public static void Deviceinfo() {
		ExtentReport.extent.setSystemInfo("PlatformName",driver.getCapabilities().getCapability("platformName").toString());
		ExtentReport.extent.setSystemInfo("platformVersion",driver.getCapabilities().getCapability("platformVersion").toString());
		ExtentReport.extent.setSystemInfo("deviceName",driver.getCapabilities().getCapability("deviceName").toString());
		ExtentReport.extent.setSystemInfo("appPackage",driver.getCapabilities().getCapability("appPackage").toString());
		ExtentReport.extent.setSystemInfo("appActivity",driver.getCapabilities().getCapability("appActivity").toString());

	}

}
