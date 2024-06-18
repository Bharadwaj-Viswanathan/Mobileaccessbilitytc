package com.pom.additionalcases;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tests.BaseClass;

import io.appium.java_client.android.nativekey.KeyEvent;

public class Dashboardpage extends BaseClass{
	@FindBy(xpath="//android.widget.ImageButton[@content-desc='Open navigation drawer']")
	private static WebElement Hamburgermenu;
	
	@FindBy(xpath="//android.widget.Button[@text='Help']")
	private static WebElement Helpoption;

	@FindBy(xpath="//android.widget.TextView[@text='Help']")
	private static WebElement Helppageheader;	
	@FindBy(xpath="//android.view.View[contains(@text,'Dashboard')]")
	private static WebElement HelpDashboard;
	@FindBy(xpath="//android.widget.Image[@text='image']")
	private static WebElement skipnavigationbutton;

	@FindBy(xpath="//android.widget.Button[@text='Logout']")
	private static WebElement Logoutbutton;



	public static void skipnavigationonhelp() {
		implictwait(2);
		clickelement(Hamburgermenu);
		implictwait(2);
		clickelement(Helpoption);
		clickelement(HelpDashboard);
		scrolltillend();
		scrolltillend();
		wait3sec();
		clickelement(skipnavigationbutton);
		wait5sec();
	}

	public static void accessbilitytalkbackforpopup() {
		clickelement(Hamburgermenu);
		//clickelement(Logoutbutton);
		enableSelectToSpeak();
		clickelement(Logoutbutton);
		disableSelectToSpeak();
	}
	private static void enableSelectToSpeak() {
		String enableCommand = "adb shell settings put secure enabled_accessibility_services com.google.android.marvin.talkback/com.google.android.marvin.talkback.TalkBackService";

		try {
			runADBCommand(enableCommand);
			print("Enabling Select to Speak...");
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	private static void disableSelectToSpeak() {

		String disableCommand = "adb shell settings put secure enabled_accessibility_services com.android.talkback/com.google.android.marvin.talkback.TalkBackService";

		try {
			runADBCommand(disableCommand);
			print("Disabling Select to Speak...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void runADBCommand(String command) throws InterruptedException, IOException{
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();  // Wait for the command to complete
	}
}