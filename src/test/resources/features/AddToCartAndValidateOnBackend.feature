#Author: ajitbhavle93@gmail.com
Feature: As a Zageno user I should be able to login and should able to place order

  Scenario: Add product to cart and complete the checkout process
    Given Navigate to url "https://nj-coding-challenge.zageno.com/"
    And Login to applicatio with username and password
    When Select the product and add to cart
    Then Add PO number to order
    And Validate cart total and cost center
    And Click on checkout button
    And Get order number

  Scenario: Validate placed order in backend application
    Given Navigate to url "https://nj-coding-challenge-admin.zageno.com/"
    And Login with Okta
    Then Search the product number and validate PO number on product detail page
