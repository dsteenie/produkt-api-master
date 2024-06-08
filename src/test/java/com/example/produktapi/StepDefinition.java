package com.example.produktapi;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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

    //Navigation to and inside Shop Page - Natalia

    @When("I click on shop link")
    public void I_click_on_shop_link() {
        driver.findElement(By.xpath("//a[@href='/products']")).click();
    }

    @Then("Shop page is shown")
    public void Shop_page_is_shown() {
        String websiteUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", websiteUrl);
    }

    @Given("I am on Shop page")
    public void i_am_on_Shop_page() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/products#");
    }

    @When("I click on All")
    public void i_click_on_All() {
        driver.findElement(By.xpath("//*/a[text()='All']")).click();
    }

    @Then("I see all products")
    public void i_see_all_products() throws InterruptedException {
        Thread.sleep(5000);
        List<WebElement> productCards = driver.findElements(By.xpath("//main/div/div/img"));
        int numberOfProductElements = productCards.size();
        Assertions.assertEquals(40, numberOfProductElements); /*I will revise this assertion in next sprint. source kod in browser shows 20 items but I see 40 when code is downloaded*/
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
        WebElement shopNav = driver.findElement(By.cssSelector("header div div ul li:nth-child(2)"));
        shopNav.click();
    }

    @Then("I should have navigated go to shop")//Jonas Nygren
    public void iShouldGoToShop() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/products", url, "URL does not match");
    }

    @When("I click on checkout")//Jonas Nygren
    public void iClickOnCheckout() {
        WebElement checkoutNav = driver.findElement(By.cssSelector("body header div div div a"));
        checkoutNav.click();
    }

    @Then("I should have navigated go to checkout page")//Jonas Nygren
    public void iShouldGoToCheckoutPage() {
        String url = driver.getCurrentUrl();
        Assertions.assertEquals("https://webshop-agil-testautomatiserare.netlify.app/checkout", url, "URL does not match");
    }
}