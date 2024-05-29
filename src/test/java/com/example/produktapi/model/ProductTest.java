package com.example.produktapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("Test Product", 100.0, "Test Category", "Test Description", "Test Image");
    }

    @Test //Deborah S
    @DisplayName("Test Get ID")
    public void testGetId() {
        assertNull(product.getId());
    }

    @Test //Deborah S
    @DisplayName("Test Set ID")
    public void testSetId() {
        product.setId(100);
        assertEquals(100, product.getId());
    }

        @Test
    @DisplayName("Correct Description") //Natalia Molina
    void testGetDescription () {
        assertEquals("Test Description", product.getDescription());
    }

    @Test
    @DisplayName("Change Description") //Natalia Molina
    void testGetDescription2 () {
        product.setDescription("descriptionTestChanged");
        assertEquals("descriptionTestChanged", product.getDescription());
    }
}

