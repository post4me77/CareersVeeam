package com.veeam.ui;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.veeam.BasePageObject;

public class Veeam extends BasePageObject {

	public Veeam(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//*[@id='country-element']")
	WebElement countryElement;

	@FindBy(xpath = "//*[@id='language']")
	WebElement languageElement;

	@FindBy(xpath = "//*[@class='selecter-fieldset-submit']")
	WebElement languageButtonApply;

	@FindBy(className ="vacancies-blocks-item")
	List<WebElement> vacanciesBlocksItem;

	@FindBy(xpath = "//*[@id='index-vacancies-buttons']")
	WebElement showAllJobsButton;

	public void scrollIntoView(WebElement element) throws InterruptedException {
		scrollToElement(element);
	}

	public void setElementText(WebElement element, String text) throws IOException, InterruptedException {
		element.sendKeys(text);
	}

	public void waitForElement(WebElement element) throws IOException, InterruptedException {
		waitUntilElementIsLoaded(element);
	}

	public void waitForNotElement(By locator) throws IOException, InterruptedException {
		waitUntilInvisibilityElement(locator);
	}

	public void selectDropDownCountry(String countryName) throws IOException, InterruptedException {
		scrollIntoView(countryElement);
		countryElement.click();
		WebElement selectedCountry = driver
				.findElement((By.xpath("//*[@id=\"country-element\"]//span[text() = \"" + countryName + "\"]")));
		waitForElement(selectedCountry);
		selectedCountry.click();
	}

	public void selectDropDownLanguage(String language) throws IOException, InterruptedException {
		scrollIntoView(languageElement);
		languageElement.click();
		WebElement selectedLanguage = driver
				.findElement((By.xpath("//*[@id='language']//label[contains(.,\"" + language + "\")]")));
		waitForElement(selectedLanguage);
		selectedLanguage.click();
		languageButtonApply.click();
	}
	
	public int countAllJobsItem() throws IOException, InterruptedException {
		if (showAllJobsButton.isDisplayed()) {
			scrollIntoView(showAllJobsButton);
			showAllJobsButton.click();
			waitForNotElement(By.xpath("//*[@class='index-vacancies-buttons']"));
			Thread.sleep(1000);
		}
		return vacanciesBlocksItem.size();
	}
}
