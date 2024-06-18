package com.tests;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class ashotimagecomparision {
	static BufferedImage expectedImage;
	static BufferedImage actualImage;
	public static void fetchingexpectedandactualimg(String expectedImagePath) throws IOException {
		File expectedImageFile = new File(expectedImagePath);
		expectedImage = ImageIO.read(expectedImageFile);
		File screenshot = new File("./src/test/resources/screenshots/ExpectedManSiddhiimage.png");
		actualImage = ImageIO.read(screenshot);
	}
 
	public static void comparingExpectedAndActualimg() throws IOException {
		 ImageDiffer imgDiff = new ImageDiffer();
		ImageDiff diff = imgDiff.makeDiff(expectedImage, actualImage);
		if (diff.hasDiff()) {
			System.out.println("Images are NOT same");
            BufferedImage diffImage = diff.getMarkedImage();

            // Save the diff image to a file
            File diffImageFile = new File("./src/test/resources/screenshots/diff_image.png");
            ImageIO.write(diffImage, "PNG", diffImageFile);

		} else {
			System.out.println("Images are same");
		}
	}
	public static void main(String[] args) throws IOException {
		fetchingexpectedandactualimg("./src/test/resources/screenshots/ActualSiddhaimage.png");
		comparingExpectedAndActualimg();
	}
}
