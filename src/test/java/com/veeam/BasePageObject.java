package com.veeam;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePageObject {
	protected static WebDriver driver;

	public BasePageObject(WebDriver driver) {
		super();
		BasePageObject.driver = driver;
	}

	/*
	 * Waiting until element is loaded.
	 * 
	 * @param {element} The element that we are waiting for.
	 */
	public static void waitUntilElementIsLoaded(WebElement element) throws IOException, InterruptedException {
		new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * Waiting until element will be invisible.
	 * 
	 * @param {locator} The locator of element that we are waiting for.
	 */
	public static void waitUntilInvisibilityElement(By locator) throws IOException, InterruptedException {
		new WebDriverWait(driver, 15).until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	/*
	 * Set browser windows size.
	 * 
	 * @param {number} The x axis value.
	 * 
	 * @param {number} The y axis value.
	 */
	public void setWindowsSize(int x, int y) {
		Dimension newSize = new Dimension(x, y);
		// Resize current window to the set dimension
		driver.manage().window().setSize(newSize);
	}

	/*
	 * Set maximum browser windows size.
	 */
	public void maxWindowSize() {
		driver.manage().window().maximize();
	}
	
	/*
	 * Scrolling until element is visible.
	 * 
	 * @param {element} The locator of element until we need to scroll.
	 */
	public void scrollToElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}
}
