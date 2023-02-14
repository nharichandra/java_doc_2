package com.hari.streamAPI;

import com.hari.model.Employee;
import com.hari.model.Product;
import com.hari.util.EmployeeUtil;
import com.hari.util.ProductsUtil;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamApiExample {

    public static void main(String[] args) {

        // creating empty stream
        Stream stream = Stream.empty();
        stream.forEach(System.out::println);

        /* creating stream object from Collections
            1. Collection
            2. List
            3. Set
            4. Map*/
       /*  Creating Stream object from Arrays
             filter
            filtering and iterating collection
            sort the elements based on product id and map it with price
            Sum by using Collectors Methods
            Find Max and Min Product Price
            Converting Product Price List into Set
            Converting Product List into a Map
            Using Method Reference*/

        Collection collection = Arrays.asList("JAVA", "J2EEE", "Spring", "Hibernate");
        Stream<String> collectionStream = collection.stream();
        collectionStream.forEach(System.out::println);

        List list = Arrays.asList("JAVA", "J2EEE", "Spring", "Hibernate");
        Stream<String> listStream = collection.stream();
        listStream.forEach(System.out::println);

        Set set = new HashSet(list);
        Stream<String> setStream = collection.stream();
        setStream.forEach(System.out::println);

        // Creating Stream object from an Arrays
        // Array can also be a source of a Stream
        Stream<String> streamOfArray = Stream.of("a", "b", "c");
        streamOfArray.forEach(System.out::println);

        // creating from existing array or of a part of an array:
        String[] arr = new String[] { "a", "b", "c" };

        Stream<String> streamOfArrayFull = Arrays.stream(arr);
        streamOfArrayFull.forEach(System.out::println);

        Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3);
        streamOfArrayPart.forEach(System.out::println);


        List<Product> productsList = ProductsUtil.getProducts();

        // filter the product price and map with product price
        List<Float> filterProductPriceList = productsList.stream()
                .filter(product -> product.getPrice() > 20000f)
                .map(product -> product.getPrice())
                .collect(Collectors.toList());

        filterProductPriceList.forEach(price -> System.out.println(price));

        System.out.println("\n");

        // filtering and iterating collection
        productsList.stream()
                .filter(product -> product.getPrice() > 25000f)
                .forEach(product -> System.out.println(product.getPrice()));

        // sort the elements based on product id and map it with price
        List<Float> sortProductPriceList = productsList.stream()
                .sorted((o1, o2) -> o1.getId() - o2.getId())
                .map(product -> product.getPrice())
                .collect(Collectors.toList());
        sortProductPriceList.forEach(System.out::println);

        //  Sum by using Collectors Methods
        double summingPrice = productsList.stream()
                .collect(Collectors.summingDouble(price -> price.getPrice()));
        System.out.println(summingPrice);

        //  Find Min and Max Product Price
        Product minProductPrice = productsList.stream()
                .min((o1, o2) -> o1.getPrice() < o2.getPrice() ? 1 : -1)
                .get();
        System.out.println(minProductPrice);

        Product maxProductPrice = productsList.stream()
                .max((o1, o2) -> o1.getPrice() > o2.getPrice() ? 1 : -1)
                .get();
        System.out.println(maxProductPrice);

        // Count of elements in the stream
        long countProduct = productsList.stream()
                .count();
        System.out.println("Count of Products : " + countProduct);

        // findFirst - Returns an Optional describing the first element of this stream, or an empty Optional if the stream is empty.
        Product findFirstProduct = productsList.stream()
                .filter(product -> product.getPrice() > 25000)
                .findFirst().get();
        System.out.println("Find first element of the product stream : " + findFirstProduct);

        //  findAny - Returns an Optional describing some element of the stream, or an empty Optional if the stream is empty.
        Product findAnyProduct = productsList.stream()
                .filter(product -> product.getPrice() > 25000)
                .findFirst().get();
        System.out.println("Find any element of the product stream : " + findAnyProduct);

        // allMatch - Returns whether all elements of this stream match the provided predicate.
        Predicate<Employee> olderThan50 = e -> e.getAge() > 28;
        Predicate<Employee> earningMoreThan40K = e -> e.getSalary() > 40000;
        Predicate<Employee> combinedCondition = olderThan50.and(earningMoreThan40K);

        boolean allMatchEmployee = EmployeeUtil.getEmployees().stream().allMatch(combinedCondition);
        System.out.println(allMatchEmployee);
        System.out.println("allMatch : " + allMatchEmployee);

        // anyMatch - Returns whether any elements of this stream match the provided predicate.
        boolean anyMatchProductList = productsList.stream()
                .anyMatch(product -> product.getPrice() > 20000);
        System.out.println("anyMatch : " + anyMatchProductList);

        /* generate - Returns an infinite sequential unordered stream where each element is generated by the provided Supplier.
            limit - Returns a stream consisting of the elements of this stream, truncated to be no longer than maxSize in length.
        we are creating a stream from generated elements. This will produce a stream of 20 random numbers.
        We have restricted the elements count using limit() function.
        */
        Stream<Integer> generateStream = Stream.generate(() -> (new Random()).nextInt(100));
        generateStream.limit(20).forEach(System.out::println);


        // Converting Product Price List into Set
        Set<Float> convertListToSet = productsList.stream()
                .filter(product -> product.getPrice() > 30000)
                .map(product -> product.getPrice())
                .collect(Collectors.toSet());
        System.out.println(convertListToSet);

        // Converting Product List into a Map
        Map <Integer, String> convertProductListToMap = productsList.stream()
                .collect(Collectors.toMap(p -> p.getId(), p -> p.getFirstName()));
        System.out.println(convertProductListToMap);

        // Using Method Reference
        List<Float> methodReferencePriceList = productsList.stream()
                .filter(product -> product.getPrice() > 30000)
                .map(Product::getPrice)
                .collect(Collectors.toList());
        methodReferencePriceList.forEach(System.out::println);

        // flatMap -
        //creating ArrayList
        List<String> productlist1 = Arrays.asList("Printer", "Mouse", "Keyboard", "Motherboard");
        List<String>  productlist2 = Arrays.asList("Scanner", "Projector", "Light Pen");
        List<String> productlist3 = Arrays.asList("Pen Drive", "Charger", "WIFI Adapter", "Cooling Fan");
        List<String> productlist4 = Arrays.asList("CPU Cabinet", "WebCam", "USB Light", "Microphone", "Power cable");
        List<List<String>> allproducts = new ArrayList<List<String>>();

        //adding elements to the list
        allproducts.add(productlist1);
        allproducts.add(productlist2);
        allproducts.add(productlist3);
        allproducts.add(productlist4);
        //creating a list of all products
        List<String> listOfAllProducts = new ArrayList<String>();
        //for each loop iterates over the list
        for(List<String> pro : allproducts)
        {
            for(String product : pro)
            {
            //adds all products to the list
                listOfAllProducts.add(product);
            }
        }
        System.out.println("List Before Applying mapping and Flattening: \n");

        //prints stream before applying the flatMap() method
        System.out.println(listOfAllProducts);
        System.out.println();
        //creats a stream of elemnts using flatMap()
        List<String> flatMapList = allproducts .stream().flatMap(pList -> pList.stream()).collect(Collectors.toList());
        System.out.println("List After Applying Mapping and Flattening Operation: \n");
        //prints the new stream that we get after applying mapping and flattening
        System.out.println(flatMapList);



    }
}
