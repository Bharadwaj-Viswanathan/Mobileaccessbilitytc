package com.tests;

import java.io.IOException;

import org.testng.annotations.Test;

import com.pom.additionalcases.LoginPage;
import com.pom.additionalcases.accessbilityimage;
import com.pom.additionalcases.Dashboardpage;


public class Hsmart_addtionalcases extends BaseClass{
	
	@Test(enabled=true)
	private static void Login() {
		LoginPage.performLogin(prop.getProperty("smartid"), prop.getProperty("loginpassword"));
	}
	
	@Test(priority=4,enabled=true)
	private static void Verifythetalkbackaccessbility() throws IOException {
		Dashboardpage.accessbilitytalkbackforpopup();
	}
	
	@Test(priority=2,enabled=true)
	private static void Verifytheskipnavigation() {
		Dashboardpage.skipnavigationonhelp();
		driver.navigate().back();
	}
	@Test(priority=3,enabled=true)
	private static void Verifytheassabilityimagecomparision() throws IOException {
		accessbilityimage.dashboard_imagecomparision();
//		accessbilityimage.imagecomparisionsmartconsulting();
	}

}
