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

      ##Footer elements - Pierre Nilsson
  @acceptance
  Scenario: Verify footer elements
    Then the footer section should contain the text "© 2023 The Shop"
    And the footer section should contain a link with text "Home" and URL "/"
    And the footer section should contain a link with text "Shop" and URL "/products"
    And the footer section should contain a link with text "Checkout" and URL "/checkout"
    And the footer section should contain a link with text "About" and a blank URL "#"