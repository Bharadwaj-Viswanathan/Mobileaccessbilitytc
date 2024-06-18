package Axetesting;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.deque.axe.AXE;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class axetesting {
	static AndroidDriver driver;

	@BeforeClass
	public static void makeDriver() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Oneplus");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
		capabilities.setCapability("appPackage", "com.instagram.android");
		capabilities.setCapability("appActivity", "com.instagram.mainactivity.InstagramMainActivity");
		URL url = new URL("http://localhost:4723/");
		driver=new AndroidDriver(url, capabilities);

	}
	@Test
	private static void axetest() {
		URL scriptUrl = axetesting.class.getResource("/axe.min.js");
		org.json.JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
		JSONArray violations = responseJSON.getJSONArray("violations");
		if (violations.length() == 0) {
			System.out.println("No voilation");
		} else {
			AXE.writeResults("testAccessibilityWithWebElement", responseJSON);
			AXE.report(violations);
			System.out.println("there is voilation");
		}
		driver.quit();
	}


}
