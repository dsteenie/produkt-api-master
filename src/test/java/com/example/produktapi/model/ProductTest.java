package com.example.produktapi.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
    }

    @Test
    @DisplayName("check the get title")
    void checkGetTitle() {
        assertNull(product.getTitle());
    }

    @Test
    @DisplayName("check the set title")
    public void checkSetTitle() {
        product.setTitle("Mens Cotton Jacket");

        assertEquals("Mens Cotton Jacket", product.getTitle());
    }
}
