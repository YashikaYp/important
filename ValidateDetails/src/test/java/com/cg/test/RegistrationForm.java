package com.cg.test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationForm
{
	@FindBy(how=How.NAME,using="firstname")
	WebElement firstName;
	@FindBy(how=How.NAME,using="lastname")
	WebElement lastName;
	@FindBy(how=How.NAME,using="email")
	WebElement email;
	@FindBy(how=How.NAME,using="number")
	WebElement number;
	@FindBy(how=How.NAME,using="address")
	WebElement address;
	@FindBy(how=How.ID,using="city")
	WebElement city;
	@FindBy(how=How.CLASS_NAME,using="new1")
	List<WebElement> state;
	@FindBy(how=How.CLASS_NAME,using="submit")
	WebElement submit;
	
}
