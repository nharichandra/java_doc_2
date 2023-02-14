package com.hari.classes;

import com.hari.model.Product;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CollectorsClassExample {

    public static void main(String[] args) {

          /*  1. toCollection
            2. toList
            3. toSet
            4. toMap
            5. Aggregate functions
                1. sum
                2. minus
                3. average
                4.  count*/


        List<Product> productsList = new ArrayList<Product>();
        // Adding Products to the products list
        productsList.add(Product.builder().id(1).firstName("HP Laptop").price(25000f).build());
        productsList.add(Product.builder().id(2).firstName("Dell Laptop").price(30000f).build());
        productsList.add(Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        productsList.add(Product.builder().id(4).firstName("Sony Laptop").price(28000f).build());
        productsList.add(Product.builder().id(5).firstName("Apple Laptop").price(95000f).build());

        Set<Product> productSet = new HashSet<>();
        // Adding Products to the products set
        productSet.add(Product.builder().id(1).firstName("HP Laptop").price(25000f).build());
        productSet.add(Product.builder().id(2).firstName("Dell Laptop").price(30000f).build());
        productSet.add(Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        productSet.add(Product.builder().id(4).firstName("Sony Laptop").price(28000f).build());
        productSet.add(Product.builder().id(5).firstName("Apple Laptop").price(95000f).build());

        Map<Integer, Product> productMap = new HashMap<>();
        // Adding Products to the products map
        productMap.put(1, Product.builder().id(1).firstName("HP Laptop").price(25000f).build());
        productMap.put(2, Product.builder().id(2).firstName("Dell Laptop").price(30000f).build());
        productMap.put(3, Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        productMap.put(4, Product.builder().id(4).firstName("Sony Laptop").price(28000f).build());
        productMap.put(5, Product.builder().id(5).firstName("Apple Laptop").price(95000f).build());



        ArrayList<Float> priceArrayList = productsList.stream()
                                .map(Product::getPrice)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(priceArrayList);

        HashSet<Float> priceHashSet = productsList.stream()
                .map(Product::getPrice)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(priceHashSet);


        List<Float> list = productsList.stream()
                .map(Product::getPrice)     // fetching price
                .collect(Collectors.toList());  //  collecting as list
        System.out.println(list);

        Set<Float> set = productsList.stream()
                .map(Product::getPrice)  // fetching price
                .collect(Collectors.toSet());   //  collecting as set
        System.out.println(set);

        Double summingDouble  = productsList.stream()
                .collect(Collectors.summingDouble(Product::getPrice));
        System.out.println(summingDouble);

        Double averagingDouble = productsList.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println(averagingDouble);

        Long counting = productsList.stream()
                .collect(Collectors.counting());
        System.out.println(counting);

        Optional<Product> minBy = productsList.stream()
                .collect(Collectors.minBy((o1, o2) -> o1.getId() - o2.getId()));
        System.out.println(minBy.get());

        Optional<Product> maxBy = productsList.stream()
                .collect(Collectors.maxBy((o1, o2) -> o1.getId() - o2.getId()));
        System.out.println(maxBy.get());


    }
}
