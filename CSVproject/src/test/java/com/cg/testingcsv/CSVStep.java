package com.cg.testingcsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import au.com.bytecode.opencsv.CSVReader;
import org.junit.Before;

public class CSVStep {
	String PATH = "D:\\new1.csv";
	WebDriver driver;

	@Before
	public void init() {
		System.setProperty("webdriver.chrome.driver", "D:\\BDD\\Selenium\\chromedriver.exe");
		
	}

	@Test
	public void csvDataRead() throws IOException {

		CSVReader reader = new CSVReader(new FileReader(PATH));
		String[] csvCell ;
	
		while ((csvCell = reader.readNext()) != null) {
			
			
				driver = new ChromeDriver();
				driver.get("D:\\IMPORTANT\\PROJECT_FILES\\SpringExam2\\CSVproject\\src\\main\\webapp\\WEB-INF\\Form.html");
				
				String EmpId = csvCell[0];
				String Name = csvCell[1];
				String Salary = csvCell[2];
				String Designation = csvCell[3];
			driver.findElement(By.id("num")).sendKeys(EmpId);

			driver.findElement(By.id("name")).sendKeys(Name);
			driver.findElement(By.id("salary")).sendKeys(Salary);

			driver.findElement(By.id("designation")).sendKeys(Designation);

			driver.findElement(By.id("submit")).click();

			
			
			
		}
	}
}
