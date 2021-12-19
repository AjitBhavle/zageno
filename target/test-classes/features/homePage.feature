#Author: ajitbhavle93@gmail.com

Feature: As a Amazon user I should be able to login and logout with valid credentials

  Scenario: Select items on amazon and validate their total
    Given Navigate to url "https://www.amazon.in/"
    When Search for cheapest "snickers chocolate bar" and add to cart
    And Search for cheapest "skittles candy" and add to cart
    Then Validate cart total
    And Click on checkout button
    And User should redirect to login Or registration page
    
  Scenario: Select items on amazon and validate their total
    Given Adding test scenaio
