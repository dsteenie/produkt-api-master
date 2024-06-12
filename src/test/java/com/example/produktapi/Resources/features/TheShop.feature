Feature: The Shop

  Background: The Shop is available in browser
    Given I am on the homepage
    ##Natalia

  @acceptance
  Scenario:
      ## When user checks page title - Natalia
    Then title is "The Shop"

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
    Then I should have navigated go to shop
    When I click on checkout
    Then I should have navigated go to checkout page

         ## Navigation to Shop page - Natalia

 ## @acceptance - I comment out since this test is already made by NM above
 ## Scenario: Shop link in Top menu leads to product catalogue
   ## When I click on shop link
    ##Then Shop page is shown

  @acceptance
  Scenario: Button All products leads to Shop page
    When I click on button All products
    Then I see Shop page

     ##Navigation inside Shop page - Natalia Molina

  @acceptance
  Scenario: Filter all products
    Given I am on Shop page
    When I click on All
    Then I see all products

  @acceptance
  Scenario: Filter Men´s clothing
    Given I am on Shop page1
    When I click on Mens clothing
    Then I see all products in Mens

  @acceptance
  Scenario: Filter Women´s clothing
    Given I am on Shop page2
    When I click on Womens clothing
    Then I see all products in Womens

  @acceptance
  Scenario: Filter Jewelery
    Given I am on Shop page3
    When I click on Jewelery
    Then I see all products in Jewelery

  @acceptance
  Scenario: Filter Electronics
    Given I am on Shop page4
    When I click on Electronics
    Then I see all products in Electronics