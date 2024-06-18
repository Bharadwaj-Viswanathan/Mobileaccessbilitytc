package Axetesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

public class demo {
	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		//		dc.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
		//dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		dc.setCapability("appPackage", "com.android.chrome");
		dc.setCapability("appActivity","com.google.android.apps.chrome.Main");
		URL url = new URL("http://0.0.0.0:4723/");

		AndroidDriver driver=new AndroidDriver(url,dc);
		driver.get("https://slicelife.partners/why-slice");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("(//div[@class='burger-box'])[2]/parent::button")).click();
	
}
}
