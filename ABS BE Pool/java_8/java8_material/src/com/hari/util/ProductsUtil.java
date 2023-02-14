package com.hari.util;

import com.hari.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductsUtil {

    public static List<Product> getProducts() {

        List<Product> productsList = new ArrayList<Product>();

        // Adding Products to the products list
        productsList.add(Product.builder().id(2).firstName("HP Laptop").price(25000f).build());
        productsList.add(Product.builder().id(1).firstName("Dell Laptop").price(30000f).build());
        productsList.add(Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        productsList.add(Product.builder().id(5).firstName("Sony Laptop").price(28000f).build());
        productsList.add(Product.builder().id(4).firstName("Apple Laptop").price(95000f).build());

        return productsList;
    }

    public static void main(String[] args) {
        ProductsUtil util = new ProductsUtil();
        List<Product> products = util.getProducts();

        //
        products.stream().forEach(product -> {
            System.out.println(product.getId());
            System.out.println(product.getFirstName());
            System.out.println(product.getPrice());
        });

        //products.stream().forEach(System.out::println);
    }
}
