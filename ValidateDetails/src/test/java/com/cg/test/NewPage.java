package com.cg.test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NewPage {

	@FindBy(how=How.NAME,using="details")
	WebElement details;
	@FindBy(how=How.NAME,using="projectname")
	WebElement projectname;
	@FindBy(how=How.ID,using="client1")
	WebElement client1;
	@FindBy(how=How.ID,using="size")
	WebElement size;
	@FindBy(how=How.CLASS_NAME,using="submit")
	WebElement submit1;
	
}
