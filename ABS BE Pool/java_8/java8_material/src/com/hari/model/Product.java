package com.hari.model;

/**
 * Simple representation of Product data class.
 *
 * @author Hari Chandra Prasad. Nimmagadda
 */
public final class Product {

    private final int id;
    private final String firstName;
    private final float price;

    /**
     * Product builder.
     *
     */
    public static class Builder {

        private int id;
        private String firstName;
        private float price;

        /**
         * Sets the id value.
         *
         * @param id the id
         * @return the instance
         */
        public Builder id(int id) {
            this.id = id;
            return this;
        }

        /**
         * Sets the first name value.
         *
         * @param firstName the first name
         * @return the instance
         */
        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Sets the price value.
         *
         * @param price the price
         * @return the instance
         */
        public Builder price(float price) {
            this.price = price;
            return this;
        }

        /**
         * Build method, create an instance of the Product.
         *
         * @return an instance of the product
         */
        public Product build() {
            return new Product(this);
        }
    }

        /**
         * Returns an instance of the builder.
         *
         * @return an instance of the builder
         */
        public static Builder builder() {
            return new Builder();
        }

    /**
     * Creates the instance of Product class.
     *
     * @param builder the builder object which contains properties values
     */
        private Product(Builder builder) {
            this.id = builder.id;
            this.firstName = builder.firstName;
            this.price = builder.price;
        }

        //All getter, and NO setter to provide immutability

    /**
     * Get the id to display on click-throughs to the pip from this product.
     *
     * @return the id to display on click-throughs to the pip from this product
     */
        public int getId() {
            return id;
        }

    /**
     * Get the first name to display on click-throughs to the pip from this product.
     *
     * @return the first name to display on click-throughs to the pip from this product
     */
        public String getFirstName() {
            return firstName;
        }

    /**
     * Get the price to display on click-throughs to the pip from this product.
     *
     * @return the price to display on click-throughs to the pip from this product
     */
        public float getPrice() {
            return price;
        }

    /**
     * Override the product values.
     *
     * @return the product details
     */
    @Override
        public String toString() {
            return "Product{" +
                    "id=" + id +
                    ", firstName='" + firstName + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

