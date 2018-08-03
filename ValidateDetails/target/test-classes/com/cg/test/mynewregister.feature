Feature: Logging In
Scenario: I want to Register
Given I have navigated to the register page
When I enter the valid credentials
Then I should navigate to next page


Scenario: I want to Register
Given I have navigated to the register page
When I enter the invalid number 
Then I should get error in the same page

Scenario: I want to Register
Given I have navigated to the register page
When I enter the invalid mail  
Then I should get error 