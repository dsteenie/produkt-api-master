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

      ## Go to Shop page - Natalia
  @acceptance
  Scenario: Shop link in Top menu leads to product catalogue
    When I click on shop link
    Then Shop page is shown

     ## Filter all products in Shop Page - Natalia
  @acceptance
  Scenario: Selection All shows all products
    Given I am on Shop page
    When I click on All
    Then I see all products

   ##add item to cart and check it-Ali Kazem Mahdy
  @acceptance
  Scenario: Add a single product to the cart
    Given I am on the homepage
    When I navigate to the shop
    And I add Mens Cotton Jacket to the cart
    Then I should see the product in the cart
    And the cart should have the item

        ##navigation -Jonas Nygren
  @acceptance
  Scenario: Verify navigation elements
    Given I am on the homepage
    When I click on home
    Then I should have navigated go to home
    When I click on Shop
    Then I should have navigated go toshop
    When I click on checkout
    Then I should have navigated go to checkout page