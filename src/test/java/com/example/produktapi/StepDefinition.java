package com.example.produktapi;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinition {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        this.driver = new ChromeDriver(options);
        this.driver.manage().window().maximize();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Background
    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app");
    }

    // Homepage title - Natalia
    @Then("title is {string}") // Natalia
    public void title_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    // Page load time - Natalia Molina
    long loadTime;

    @When("I measure page load time") //Natalia
    public void I_measure_page_load_time() {
        Instant start = Instant.now();
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
        Instant finish = Instant.now();
        loadTime = Duration.between(start, finish).toMillis();
    }

    @Then("Page is loaded within {int} milliseconds") //Natalia
    public void Page_is_loaded_within_seconds(int milliseconds) {
        assertTrue(loadTime < milliseconds, "Page did not load within " + milliseconds + "milliseconds. Load time: " + loadTime + " milliseconds.");
    }

    // Search functionality - Deborah Steenie
    @Given("I am on the shop page") //Deborah
    public void i_am_on_the_shop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }

    @When("I enter {string} into the search bar") //Deborah
    public void i_enter_into_the_search_bar(String searchQuery) {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchBar.sendKeys(searchQuery);
    }

    @Then("I should see at least one product containing {string}") //Deborah
    public void i_should_see_at_least_one_product_containing(String searchTerm) {
        WebElement productDescription = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(), '" + searchTerm + "')]")));
        assertNotNull(productDescription, "No product found containing the search term: " + searchTerm);
    }

    //Verify footer elements - Pierre Nilsson
    @Then("the footer section should contain the text {string}") //Pierre
    public void the_footer_section_should_contain_the_text(String expectedText) {
        WebElement footerText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer p.col-md-4.mb-0.text-muted")));
        String actualText = footerText.getText();
        Assertions.assertEquals(expectedText, actualText, "Footer text does not match.");
    }

    @Then("the footer section should contain a link with text {string} and URL {string}") //Pierre
    public void the_footer_section_should_contain_a_link_with_text_and_URL(String linkText, String expectedUrl) {
        WebElement footerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        String actualUrl = footerLink.getAttribute("href");
        Assertions.assertTrue(actualUrl.contains(expectedUrl), "Footer link URL does not match.");
    }

    @Then("the footer section should contain a link with text {string} and a blank URL {string}") //Pierre
    public void the_footer_section_should_contain_a_link_with_text_and_a_blank_URL(String linkText,
            String expectedUrl) {
        WebElement footerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        String actualUrl = footerLink.getAttribute("href");
        String baseUrl = "https://webshop-agil-testautomatiserare.netlify.app";
        String relativeUrl = actualUrl.replace(baseUrl, "");
        if (expectedUrl.equals("#")) {
            Assertions.assertTrue(relativeUrl.equals("#") || relativeUrl.equals("/#"),
                    "Footer link URL does not match.");
        } else {
            Assertions.assertEquals(expectedUrl, relativeUrl, "Footer link URL does not match.");
        }
    }

    // Add item to cart and check it-Ali Kazem Mahdy
    @When("I navigate to the shop") //ALi
    public void i_navigate_to_the_shop() {
        driver.findElement(By.cssSelector("body > header > div > div > ul > li:nth-child(2) > a")).click();

    }

    @When("I add Mens Cotton Jacket to the cart") //Ali
    public void i_add_mens_cotton_jacket_to_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addtocart = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[3]/div/div/button")));
        Actions addToCardBotton = new Actions(driver);
        addToCardBotton.moveToElement(addtocart).click().perform();

    }

    @Then("I should see the product in the cart") //Ali
    public void i_should_see_the_product_in_the_cart() {
        WebElement checkOut = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div/a")));
        String checkOutUrl = checkOut.getAttribute("href");
        driver.get(checkOutUrl);

    }

    @Then("the cart should have the item") //Ali
    public void the_cart_should_have_the_item() {
        WebElement cartCount = driver.findElement(By.xpath("//html/body/main/div[2]/div[1]/h4/span[2]"));
        String cartItemCount = cartCount.getText();
        Assertions.assertEquals(cartItemCount, "1");
    }

    //Verify navigation elements - Jonas Nygren
    @When("I click on home") // Jonas
    public void iClickOnHome() {
        WebElement homeNav = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(1)")));
        homeNav.click();
    }

    @Then("I should have navigated go to home") // Jonas
    public void iShouldGoToHome() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/", url, "URL does not match");
    }

    @When("I click on Shop") // Jonas
    public void iClickOnShop() {
        WebElement shopNav = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header div div ul li:nth-child(2)")));
        shopNav.click();
    }

    @Then("I should have navigated go to shop") // Jonas
    public void iShouldGoToShop() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", url,
                "URL does not match");
    }

    @When("I click on checkout") // Jonas
    public void iClickOnCheckout() {
        WebElement checkoutNav = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body header div div div a")));
        checkoutNav.click();
    }

    @Then("I should have navigated go to checkout page") // Jonas
    public void iShouldGoToCheckoutPage() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", url,
                "URL does not match");
    }

    // Verify button leads to Shop page - Natalia Molina
    @When("I click on button All products") // Natalia
    public void i_click_on_button_all_products() {
        driver.findElement(By.xpath("//*/button[text()='All products']")).click();
    }

    @Then("I see Shop page") // Natalia
    public void i_see_shop_page() {
        String websiteUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products.html", websiteUrl);
    }

    // Shop Page filtering menu - Natalia
    @Given("I am on Shop page") // Natalia
    public void i_am_on_Shop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
    }

    @When("I click on All") // Natalia
    public void i_click_on_All() {
        driver.findElement(By.xpath("//*/a[text()='All']")).click();
    }

    @Then("I see all products") // Natalia
    public void i_see_all_products() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productCards = driver.findElements(By.xpath("//main/div/div/img"));
        int numberOfProductElements = productCards.size();
        Assertions.assertEquals(40, numberOfProductElements);
    }

    @Given("I am on Shop page1") // Natalia
    public void i_am_on_Shop_page1() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(1000);
    }

    @When("I click on Mens clothing") // Natalia
    public void i_click_on_mens_clothing() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Men's clothing\"]")).click();
        Thread.sleep(1000);
    }

    @Then("I see all products in Mens") // Natalia
    public void i_see_all_products_in_Mens() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productCardButtons = driver
                .findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"mens clothing\""));
        }
    }

    @Given("I am on Shop page2") // Natalia
    public void i_am_on_Shop_page2() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(1000);
    }

    @When("I click on Womens clothing") // Natalia
    public void i_click_on_Womens_clothing() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Women's clothing\"]")).click();
        Thread.sleep(1000);
    }

    @Then("I see all products in Womens") // Natalia
    public void i_see_all_products_in_Womens() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productCardButtons = driver
                .findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"womens clothing\""));
        }
    }

    @Given("I am on Shop page3") // Natalia
    public void i_am_on_Shop_page3() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(1000);
    }

    @When("I click on Jewelery")
    public void i_click_on_jewelery() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Jewelery\"]")).click();
        Thread.sleep(1000);
    }

    @Then("I see all products in Jewelery") // Natalia
    public void i_see_all_products_in_Jewelery() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productCardButtons = driver
                .findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"jewelery\""));
        }
    }

    @Given("I am on Shop page4") // Natalia
    public void i_am_on_Shop_page4() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(1000);
    }

    @When("I click on Electronics") // Natalia
    public void i_click_on_Electronics() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Electronics\"]")).click();
        Thread.sleep(1000);
    }

    @Then("I see all products in Electronics") // Natalia
    public void i_see_all_products_in_Electronics() throws InterruptedException {
        Thread.sleep(1000);
        List<WebElement> productCardButtons = driver
                .findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"electronics\""));
        }
    }

    // Verify navigation elements on about page - Jonas Nygren
    @Given("^I am on the about page$") // Jonas
    public void iAmOnTheAboutPage() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/about");
    }

    @Then("I have section should contain a heading with text {string}") //Jonas
    public void iHaveSectionShouldContainAHeadingWithText(String arg0) {
        WebElement aboutHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body div.container.my-5  div  h2")));
        Assertions.assertEquals(arg0, aboutHeading.getText());
    }

    @Then("I Section have text with informative text") //Jonas
    public void iSectionHaveTextWithInformativeText() {
        WebElement aboutp = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body div.container.my-5 div p")));
        Assertions.assertEquals(
                "Welcome to The Shop, your premier online destination for all things fashion, electronics, and jewelry! At The Shop, we pride ourselves on offering a curated selection of high-quality products to cater to your diverse needs and desires.\n"
                        +
                        "\n" +
                        "Explore our extensive range of fashion, featuring stylish attire for every occasion. Whether you're gearing up for a formal event or a laid-back weekend, we've got you covered with the latest trends and timeless classics.\n"
                        +
                        "\n" +
                        "Tech enthusiasts will be delighted by our assortment of cutting-edge electronics, including smartphones, laptops, tablets, and accessories. Stay connected and ahead of the curve with the latest gadgets from top brands in the industry.\n"
                        +
                        "\n" +
                        "Elevate your style with our stunning jewelry pieces, carefully crafted to add a touch of glamour to any outfit. From delicate necklaces and sparkling earrings to sleek watches and bold bracelets, our jewelry collection offers something for every personality and preference.\n"
                        +
                        "\n" +
                        "At The Shop, we are dedicated to providing our customers with an exceptional shopping experience, from seamless navigation to prompt delivery and stellar customer service. Shop with confidence and discover the perfect blend of fashion, technology, and elegance at The Shop!",
                aboutp.getText());
    }

    @Then("I have button have text {string}") //Jonas
    public void iHaveButtonHaveText(String arg0) {
        WebElement aboutButton = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("body div.container.my-5 div div button")));
        Assertions.assertEquals(arg0, aboutButton.getText());
    }

    //Verify total amount rounding in cart with decimal and Toggling payment methods on the checkout page  - Pierre Nilsson
    @Given("I am on the shop pagepierre") // Pierre
    public void i_am_on_the_shop_pagepierre() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
        Assertions.assertEquals("The Shop | Products", driver.getTitle(), "Title is not as expected");
    }

    @When("I add {string} to the cart") // Pierre
    public void i_add_to_the_cart(String productName) {
        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("complete"));

        WebElement productElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='" + productName + "']")));
        Assertions.assertNotNull(productElement, "Product element not found: " + productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productElement);

        WebElement productButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
                "//h3[text()='" + productName + "']/following-sibling::button[contains(text(), 'Add to cart')]")));
        Assertions.assertNotNull(productButton, "Add to cart button not found for product: " + productName);

        try {
            productButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productButton);
            productButton.click();
        }
    }

    @When("I navigate to the checkout page") // Pierre
    public void i_navigate_to_the_checkout_page() {
        WebElement checkoutButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/checkout'][@type='button']")));
        Assertions.assertNotNull(checkoutButton, "Checkout button not found");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        try {
            checkoutButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
            checkoutButton.click();
        }

        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                .executeScript("return document.readyState").equals("complete"));

        Assertions.assertEquals("The Shop | Checkout", driver.getTitle(), "Checkout page title is not as expected");
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", driver.getCurrentUrl(),
                "Checkout page URL is not as expected");
    }

    @Then("the total amount in the cart should be {string}") // Pierre
    public void the_total_amount_in_the_cart_should_be(String expectedTotal) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
                    .executeScript("return document.readyState").equals("complete"));

            Assertions.assertEquals("The Shop | Checkout", driver.getTitle(), "Checkout page title is not as expected");
            Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout",
                    driver.getCurrentUrl(), "Checkout page URL is not as expected");

            WebElement cartTotalElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='cartList']/li[3]/strong")));
            Assertions.assertNotNull(cartTotalElement, "Cart total element not found");
            String actualTotal = cartTotalElement.getText();
            Assertions.assertEquals(expectedTotal, actualTotal,
                    "The total amount in the cart is not correctly rounded.");
            Assertions.assertTrue(actualTotal.matches("\\$\\d+\\.\\d{2}"),
                    "The total amount is not rounded to two decimal places: " + actualTotal);
        } catch (TimeoutException e) {
            System.out.println(
                    "TimeoutException: Expected condition failed. Element not found within the specified timeout.");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source: " + driver.getPageSource());
            throw e;
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Source: " + driver.getPageSource());
            throw e;
        }
    }

    @When("I select PayPal as the payment method") //Pierre
    public void i_select_paypal_as_the_payment_method() {
        WebElement paypalRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("paypal")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", paypalRadio);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", paypalRadio);
        System.out.println("PayPal radio button clicked");
    }

    @Then("the credit card fields should be hidden") //Pierre
    public void the_credit_card_fields_should_be_hidden() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("card")));
        WebElement cardInfoSection = driver.findElement(By.id("card"));
        assertFalse(cardInfoSection.isDisplayed());
        System.out.println("Credit card fields are hidden");
    }

    @Then("the PayPal message should be visible") //Pierre
    public void the_paypal_message_should_be_visible() {
        WebElement paypalInfoSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("paypalInfo")));
        assertTrue(paypalInfoSection.isDisplayed());
        System.out.println("PayPal message is visible");
    }

    @When("I select Credit card as the payment method") //Pierre
    public void i_select_credit_card_as_the_payment_method() {
        WebElement creditCardRadio = wait.until(ExpectedConditions.elementToBeClickable(By.id("credit")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", creditCardRadio);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", creditCardRadio);
        System.out.println("Credit card radio button clicked");
    }

    @Then("the credit card fields should be visible") //Pierre
    public void the_credit_card_fields_should_be_visible() {
        WebElement cardInfoSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card")));
        assertTrue(cardInfoSection.isDisplayed());
        System.out.println("Credit card fields are visible");
    }

    @Then("the PayPal message should be hidden") //Pierre
    public void the_paypal_message_should_be_hidden() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("paypalInfo")));
        WebElement paypalInfoSection = driver.findElement(By.id("paypalInfo"));
        assertFalse(paypalInfoSection.isDisplayed());
        System.out.println("PayPal message is hidden");
        driver.quit();
    }

    // Presentation text on homepage - Deborah
    @Then("I should see the headline {string}") //Deborah
    public void i_should_see_the_headline(String expectedHeadline) {
        WebElement headlineElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.display-4")));
        String actualHeadline = headlineElement.getText().trim();
        assertEquals(expectedHeadline, actualHeadline);
    }

    @Then("I should see the description text {string}") //Deborah
    public void i_should_see_the_description_text(String expectedText) {
        WebElement descriptionElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.lead")));

        String actualDescription = descriptionElement.getText().trim();
        System.out.println("Actual description text: '" + actualDescription + "'");

        assertTrue(actualDescription.contains(expectedText),
                "Expected description text to contain: '" + expectedText + "', but found: '" + actualDescription + "'");
    }

    // Verify new link to About page in main menu - Deborah
    @When("I click on the About link in the main menu") //Deborah
    public void i_click_on_the_about_link_in_the_main_menu() {
        WebElement aboutNav = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header div div ul li:nth-child(3)")));
        aboutNav.click();
    }

    @Then("I should be redirected to the About page") //Deborah
    public void i_should_be_redirected_to_the_about_page() {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/about", currentUrl);
    }

    @Then("the URL should match {string}") //Deborah
    public void the_url_should_match(String expectedUrl) {
        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals(expectedUrl, currentUrl);
    }

    // Verify new link to About page in footer - Deborah
    @When("I click on the About link in the footer") //Deborah
    public void i_click_on_the_about_link_in_the_footer() {
        WebElement aboutFooterLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer .nav-item a[href='/about']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", aboutFooterLink);
    }

    // Check the update title for shop,about and checkout - Ali Kazem Mahdy
    @When("I click on Shop page") //Ali
    public void i_click_on_shop_page() {
        driver.findElement(By.cssSelector("body > header > div > div > ul > li:nth-child(2) > a")).click();

    }

    @Then("The Title should be The Shop | Products") //Ali
    public void the_title_should_be_the_shop_products() {
        String titleShop = driver.getTitle();
        Assertions.assertEquals(titleShop, "The Shop | Products");
    }

    @When("I click on About") //Ali
    public void i_click_on_about() {
        driver.findElement(By.cssSelector("body > header > div > div > ul > li:nth-child(3) > a")).click();
    }

    @Then("The Title should be The Shop | About") //Ali
    public void the_title_should_be_the_shop_about() {
        String titleAbout = driver.getTitle();
        Assertions.assertEquals(titleAbout, "The Shop | About");
    }

    @When("I click Checkout") //Ali
    public void i_click_checkout() {
        driver.findElement(By.cssSelector("body > header > div > div > div > a")).click();
    }

    @Then("The Title should be The Shop | Checkout") //Ali
    public void the_title_should_be_the_shop_checkout() {
        String titleCheckOut = driver.getTitle();
        Assertions.assertEquals(titleCheckOut, "The Shop | Checkout");

    }

    // Remove item from cart and verify updates - Deborah Steenie
    @When("I remove {string} from the cart") //Deborah
    public void i_remove_from_the_cart(String productName) {
        WebElement removeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='" + productName + "']/following-sibling::button[contains(text(), 'Remove')]")));
        removeButton.click();
    }

    @Then("the cart should have {int} items") //Deborah
    public void the_cart_should_have_items(int expectedItemCount) {
        WebElement cartSizeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartSize")));
        int actualItemCount = Integer.parseInt(cartSizeElement.getText());
        Assertions.assertEquals(expectedItemCount, actualItemCount, "Cart size does not match the expected value.");
    }

    @Then("the cart total amount should be {string}") //Deborah
    public void the_cart_total_amount_should_be(String expectedTotal) {
        WebElement totalAmountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//li[@class='list-group-item d-flex justify-content-between']/strong")));
        String actualTotal = totalAmountElement.getText();
        Assertions.assertEquals(expectedTotal, actualTotal, "Total amount does not match the expected value.");
    }

    @Then("the checkout button in the header should display {int} items") //Deborah
    public void the_checkout_button_in_the_header_should_display_items(int expectedItemCount) {
        WebElement checkoutButton = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a.btn.btn-warning span.badge")));
        int actualItemCount = Integer.parseInt(checkoutButton.getText());
        Assertions.assertEquals(expectedItemCount, actualItemCount,
                "Checkout button item count does not match the expected value.");
    }

    //Validate billing address - Jonas Nygren
    @When("I write First name {string}") //Jonas
    public void iWriteFirstName(String arg0) {
        driver.findElement(By.id("firstName")).sendKeys(arg0);
    }

    @When("I write Last name {string}") //Jonas
    public void iWriteLastName(String arg0) {
        driver.findElement(By.id("lastName")).sendKeys(arg0);
    }

    @When("I write Email {string}") //Jonas
    public void iWriteEmail(String arg0) {
        driver.findElement(By.id("email")).sendKeys(arg0);
    }

    @When("I write Address {string}") //Jonas
    public void iWriteAddress(String arg0) {
        driver.findElement(By.id("address")).sendKeys(arg0);
    }

    @When("I write Country {string}") //Jonas
    public void iWriteCountry(String arg0) {
        driver.findElement(By.id("country")).sendKeys(arg0);
    }

    @When("I write City {string}") //Jonas
    public void iWriteCity(String arg0) {
        driver.findElement(By.id("city")).sendKeys(arg0);
    }

    @When("I write Zip {string}") //Jonas
    public void iWriteZip(String arg0) {
        driver.findElement(By.id("zip")).sendKeys(arg0);
    }

    @Then("I Vailidate error messages and border is red") //Jonas
    public void iVailidateErrorMessages() throws InterruptedException {
        Thread.sleep(5000);
        // First name
        WebElement errorFirstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(1) > div")));
        Assertions.assertEquals("Valid first name is required.", errorFirstName.getText());

        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("firstName")).getCssValue("border-color"));


        // Lastname
        WebElement errorLastName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(2) > div")));
        Assertions.assertEquals("Valid last name is required.", errorLastName.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("lastName")).getCssValue("border-color"));

        // Email
        WebElement errorEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(3) > div")));
        Assertions.assertEquals("Please enter a valid email address for shipping updates.", errorEmail.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("email")).getCssValue("border-color"));

        // Address
        WebElement errorAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(4) > div")));
        Assertions.assertEquals("Please enter your shipping address.", errorAddress.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("address")).getCssValue("border-color"));

        // Country
        WebElement errorCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(5) > div")));
        Assertions.assertEquals("Please select a valid country.", errorCountry.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("country")).getCssValue("border-color"));

        // City
        WebElement errorCity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(6) > div")));
        Assertions.assertEquals("Please provide a valid state.", errorCity.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("city")).getCssValue("border-color"));

        // zip
        WebElement errorZip = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
                "body > main > div.row.g-5 > div.col-md-7.col-lg-6 > form > div.row.g-3 > div:nth-child(7) > div")));
        Assertions.assertEquals("Zip code required.", errorZip.getText());
        assertEquals("rgb(220, 53, 69)", driver.findElement(By.id("zip")).getCssValue("border-color"));
    }

    @When("I Click on Button Continue to checkout") //Jonas
    public void iClickOnButtonContinueToCheckout() {
        WebElement checkoutButton = wait.until(ExpectedConditions
                .elementToBeClickable(By.xpath("//button[contains(text(), 'Continue to checkout')]")));
        checkoutButton.submit();
    }

    @Then("I Check if border is green") //Jonas
    public void iCheckIfValidateValuesInFormIsValid() throws InterruptedException {
        Thread.sleep(5000);

        // First name
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("firstName")).getCssValue("border-color"));

        // Lastname
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("lastName")).getCssValue("border-color"));

        // Email
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("email")).getCssValue("border-color"));

        // Address
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("address")).getCssValue("border-color"));

        // Country
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("country")).getCssValue("border-color"));

        // City
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("city")).getCssValue("border-color"));

        // zip
        assertEquals("rgb(25, 135, 84)", driver.findElement(By.id("zip")).getCssValue("border-color"));
    }
}