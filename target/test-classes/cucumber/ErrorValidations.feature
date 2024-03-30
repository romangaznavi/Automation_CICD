@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce webpage
    And Logged in with username <username> and passowrd <password>
    Then "Incorrect email or password." message is displayed

 		Examples: 
      | username  								|  password  		|
      |roman1@gmail.com       		|Roman@1213			|
