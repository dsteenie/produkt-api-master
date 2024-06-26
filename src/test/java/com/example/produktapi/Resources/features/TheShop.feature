Feature: The Shop

  Background: The Shop is available in browser
    Given I am on the homepage

  # Homepage Tests
  @acceptance
  Scenario: Verify homepage title
      ## When user checks page title - Natalia Molina
    Then title is "The Shop"

  @acceptance
  Scenario: Verify updated presentation text on homepage
    ## Presentation text on homepage - Deborah Steenie
    Then I should see the headline "This shop is all you need"
    And I should see the description text "Welcome to The Shop, your premier online destination"

  @acceptance
  Scenario: Check the Title for shop,about and checkout
    ## Check the update title for shop,about and checkout - Ali Kazem Mahdy
    Given I am on the shop page
    When  I click on Shop page
    Then  The Title should be The Shop | Products
    When  I click on About
    Then  The Title should be The Shop | About
    When  I click Checkout
    Then  The Title should be The Shop | Checkout

 # Navigation Tests
  @acceptance
  Scenario: Verify navigation elements
    ## Verify navigation elements - Jonas Nygren
    Given I am on the homepage
    When I click on home
    Then I should have navigated go to home
    When I click on Shop
    Then I should have navigated go to shop
    When I click on checkout
    Then I should have navigated go to checkout page

  @acceptance
  Scenario: Button All products leads to Shop page
    ## Verify button leads to Shop page - Natalia Molina
    When I click on button All products
    Then I see Shop page

  @acceptance
  Scenario: Verify navigation elements
    ## Verify navigation elements on about page - Jonas Nygren
    Given I am on the about page
    Then I have section should contain a heading with text "About The Shop"
    Then I Section have text with informative text
    Then I have button have text "To all products"

  @acceptance
  Scenario: Navigate to About page from main menu
    ## Verify new link to About page in main menu - Deborah Steenie
    Given I am on the homepage
    When I click on the About link in the main menu
    Then I should be redirected to the About page
    And the URL should match "https://webshop-agil-testautomatiserare.netlify.app/about"

  @acceptance
  Scenario: Navigate to About page from footer
     ## Verify new link to About page in footer - Deborah Steenie
    Given I am on the homepage
    When I click on the About link in the footer
    Then I should be redirected to the About page
    And the URL should match "https://webshop-agil-testautomatiserare.netlify.app/about"

  # Product Filtering
  @acceptance
  Scenario: Filter all products
    ## Filter all products - Natalia Molina
    Given I am on Shop page
    When I click on All
    Then I see all products

  @acceptance
  Scenario: Filter Men´s clothing
    ## Filter men's clothing - Natalia Molina
    Given I am on Shop page1
    When I click on Mens clothing
    Then I see all products in Mens

  @acceptance
  Scenario: Filter Women´s clothing
    ## Filter women's clothing - Natalia Molina
    Given I am on Shop page2
    When I click on Womens clothing
    Then I see all products in Womens

  @acceptance
  Scenario: Filter Jewelery
    ## Filter jewelery - Natalia Molina
    Given I am on Shop page3
    When I click on Jewelery
    Then I see all products in Jewelery

  @acceptance
  Scenario: Filter Electronics
    ## Filter electronics - Natalia Molina
    Given I am on Shop page4
    When I click on Electronics
    Then I see all products in Electronics

  # Cart and Checkout Tests
  @acceptance
  Scenario: Add a single product to the cart
    ## Add item to cart - Ali Kazem Mahdy
    Given I am on the homepage
    When I navigate to the shop
    And I add Mens Cotton Jacket to the cart
    Then I should see the product in the cart
    And the cart should have the item

  @acceptance
  Scenario: Verify total amount rounding in cart & Toggling payment methods on the checkout page
    ## Verify total amount rounding in cart with decimal and Toggling payment methods on the checkout page  - Pierre Nilsson
    Given I am on the shop pagepierre
    When I add "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops" to the cart
    And I add "Mens Casual Premium Slim Fit T-Shirts" to the cart
    When I navigate to the checkout page
    Then the total amount in the cart should be "$132.25"
    When I select PayPal as the payment method
    Then the credit card fields should be hidden
    And the PayPal message should be visible
    When I select Credit card as the payment method
    Then the credit card fields should be visible
    And the PayPal message should be hidden

  @acceptance
  Scenario: Remove an item from the cart and verify updates
    ## Remove item from cart and verify updates - Deborah Steenie
    Given I am on the shop page
    When I add "Mens Cotton Jacket" to the cart
    And I add "Mens Casual Premium Slim Fit T-Shirts" to the cart
    And I add "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops" to the cart
    And I navigate to the checkout page
    When I remove "Mens Cotton Jacket" from the cart
    Then the cart should have 2 items
    And the cart total amount should be "$132.25"
    And the checkout button in the header should display 2 items

  @acceptance
  Scenario: Validate billing address
    ## Validate billing address - Jonas Nygren
    Given I am on the homepage
    And I navigate to the checkout page
    When I write First name "Test"
    When I write Last name "Testson"
    When I write Email "Testson@test.se"
    When I write Address "Test gatan 1"
    When I write Country "Test landet"
    When I write City "Test staden"
    When I write Zip "57754"
    When I Click on Button Continue to checkout
    Then I Check if border is green

  @acceptance
  Scenario: Validate billing address with empty string give error message
    ## Validate billing address with empty fields - Jonas Nygren
    Given I am on the homepage
    And I navigate to the checkout page
    When I write First name ""
    When I write Last name ""
    When I write Email ""
    When I write Address ""
    When I write Country ""
    When I write City ""
    When I write Zip ""
    When I Click on Button Continue to checkout
    Then I Vailidate error messages and border is red

  # Search functionality
  @acceptance
  Scenario Outline: User searches for a product by name
    ## Search functionality - Deborah Steenie
    Given I am on the shop page
    When I enter "<product_name>" into the search bar
    Then I should see at least one product containing "<expected_result>"

    Examples:
      | product_name | expected_result |
      | JaCkEt       | jacka           |
      | T-shirt      | t-shirt         |
      | jacket       | jacka           |

  # Footer elements
  @acceptance
  Scenario: Verify footer elements
    ##Verify footer elements - Pierre Nilsson
    Then the footer section should contain the text "© 2024 The Shop"
    And the footer section should contain a link with text "Home" and URL "/"
    And the footer section should contain a link with text "Shop" and URL "/products"
    And the footer section should contain a link with text "Checkout" and URL "/checkout"
    And the footer section should contain a link with text "About" and a blank URL "/about"

# Performance Tests
  @acceptance
  Scenario: Page load time
    ## Page load time - Natalia Molina
    When I measure page load time
    Then Page is loaded within 3000 milliseconds



