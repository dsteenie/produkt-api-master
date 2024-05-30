package com.example.produktapi;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class StepDefinition {

    static WebDriver driver;


    //Beforemetod som instansierar webbdrivern - Natalia
    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().getPosition();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Background - Natalia
    @Given("user opens The shop in browser")
    public void user_opens_The_shop_in_browser() {
        driver.get("https://webshop-agil-testautomatiserare.netlify.app/");
    }

    @Then("title is {string}") //Natalia
    public void title_is(String expectedTitle) {
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }
}
