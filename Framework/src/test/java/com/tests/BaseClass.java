package com.tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Driver.DBConnection;
import com.ExtentReport.Extentlogger;
import com.configure.config;
import com.github.javafaker.Faker;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.imagecomparison.SimilarityMatchingOptions;
import io.appium.java_client.imagecomparison.SimilarityMatchingResult;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;


/**
 * Base Class
 */
public class BaseClass extends DBConnection {
	static Faker faker = new Faker();
	static BufferedImage expectedImage;
	static BufferedImage actualImage;
	/**
	 * get the text from the element
	 * 
	 * @param element enter the webelement
	 * @return element text
	 */
	public static String Gettext(WebElement element) {
		return element.getText();
	}

	/**
	 * get the attribute value
	 * 
	 * @param element       enter the webelement
	 * @param attributename enter the attribute name
	 * @return attribute value
	 */
	private static String Getattribute(WebElement element, String attributename) {
		return element.getAttribute(attributename);
	}

	/**
	 * send the value to the given field
	 * 
	 * @param element given an webelement
	 * @param value   enter the value that will send
	 */
	public static void sendkeys(WebElement element, String value) {
		String text = Getattribute(element, "text");
		element.sendKeys(value);
		if (!text.isEmpty()) {
			Extentlogger.pass(value + " is entered in " + text + " successfully", false);
		} else {
			Extentlogger.pass(value + " is entered successfully", false);
		}

	}

	/**
	 * send the value to the field and press arrow down and click enter
	 * 
	 * @param element given an webelement
	 * @param value   enter the value that will send
	 */
	public static void sendkeywithenter(WebElement element, String value) {
		clickelement(element);
		element.sendKeys(value, Keys.BACK_SPACE);
		wait5sec();
		;
		element.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		if (!Getattribute(element, "placeholder").isEmpty()) {
			Extentlogger.pass(value + " is entered in " + Getattribute(element, "placeholder") + " successfully",
					false);
		} else {
			Extentlogger.pass(value + " is entered in successfully", false);
		}

	}

	/**
	 * Element is display or not
	 * 
	 * @param element enter the webelement
	 * @return boolean is display or not
	 */
	public static Boolean isdisplay(WebElement element) {
		if (!Gettext(element).isEmpty()) {
			Extentlogger.info(Gettext(element) + " is Display? " + element.isDisplayed());
			return element.isDisplayed();
		} else {
			return element.isDisplayed();
		}

	}

	/**
	 * assertEquals compare the string values
	 * 
	 * @param Actual   Actual result
	 * @param Expected expected result
	 */
	public static void assertEquals(String Actual, String Expected) {
		Softassert.assertEquals(Actual, Expected);
	}

	/**
	 * assert true get the boolean value
	 * 
	 * @param Condition enter the condition
	 */
	public static void asserttrue(Boolean Condition) {
		Softassert.assertTrue(Condition);
	}



