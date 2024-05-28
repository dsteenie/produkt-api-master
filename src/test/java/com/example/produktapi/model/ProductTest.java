package com.example.produktapi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProductTest {

 //Enhetstester av Product Description
    Product product;

        @BeforeEach //This will run before each test - Natalia Molina
        void beforeTest(){
            System.out.println("Run Test");
            product = new Product ("titleTest", 1000d, "categoryTest", "descriptionTest", "imageTest.jpg");
        }

        @Test
    @DisplayName("Correct Description") //Natalia Molina
    void testGetDescription () {
            Assertions.assertEquals("descriptionTest", product.getDescription());
    }

    @Test
    @DisplayName("Change Description") //Natalia Molina
    void testGetDescription2 () {
        product.setDescription("descriptionTestChanged");
        Assertions.assertEquals("descriptionTestChanged", product.getDescription());
    }

}

