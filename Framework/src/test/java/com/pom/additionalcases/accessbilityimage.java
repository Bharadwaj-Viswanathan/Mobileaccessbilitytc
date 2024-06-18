package com.pom.additionalcases;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;

import com.tests.BaseClass;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.remote.MobileCapabilityType;

public class accessbilityimage extends BaseClass {


	@FindBy(id="com.hyundai.ndms:id/action_bar_root")
	private static WebElement dashboard;
	@FindBy(xpath="//android.widget.Switch[@resource-id='android:id/switch_widget']")
	private static WebElement colorinversiontoggle;
	@FindBy(xpath="//android.widget.TextView[@text='Use color inversion']/../../android.widget.LinearLayout/android.widget.Switch[@text='OFF']")
	private static WebElement colorinversionenableswitch;
	@FindBy(xpath="//android.widget.TextView[@text='Use color inversion']/../../android.widget.LinearLayout/android.widget.Switch[@text='ON']")
	private static WebElement colorinversiondisableswitch;

	@FindBy(xpath="//android.widget.TextView[@text='Smart Consulting']")
	private static WebElement smartconsulting;
	@FindBy(xpath="//android.widget.TextView[@text='TUCSON']")
	private static WebElement tucson;
	
	static String colorinversion="Color inversion";


	public static void dashboard_imagecomparision() {
		wait3sec();
//		String Expectedimagepath = Takeascreenshotwithelement(dashboard,"ExpectedDashboard");
//		colourinversionfeature("OFF");
//		driver.activateApp(prop.getProperty("appPackage"));
		wait5sec();
//		System.out.println(driver.getCurrentPackage());
		fetchingexpectedandactualimg("./src/test/resources/screenshots/ExpectedDashboard.png", dashboard);
		comparingExpectedAndActualimg();
		String Expectedimage = Takeascreenshotwithstring("Expectedresult");
		scrolltillend();
		String ActualImage = Takeascreenshotwithstring("Actualresult");
		comparingImage(Expectedimage, ActualImage);
		
		
//		colourinversionfeature("ON");
//		driver.activateApp(prop.getProperty("appPackage"));
	}
	
	public static void imagecomparisionsmartconsulting() {
		clickelement(smartconsulting);
		wait5sec();
		wait5sec();
		wait5sec();
		wait3sec();
		String Expectedimage = Takeascreenshotwithstring("Expectedresult");
		print("Scrolled");
		swipeTillElement(tucson);
		String ActualImage = Takeascreenshotwithstring("Actualresult");
		comparingImage(Expectedimage, ActualImage);
	}
	private static void colourinversionfeature(String onoroff) {
		implictwait(30);
		driver.startActivity(new Activity("com.android.settings", ".Settings$AccessibilitySettingsActivity"));
		scrollandselect(colorinversion, driver);
		if(colorinversiontoggle.getText().equalsIgnoreCase(onoroff)) {
			colorinversiontoggle.click();
		}
	}
}