	/**
	 * Implict wait for 30sec
	 * 
	 * @param seconds enter the second that will wait
	 */
	public static void implictwait(int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));

	}

	/**
	 * Thread.sleep for 3sec
	 */
	public static void wait3sec() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Thread.sleep for 5sec
	 */
	public static void wait5sec() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * assert by using Boolean
	 * 
	 * @param TrueorFalse true or false value
	 */
	public static void verifyisdisplay(Boolean TrueorFalse) {
		asserttrue(TrueorFalse);
		Extentlogger.info("Is that element displayed?->" + TrueorFalse);
	}

	/**
	 * Explicit wait for Visiblity of List of element for 30 sec
	 */
	public static void Visiblityofelementsexplicitwait(List<WebElement> element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		Extentlogger.info("Elements are Visibile");
	}

	/**
	 * wait for the element to be click able
	 * 
	 * @param element enter the webelement
	 * @param seconds give a seconds that will wait
	 */
	public static void Waitforclickable(WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * Fluent wait for increase poling period
	 * 
	 * @param seconds enter the seconds of Duration
	 * @param pole    enter the polling seconds
	 */
	@SuppressWarnings("unused")
	private static void fluentwait(int seconds, int pole) {
		Wait<WebDriver> Fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(pole)).ignoring(Exception.class);
	}

	/**
	 * click the element
	 * 
	 * @param element enter the webelement
	 */
	public static void clickelement(WebElement element) {
		Waitforclickable(element, 20);
		String text = Gettext(element);

		String content=Getattribute(element, "content-desc");

		element.click();
		try {
			if (!text.isEmpty()) {
				Extentlogger.pass(text + " is Clicked", false);
			}else if(!content.isEmpty()&&content != null) {
				Extentlogger.pass(content + " is Clicked", false);
			}else {

			}
		} catch (NullPointerException|WebDriverException e) {
		}
	}
	/**
	 * click the element
	 * 
	 * @param element enter the webelement
	 */
	public static void clickelementwithoutwait(WebElement element) {
		String text = Gettext(element);

		String content=Getattribute(element, "content-desc");

		element.click();
		try {
			if (!text.isEmpty()) {
				Extentlogger.pass(text + " is Clicked", false);
			}else if(!content.isEmpty()&&content != null) {
				Extentlogger.pass(content + " is Clicked", false);
			}else {

			}
		} catch (NullPointerException|WebDriverException e) {
		}
	}


	/**
	 * Clears the text in the given input field
	 * 
	 * @param element the WebElement representing the input field
	 */
	public static void clearTextField(WebElement element) {
		implictwait(10);
		element.clear();
	}

	/**
	 * By using Robot Class Clear the Textbox field
	 */
	public static void Robotclassfordeletetextbox() {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyPress(java.awt.event.KeyEvent.VK_A);

			robot.keyRelease(java.awt.event.KeyEvent.VK_CONTROL);
			robot.keyRelease(java.awt.event.KeyEvent.VK_A);
			robot.keyPress(java.awt.event.KeyEvent.VK_CLEAR);
			robot.keyRelease(java.awt.event.KeyEvent.VK_CLEAR);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Select an option in an dropdown the tagname is not select
	 * 
	 * @param element  element options
	 * @param element1 click the dropdown box
	 * @param data     data which is going to select
	 */
	public static void selectaoption(List<WebElement> element, WebElement element1, String data) {
		clickelement(element1);
		Extentlogger.Reportlistofdata(element);
		for (int i = 0; i < element.size(); i++) {
			if (Gettext(element.get(i)).equalsIgnoreCase(data)) {
				implictwait(10);
				clickelement(element.get(i));
				break;
			}
		}

	}

	/**
	 * javascriptclick
	 * 
	 * @param element enter the element
	 */
	public static void javascriptclick(WebElement element) {
		Waitforclickable(element, 20);
		String text = Gettext(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		try {
			if (!text.isEmpty()) {
				Extentlogger.info(text + " is Clicked");
			}
		} catch (WebDriverException e) {
		}

	}

	/**
	 * Export the data's to dynamic excel file
	 * 
	 * @param rowelement enter the list webelement of row
	 * @param colelement enter the list webelement of col
	 * @param nextbutton Webelement next button
	 * @param rowxpath   dynamic row xpath in string
	 * @param colxpath   dynamic col xpath in string
	 */
	public static void exportexceldata(List<WebElement> rowelement, List<WebElement> colelement, WebElement nextbutton,
			String rowxpath, String colxpath) {

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Employee");

		int sheetrow = 0;
		int i;

		while (true) {

			for (i = 2; i <= rowelement.size(); i++) {
				XSSFRow excelRow = sheet.createRow(sheetrow++);
				for (int j = 2; j < colelement.size(); j++) {

					WebElement col = driver
							.findElement(By.xpath("(" + rowxpath + ")[" + i + "]" + colxpath + "[" + j + "]"));
					XSSFCell cell = excelRow.createCell(j - 2);
					cell.setCellValue(Gettext(col));
				}
			}

			try {
				isdisplay(nextbutton);
				clickelement(nextbutton);
				sheetrow = i;

			} catch (NoSuchElementException e) {
				break;
			}
		}
		try {
			File file = new File("./src/test/resources/config/" + config.getcurrenttime() + ".xlsx");
			FileOutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			Extentlogger.pass("Data written successfully to the Excel file.", false);
			Extentlogger.info("Extent excel file is stored in this loction" + file.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Switch the driver handle to alert
	 */
	public static void acceptthealert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * Take a screen shot based with classname as file name
	 */
	public void Takeascreenshot() {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File("./src/test/resources/" + config.getcurrenttime() + ".png");

		try {
			FileUtils.copyFile(SrcFile, DestFile);

			Extentlogger.info("Screenshot was stored in" + DestFile.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String Takeascreenshotwithstring(String name) {
		TakesScreenshot scrShot = ((TakesScreenshot) driver);

		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

		File DestFile = new File("./Screenshots/" + name + ".png");

		try {
			FileUtils.copyFile(SrcFile, DestFile);
			Extentlogger.info("Screenshot was stored in" + DestFile.toString());

		} catch (IOException e) {
		}
		return DestFile.getAbsolutePath();
	}
	public static String Takeascreenshotwithelement(WebElement element,String name) {

		File screenshot = element.getScreenshotAs(OutputType.FILE);
		String screenshotPath = "./src/test/resources/screenshots/"+name+".png";	
		try {
			FileUtils.copyFile(screenshot, new File(screenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return screenshotPath;
	}

	public static void print(String textinfo) {
		Extentlogger.info(textinfo);
		System.out.println(textinfo);
	}

	public static void switchtoframe(WebElement element) {
		driver.switchTo().frame(element);
	}

	public static void switchtoframe(int index) {
		driver.switchTo().frame(index);
	}

	public static void switchtoframe(String nameorid) {
		driver.switchTo().frame(nameorid);
	}

	public static void scrolluntillelement(String text, int seconds) {
		long startTime = System.currentTimeMillis();
		while ((System.currentTimeMillis() - startTime) < (seconds * 1000)) {
			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(" + text
					+ "))"));
		}
	}


	public static void selectvalueusingscroll(String value, String elementid) {
		WebElement element = driver
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"" + elementid
						+ "\")).scrollIntoView(" + "new UiSelector().text(\"" + value + "\"));"));
		clickelement(element);
	}

	public static void scrolltillend() {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
	}

	public static void scrollandselect(String value,AndroidDriver Driver) {
		WebElement element = Driver.findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(" + "new UiSelector().text(\"" + value + "\"));"));
		clickelementwithoutwait(element);
	}

	public static void navigateback() {
		implictwait(5);
		driver.navigate().back();
	}


	public static String randomcharacters() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		StringBuilder randomString = new StringBuilder();

		Random random = new Random();
		int index = random.nextInt(characters.length());
		randomString.append(characters.charAt(index));
		return randomString.toString();
	}

	public static void seekbarsscroll(int startX, int endX, int yaxis) {
		final PointerInput FINGER = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Point start = new Point(startX, yaxis);
		Point end = new Point(endX, yaxis);
		Sequence swipe = new Sequence(FINGER, 1)
				.addAction(FINGER.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(),
						start.getY()))
				.addAction(FINGER.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
				.addAction(FINGER.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(),
						end.getY()))
				.addAction(FINGER.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(swipe));
	}
	public static void action_clickOnPosition(int pointA_X, int pointA_Y) {
		PointerInput finger = new PointerInput(org.openqa.selenium.interactions.PointerInput.Kind.TOUCH, "finger");
		org.openqa.selenium.interactions.Sequence clickPosition = new org.openqa.selenium.interactions.Sequence(finger,
				1);
		clickPosition.addAction(finger.createPointerMove(Duration.ZERO, Origin.viewport(), pointA_X, pointA_Y))
		.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()))
		.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));
		driver.perform(Arrays.asList(clickPosition));
	}


	public static void fetchingexpectedandactualimg(String expectedImagePath,WebElement element)  {
		File expectedImageFile = new File(expectedImagePath);
		try {
			expectedImage = ImageIO.read(expectedImageFile);
			File screenshot = element.getScreenshotAs(OutputType.FILE);
			actualImage = ImageIO.read(screenshot);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void comparingExpectedAndActualimg(){
		ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);
		if (diff.hasDiff()) {
			Extentlogger.fail("Images are NOT same", true);
			asserttrue(false);
			BufferedImage diffImage = diff.getMarkedImage();

			// Save the diff image to a file
			File diffImageFile = new File("./src/test/resources/screenshots/diff_image.png");
			try {
				ImageIO.write(diffImage, "PNG", diffImageFile);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			Extentlogger.pass("Images are same",true);
		}
	}
	public static void comparingImage(String Expectedimage,String Actualimage) {
		SimilarityMatchingOptions options=new SimilarityMatchingOptions();
		options.withEnabledVisualization();
		SimilarityMatchingResult res;
		try {
			res = driver.getImagesSimilarity(new File(Expectedimage), new File(Actualimage),options);
			print(""+res.getScore());
			if(res.getScore()<1) {
				File difffile=new File("./src/test/resources/screenshots/differimage.png");
				res.storeVisualization(difffile);
			}else {
				asserttrue(true);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void swipeTillElement(WebElement element) {
		Dimension size = driver.manage().window().getSize();
		int startX = size.width / 2;
		int startY = (int) (size.height * 0.8);
		int endY = (int) (size.height * 0.2);

		boolean endOfList = false;

		while (!endOfList) {
			TouchAction<?> action = new TouchAction<>(driver)
					.press(PointOption.point(startX, startY))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(20)))
					.moveTo(PointOption.point(startX, endY));
			action.perform();

			// Check if end of list is reached
			endOfList = element.isDisplayed();
		}
	}

}