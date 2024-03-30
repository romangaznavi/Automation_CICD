@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce webpage
	

  @Regression
  Scenario Outline: Positive test of purchasing the order
    Given Logged in with username <username> and passowrd <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmationPage

    Examples: 
      | username  								|  password  		|productName			|
      |roman1@gmail.com       		|Roman@123 			| ZARA COAT 3			|
