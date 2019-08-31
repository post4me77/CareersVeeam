package com.veeam.ui;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.veeam.DriverFactory;
import com.veeam.ReadPropertyFile;

public class VeeamTest {
	DriverFactory objDriver = new DriverFactory();
	ReadPropertyFile readPropertyFile = new ReadPropertyFile();
	Veeam veeam;
	String BASEURL = "https://careers.veeam.com/";
	String COUNTRY_ROMANIA = "Romania";
	String LANGUAGE_ENGLISH = "English";
	int QUANTITY_ROMANIA_VACANCY = 31;

	@Before
	public void setUp() throws IOException, InterruptedException {
		veeam = new Veeam(objDriver.getDriver());
		// Set maximum browser size.
		veeam.maxWindowSize();
		// Go to URL.
		objDriver.getDriver().navigate().to(BASEURL);
	}

	@After
	public void tearDown() {
		objDriver.quitDriver();
	}

	@Test
	public void mySimpleEqualsTest() throws InterruptedException, IOException {
		// Select country from drop down menu.
		veeam.selectDropDownCountry(COUNTRY_ROMANIA);
		// Select language from drop down menu.
		veeam.selectDropDownLanguage(LANGUAGE_ENGLISH);
		// Count the number of vacancies.
		assertEquals(veeam.countAllJobsItem(), QUANTITY_ROMANIA_VACANCY);
	}
}
