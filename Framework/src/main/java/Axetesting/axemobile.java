//package Axetesting;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.json.JSONArray;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import com.deque.axe.AXE;
//
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.remote.MobileCapabilityType;
//
//public class axemobile {
//
//	static AndroidDriver driver;
//	@BeforeClass
//	public static void makeDriver() throws MalformedURLException {
//		DesiredCapabilities capabilities = new DesiredCapabilities();
//		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
//		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Oneplus");
//		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11");
//		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Uiautomator2");
//		capabilities.setCapability("appPackage", "com.instagram.android");
//		capabilities.setCapability("appActivity", "com.instagram.mainactivity.InstagramMainActivity");
//		URL url = new URL("http://localhost:4723/");
//		driver=new AndroidDriver(url, capabilities);
//	}
//	@Test
//	private static void axetest() {
//		  Axe.init(driver);
//
//          // Configure Axe Analytics
//          AxeAnalyticsConfig config = new AxeAnalyticsConfig.Builder()
//                  .setApplicationId("YOUR_APPLICATION_ID")  // Replace with your actual Application ID
//                  .setApiKey("YOUR_API_KEY")                // Replace with your actual API Key
//                  .setEndpoint("YOUR_ENDPOINT_URL")         // Replace with your actual Endpoint URL
//                  .build();
//          AxeAnalytics.init(config);
//
//          // Example: Navigate to a view and run an accessibility scan
//          MobileElement element = driver.findElementById("your_element_id");
//          Axe.run(driver, element, new Axe.ScanCallback() {
//              @Override
//              public void onFinished(AxeResult axeResult) {
//                  // Handle the scan result here
//                  System.out.println(axeResult.toJson());
//              }
//		driver.quit();
//	}
//}
