Feature: The Shop

  Background: The Shop is available in browser
    Given I am on the homepage
    ##Natalia

  @acceptance
  Scenario:
      ## When user checks page title - Natalia
    Then title is "Webbutiken"

    ##Search functionality - Deborah
    @acceptance
  Scenario: User searches for a product by name
    Given I am on the shop page
    When I enter "Jacket" into the search bar
    And I press Enter
    Then I should see a product with the description "Bomullsjacka, vad finns det mer att säga?!"