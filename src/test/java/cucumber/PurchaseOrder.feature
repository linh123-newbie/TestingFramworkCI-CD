@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file
	
	Background:
	Given I landed on Ecommerce Page
	
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with email <email> and password <password>
    When I add product <item> to cart
    And Checkout <item> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | email   								| password 			| item  					|
      | gialinh0167@gmail.com 	| 01656107662aA | ADIDAS ORIGINAL |
