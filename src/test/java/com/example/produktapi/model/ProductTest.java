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

        public Product(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    }

    @BeforeEach
    public void setUp() {
        product = new Product("Test Image");
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



}

