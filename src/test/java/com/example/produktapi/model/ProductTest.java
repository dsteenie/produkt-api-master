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
        private double price;
        private String title;

        public Product(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public Integer getId() { return id; }
        public void setId(Integer id) { this.id = id; }

        public String getImageUrl() { return imageUrl; }
        public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public double getPrice() { return price; }
        public void setPrice(Double price) { this.price = price; }

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
    }

    @BeforeEach
    public void setUp() {
        product = new Product("Test Image");
        product.setDescription("Test Description");
        product.setPrice(12.2);
    }

    @Test //Deborah Steenie
    @DisplayName("Test Get ID")
    public void testGetId() {
        assertNull(product.getId());
    }

    @Test //Deborah Steenie
    @DisplayName("Test Set ID")
    public void testSetId() {
        product.setId(100);
        assertEquals(100, product.getId());
    }

    @Test //Pierre Nilsson
    @DisplayName("Test Get Image URL")
    public void testGetImageUrl() {
        assertEquals("Test Image", product.getImageUrl());
    }

    @Test //Pierre Nilsson
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

    @Test //Jonas Nygren
    @DisplayName("get price")
    void testGetPrice(){
        assertEquals(12.2, product.getPrice());
}

    @Test //Jonas Nygren
    @DisplayName("Set price")
    void testSetPrice(){
        product.setPrice(100.2);
        assertEquals(100.2, product.getPrice());
    }

    @Test //Ali Kazem Mahdy
    @DisplayName("check the get title")
    void checkGetTitle() {
        assertNull(product.getTitle());
    }

    @Test //Ali Kazem Mahdy
    @DisplayName("check the set title")
    public void checkSetTitle() {
        product.setTitle("Mens Cotton Jacket");

        assertEquals("Mens Cotton Jacket", product.getTitle());
    }

}