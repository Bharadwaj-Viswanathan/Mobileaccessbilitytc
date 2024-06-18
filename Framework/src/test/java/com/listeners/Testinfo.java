package com.listeners;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.ExtentReport.ExtentReport;
import com.ExtentReport.Extentlogger;

import com.tests.BaseClass;

import io.appium.java_client.android.AndroidDriver;

public class Testinfo extends BaseClass implements ITestListener,ISuiteListener{
	
	@Override
	public void onStart(ISuite suite) {
	    Runtime runtime = Runtime.getRuntime();
	    try {
	        runtime.exec("cmd.exe /c start cmd.exe /k \"appium --use-plugin=images\"");
	        Thread.sleep(10000);
	    } catch (IOException | InterruptedException e) {
	        e.printStackTrace();
	    }
		ExtentReport.invokereport();
//		Extentlogger.log.info("On Start program");

	}
	
	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushreport();
		 Runtime runtime = Runtime.getRuntime();
//		    try {
//		        runtime.exec("taskkill /F /IM node.exe");
//		        runtime.exec("taskkill /F /IM cmd.exe");
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		Extentlogger.log.info("On Finish program");
	}


	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
//		Extentlogger.log.info(result.getMethod().getMethodName()+"->This method execution is started");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Extentlogger.fail(result.getMethod().getMethodName(), true);
		Extentlogger.fail(result.getThrowable().toString(), false);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		Extentlogger.pass(result.getMethod().getMethodName(),true);

	}
	@Override
	public void onTestSkipped(ITestResult result) {
		Extentlogger.skip(result.getMethod().getMethodName(),true);
		Extentlogger.skip(result.getThrowable().toString(), false);
	}


}
