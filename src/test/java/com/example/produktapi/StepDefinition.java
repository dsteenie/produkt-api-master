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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

}
