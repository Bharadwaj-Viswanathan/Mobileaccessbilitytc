package com.pom.additionalcases;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.FindBy;

import com.tests.BaseClass;

import io.appium.java_client.HidesKeyboard;

public class LoginPage extends BaseClass {

	// Android permissions dialog - Allow H-SMART to access photos, media, and files on your device
	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private static WebElement allowPermissionsButton;

	// WebElement for the login heading on the login page
	@FindBy(xpath = "//android.widget.TextView[@text='Login']")
	private static WebElement loginHeading;
	
	// WebElement for the login content description on the login page
	@FindBy(xpath = "//android.widget.TextView[@text='Use your credentials to access your account.']")
	private static WebElement loginContent;

	// WebElement for the user ID field on the login page
	@FindBy(xpath = "//android.widget.EditText[@text='Enter your H-SMART ID']")
	//@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.EditText")
	private static WebElement userIdField;

	// WebElement for the password field on the login page
	@FindBy(xpath = "//android.widget.EditText[@text='Enter your password']")
	//@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.FrameLayout/android.widget.EditText")
	private static WebElement passwordField;

	// WebElement for the login button on the login page
	@FindBy(id = "com.hyundai.ndms:id/login_button")
	private static WebElement loginButton;

	// WebElement for the forgot password button on the login page
	@FindBy(id = "com.hyundai.ndms:id/forgot_button")
	private static WebElement forgotPasswordButton;
	@FindBy(xpath = "//android.widget.Button[@text='SKIP']")
	private static WebElement skip;

	// Onboarding Screens
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]")
	private static WebElement screen1;
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[2]")
	private static WebElement screen2;
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[3]")
	private static WebElement screen3;
	@FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.HorizontalScrollView/android.widget.LinearLayout/android.widget.LinearLayout[4]")
	private static WebElement screen4;

	// Method to perform login action with given credentials
	public static void performLogin(String hSmartId, String password) {
//		clickelement(allowPermissionsButton);
        implictwait(60);

		clickelement(userIdField);
		sendkeys(userIdField, hSmartId);
		clickelement(passwordField);
		wait3sec();
		sendkeys(passwordField, password);
		((HidesKeyboard) driver).hideKeyboard();
		clickelement(loginButton);

		clickelement(skip);

//		action_clickOnPosition(587, 1414);
		wait3sec();
	}

	
	
    // Click Element
	public static void action_clickOnPosition(int pointA_X, int pointA_Y) {
		PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
		org.openqa.selenium.interactions.Sequence clickPosition = new org.openqa.selenium.interactions.Sequence(finger,
				1);
		clickPosition.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), pointA_X, pointA_Y))
				.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()))
				.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(clickPosition));
	}

}
