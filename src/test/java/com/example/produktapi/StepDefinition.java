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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    //Background
    @Given("I am on the homepage")
    public void i_am_on_the_homepage() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app");
    }

    //Homepage title - Natalia
    @Then("title is {string}") //Natalia
    public void title_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    //Search functionality - Deborah
    @Given("I am on the shop page")
    public void i_am_on_the_shop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
    }

    @When("I enter {string} into the search bar")
    public void i_enter_into_the_search_bar(String searchQuery) {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchBar.sendKeys(searchQuery);
    }

    @When("I press Enter")
    public void i_press_enter() {
        WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search")));
        searchBar.sendKeys(Keys.ENTER);
    }

    @Then("I should see a product with the description {string}")
    public void i_should_see_a_product_with_the_description(String expectedDescription) {
        WebElement productDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='card-text' and text()='" + expectedDescription + "']")));
        assertNotNull(productDescription, "Expected description: " + expectedDescription + " not found.");
    }

    //Footer elements - Pierre
    @Then("the footer section should contain the text {string}")
    public void the_footer_section_should_contain_the_text(String expectedText) {
        WebElement footerText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("footer p.col-md-4.mb-0.text-muted")));
        String actualText = footerText.getText();
        Assertions.assertEquals(expectedText, actualText, "Footer text does not match.");
    }

    @Then("the footer section should contain a link with text {string} and URL {string}")
    public void the_footer_section_should_contain_a_link_with_text_and_URL(String linkText, String expectedUrl) {
        WebElement footerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        String actualUrl = footerLink.getAttribute("href");
        Assertions.assertTrue(actualUrl.contains(expectedUrl), "Footer link URL does not match.");
    }

    @Then("the footer section should contain a link with text {string} and a blank URL {string}")
    public void the_footer_section_should_contain_a_link_with_text_and_a_blank_URL(String linkText, String expectedUrl) {
        WebElement footerLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(linkText)));
        String actualUrl = footerLink.getAttribute("href");
        String baseUrl = "https://webshop-agil-testautomatiserare.netlify.app";
        String relativeUrl = actualUrl.replace(baseUrl, "");
        if (expectedUrl.equals("#")) {
            Assertions.assertTrue(relativeUrl.equals("#") || relativeUrl.equals("/#"), "Footer link URL does not match.");
        } else {
            Assertions.assertEquals(expectedUrl, relativeUrl, "Footer link URL does not match.");
        }
    }

    //add item to cart and check it-Ali Kazem Mahdy
    @When("I navigate to the shop")
    public void i_navigate_to_the_shop() {
        driver.findElement(By.cssSelector("body > header > div > div > ul > li:nth-child(2) > a")).click();

    }
    @When("I add Mens Cotton Jacket to the cart")
    public void i_add_mens_cotton_jacket_to_the_cart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addtocart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[3]/div/div/button")));
        Actions addToCardBotton = new Actions(driver);
        addToCardBotton.moveToElement(addtocart).click().perform();

    }
    @Then("I should see the product in the cart")
    public void i_should_see_the_product_in_the_cart() {
        WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/div/div/a")));
        String checkOutUrl = checkOut.getAttribute("href");
        driver.get(checkOutUrl);

    }
    @Then("the cart should have the item")
    public void the_cart_should_have_the_item() {
        WebElement cartCount = driver.findElement(By.xpath("//html/body/main/div[2]/div[1]/h4/span[2]"));
        String cartItemCount = cartCount.getText();
        Assertions.assertEquals(cartItemCount,"1");
    }

    @When("I click on home") //Jonas Nygren
    public void iClickOnHome() {
        WebElement homeNav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li:nth-child(1)")));
        homeNav.click();
    }

    @Then("I should have navigated go to home") //Jonas Nygren
    public void iShouldGoToHome() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/", url, "URL does not match");
    }

    @When("I click on Shop")//Jonas Nygren
    public void iClickOnShop() {
        WebElement shopNav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header div div ul li:nth-child(2)")));
        shopNav.click();
    }

    @Then("I should have navigated go to shop")//Jonas Nygren
    public void iShouldGoToShop() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", url, "URL does not match");
    }

    @When("I click on checkout")//Jonas Nygren
    public void iClickOnCheckout() {
        WebElement checkoutNav = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body header div div div a")));
        checkoutNav.click();
    }

    @Then("I should have navigated go to checkout page")//Jonas Nygren
    public void iShouldGoToCheckoutPage() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", url, "URL does not match");
    }

    //Navigation from Home to Shop Page

   /*@When("I click on shop link") Natalia: I commented out since there is already a test of this menu above by JN
    public void I_click_on_shop_link() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();
    }

    @Then("Shop page is shown")
    public void Shop_page_is_shown() {
        String websiteUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", websiteUrl);
    }*/

    @When("I click on button All products") //Natalia
    public void i_click_on_button_all_products() {
        driver.findElement(By.xpath("//*/button[text()='All products']")).click();
    }

    @Then("I see Shop page") //Natalia
    public void i_see_shop_page() {
        String websiteUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products.html", websiteUrl);
    }

    //Shop Page filtering menu - Natalia

    @Given("I am on Shop page") //Natalia
    public void i_am_on_Shop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
    }

    @When("I click on All") //Natalia
    public void i_click_on_All() {
        driver.findElement(By.xpath("//*/a[text()='All']")).click();
    }

    @Then("I see all products") //Natalia
    public void i_see_all_products() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCards = driver.findElements(By.xpath("//main/div/div/img"));
        int numberOfProductElements = productCards.size();
        Assertions.assertEquals(40, numberOfProductElements); /*I will revise this assertion in next sprint. source kod in browser shows 20 items but I see 40 when code is downloaded*/
    }

    @Given("I am on Shop page1") //Natalia
    public void i_am_on_Shop_page1() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(5000);
    }

    @When("I click on Mens clothing") //Natalia
    public void i_click_on_mens_clothing() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Men's clothing\"]")).click();
        Thread.sleep(5000);
    }

    @Then("I see all products in Mens") //Natalia
    public void i_see_all_products_in_Mens() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCardButtons = driver.findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"mens clothing\""));
        }
    }

    @Given("I am on Shop page2") //Natalia
    public void i_am_on_Shop_page2() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(5000);
    }

    @When("I click on Womens clothing") //Natalia
    public void i_click_on_Womens_clothing() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Women's clothing\"]")).click();
        Thread.sleep(5000);
    }

    @Then("I see all products in Womens") //Natalia
    public void i_see_all_products_in_Womens() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCardButtons = driver.findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"womens clothing\""));
        }
    }

    @Given("I am on Shop page3") //Natalia
    public void i_am_on_Shop_page3() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(5000);
    }

    @When("I click on Jewelery")
    public void i_click_on_jewelery() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Jewelery\"]")).click();
        Thread.sleep(5000);
    }

    @Then("I see all products in Jewelery") //Natalia
    public void i_see_all_products_in_Jewelery() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCardButtons = driver.findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"jewelery\""));
        }
    }

    @Given("I am on Shop page4") //Natalia
    public void i_am_on_Shop_page4() throws InterruptedException {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
        Thread.sleep(5000);
    }

    @When("I click on Electronics") //Natalia
    public void i_click_on_Electronics() throws InterruptedException {
        driver.findElement(By.xpath("//*/a[text()=\"Electronics\"]")).click();
        Thread.sleep(5000);
    }

    @Then("I see all products in Electronics") //Natalia
    public void i_see_all_products_in_Electronics() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCardButtons = driver.findElements(By.xpath("//main/div/div/div[@class='card-body']/button"));
        for (WebElement button : productCardButtons) {
            Assertions.assertTrue(button.getDomAttribute("onclick").contains("\"category\":\"electronics\""));
        }
    }

    @Given("I am on the shop pagepierre")//Pierre
    public void i_am_on_the_shop_pagepierre() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products");
        Assertions.assertEquals("The Shop | Products", driver.getTitle(), "Title is not as expected");
    }

    @When("I add {string} to the cart")//Pierre
    public void i_add_to_the_cart(String productName) {
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        WebElement productElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='" + productName + "']")));
        Assertions.assertNotNull(productElement, "Product element not found: " + productName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productElement);

        WebElement productButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h3[text()='" + productName + "']/following-sibling::button[contains(text(), 'Add to cart')]")));
        Assertions.assertNotNull(productButton, "Add to cart button not found for product: " + productName);

        try {
            productButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productButton);
            productButton.click();
        }
    }

    @When("I navigate to the checkout page") //Pierre
    public void i_navigate_to_the_checkout_page() {
        WebElement checkoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/checkout'][@type='button']")));
        Assertions.assertNotNull(checkoutButton, "Checkout button not found");

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);

        try {
            checkoutButton.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
            checkoutButton.click();
        }

        wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        Assertions.assertEquals("The Shop | Checkout", driver.getTitle(), "Checkout page title is not as expected");
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", driver.getCurrentUrl(), "Checkout page URL is not as expected");
    }

    @Then("the total amount in the cart should be {string}") //Pierre
    public void the_total_amount_in_the_cart_should_be(String expectedTotal) {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(60));
            wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

            Assertions.assertEquals("The Shop | Checkout", driver.getTitle(), "Checkout page title is not as expected");
            Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", driver.getCurrentUrl(), "Checkout page URL is not as expected");

            WebElement cartTotalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='cartList']/li[3]/strong")));
            Assertions.assertNotNull(cartTotalElement, "Cart total element not found");
            String actualTotal = cartTotalElement.getText();
            Assertions.assertEquals(expectedTotal, actualTotal, "The total amount in the cart is not correctly rounded.");
            Assertions.assertTrue(actualTotal.matches("\\$\\d+\\.\\d{2}"), "The total amount is not rounded to two decimal places: " + actualTotal);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Expected condition failed. Element not found within the specified timeout.");
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
}