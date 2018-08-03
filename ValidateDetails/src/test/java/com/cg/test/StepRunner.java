package com.cg.test;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepRunner {

	WebDriver driver = null;
	RegistrationForm register = null;
	NewPage pageNext = null;

	@Before
	public void getDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\BDD\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Given("^I have navigated to the register page$")
	public void i_have_navigated_to_the_register_page() throws Throwable {
		driver.navigate().to(
				"file:D:\\IMPORTANT\\PROJECT_FILES\\SpringExam2\\ValidateDetails\\src\\main\\webapp\\WEB-INF\\EnterDetails.html");
	}

	@When("^I enter the valid credentials$")
	public void i_enter_the_valid_credentials() throws Throwable {
		register = PageFactory.initElements(driver, RegistrationForm.class);

		register.firstName.sendKeys("yashi");

		register.lastName.sendKeys("nathwani");
		register.email.sendKeys("yashu@gmail.com");
		register.number.sendKeys("7062156698");
		register.address.sendKeys("Hinjawadi");
		register.city.sendKeys("Pune");
		register.state.get(1).click();
		Thread.sleep(5000);
		register.submit.click();

	}

	@Then("^I should navigate to next page$")
	public void i_should_navigate_to_next_page() throws Throwable {
		pageNext = PageFactory.initElements(driver, NewPage.class);

		pageNext.details.sendKeys("Creating a new Login Page");
		pageNext.projectname.sendKeys("JAVA");
		pageNext.client1.sendKeys("Akash");
		pageNext.size.sendKeys("6");
		Thread.sleep(5000);
		pageNext.submit1.click();

		driver.close();

	}

	@When("^I enter the invalid number$")
	public void i_enter_the_invalid_credentials() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		register = PageFactory.initElements(driver, RegistrationForm.class);

		register.firstName.sendKeys("yashi");

		register.lastName.sendKeys("nathwani");
		register.email.sendKeys("yashu@gmail.com");
		register.number.sendKeys("70621");
		register.address.sendKeys("Hinjawadi");
		register.city.sendKeys("Pune");
		register.state.get(1).click();
		//Thread.sleep(5000);
		register.submit.click();
	}

	@Then("^I should get error in the same page$")
	public void i_should_get_error_in_the_same_page() throws Throwable {

		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		System.out.println("Message from alert : " + msg);

		assertEquals("Enter valid number", msg);
		driver.close();

	}

	@When("^I enter the invalid mail$")
	public void i_enter_the_invalid_mail() throws Throwable {
		
		register = PageFactory.initElements(driver, RegistrationForm.class);

		register.firstName.sendKeys("yashi");

		register.lastName.sendKeys("nathwani");
		register.email.sendKeys("ffd@g");
		register.number.sendKeys("7062156698");
		register.address.sendKeys("Hinjawadi");
		register.city.sendKeys("Pune");
		register.state.get(1).click();
		Thread.sleep(5000);
		register.submit.click();
	}

	@Then("^I should get error$")
	public void i_should_get_error_in_the_same_page1() throws Throwable {

		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		System.out.println("Message from alert : " + msg);

		assertEquals("Invalid email entered", msg);
		driver.close();

	}

}
