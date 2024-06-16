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
  Scenario: User searches for a product by name with mixed case
    Given I am on the shop page
    When I enter "JaCkEt" into the search bar
    And I press Enter
    Then I should see at least one product containing "jacka"

  @acceptance
  Scenario: User searches for a different product by name
    Given I am on the shop page
    When I enter "T-shirt" into the search bar
    And I press Enter
    Then I should see at least one product containing "t-shirt"

  @acceptance
  Scenario: User searches for a product by name with lowercase
    Given I am on the shop page
    When I enter "jacket" into the search bar
    And I press Enter
    Then I should see at least one product containing "jacka"

      ##Footer elements - Pierre Nilsson
  @acceptance
  Scenario: Verify footer elements
    Then the footer section should contain the text "© 2024 The Shop"
    And the footer section should contain a link with text "Home" and URL "/"
    And the footer section should contain a link with text "Shop" and URL "/products"
    And the footer section should contain a link with text "Checkout" and URL "/checkout"
    And the footer section should contain a link with text "About" and a blank URL "/about"

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

              ##Verify total amount rounding in cart - Pierre Nilsson
  @acceptance
  Scenario: Verify total amount rounding in cart
    Given I am on the shop pagepierre
    When I add "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops" to the cart
    And I add "Mens Casual Premium Slim Fit T-Shirts" to the cart
    When I navigate to the checkout page
    Then the total amount in the cart should be "$132.25"


  @acceptance #Jonas Nygren
  Scenario: Verify navigation elements
    Given I am on the about page
    Then I have section should contain a heading with text "About The Shop"
    Then I Section have text with informative text
    Then I have button have text "To all products"

    ##Presentation text on homepage - Deborah
  @acceptance
  Scenario: Verify updated presentation text on homepage
    Then I should see the headline "This shop is all you need"
    And I should see the description text "Welcome to The Shop, your premier online destination"

    ## Verify new link to About page in main menu - Deborah
  @acceptance
  Scenario: Navigate to About page from main menu
    Given I am on the homepage
    When I click on the About link in the main menu
    Then I should be redirected to the About page
    And the URL should match "https://webshop-agil-testautomatiserare.netlify.app/about"

    ## Verify new link to About page in footer - Deborah
  @acceptance
  Scenario: Navigate to About page from footer
    Given I am on the homepage
    When I click on the About link in the footer
    Then I should be redirected to the About page
    And the URL should match "https://webshop-agil-testautomatiserare.netlify.app/about"

    ## Ali Kazem Mahdey
  ## Check the update title for shop,about and checkout
  @acceptance
  Scenario: Check the Title for shop,about and checkout
    Given I am on the shop page
    When I click on shop page
    Then  Title must be Products
    When I click on about
    Then Title must be About
    When I click Checkout
    Then Title must be Checkout
