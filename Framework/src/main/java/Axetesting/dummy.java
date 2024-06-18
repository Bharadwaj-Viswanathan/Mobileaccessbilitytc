package Axetesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class dummy {
	public static AndroidDriver driver;
	public static void main(String[] args) {
		DesiredCapabilities dc = new DesiredCapabilities();

		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability("appPackage", "com.hyundai.ndms");
		dc.setCapability("appActivity","com.hyundai.ndms.activities.SplashActivity");
		dc.setCapability("autoGrantPermissions",true);

		// Set up Appium driver
		try {
			driver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), dc);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
//		DesiredCapabilities settingcap=new DesiredCapabilities();
//		settingcap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
//		settingcap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//
//		settingcap.setCapability("appPackage","com.android.settings");
//		settingcap.setCapability("appActivity", ".Settings$AccessibilitySettingsActivity");
//		settingcap.setCapability("autoGrantPermissions",true);
//		settingcap.setCapability("newCommandTimeout", 120); 
//		try {
//			settingdriver = new AndroidDriver(new URL("http://0.0.0.0:4723/"), settingcap);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		driver.startActivity(new Activity("com.android.settings", ".Settings$AccessibilitySettingsActivity"));
		System.out.println(driver.getCurrentPackage());
		WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"Color inversion\"));"));
		element.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//android.widget.TextView[@text='Use color inversion']/../../android.widget.LinearLayout/android.widget.Switch[@text='OFF']")).click();
		driver.activateApp("com.hyundai.ndms");
		System.out.println(driver.getCurrentPackage());
	}
}