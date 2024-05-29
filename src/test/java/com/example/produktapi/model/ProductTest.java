package com.example.produktapi.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    private Product product;

    static class Product {
        private Integer id;
        private String imageUrl;
        private String description;

        public Product(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    @BeforeEach
    public void setUp() {
        product = new Product("Test Image");
        product.setDescription("Test Description");
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

    @Test //Pierre
    @DisplayName("Test Get Image URL")
    public void testGetImageUrl() {
        assertEquals("Test Image", product.getImageUrl());
    }

    @Test //Pierre
    @DisplayName("Test Set Image URL")
    public void testSetImageUrl() {
        product.setImageUrl("http://example.com/image.jpg");
        assertEquals("http://example.com/image.jpg", product.getImageUrl());
    }

    @Test //Natalia Molina
    @DisplayName("Correct Description")
    void testGetDescription () {
        assertEquals("Test Description", product.getDescription());
    }

    @Test //Natalia Molina
    @DisplayName("Change Description")
    void testGetDescription2 () {
        product.setDescription("descriptionTestChanged");
        assertEquals("descriptionTestChanged", product.getDescription());
    }
}