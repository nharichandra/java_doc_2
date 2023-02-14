package com.hari.classes;

import com.hari.model.Person;
import com.hari.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalClassExample {

    public static void main(String[] args) {
        isPresentOptionalAPI();
        createEmptyOptionalObject();
        createEmptyOptionalObjectWithStaticAPI();
        ifPresentOptionalAPI();
        orElseOptionalAPI();
        orElseOptionalAPI();
        orElseGetOptionalAPI();
        orElseThrowOptionalAPI();
        getOptionalAPI();

        filterOptionalAPI();
        mapOptionalAPI();
        flatMapOptinalAPI();

    }

    private static void filterOptionalAPI() {

        // filter the product price with ifPresent Optional API
        Optional.of(Product.builder().id(1).firstName("HP Laptop").price(25000f).build())
                .filter(product -> product.getPrice() > 20000f)
                        .ifPresent(product -> {
                            System.out.println(product.getId());
                            System.out.println(product.getFirstName());
                            System.out.println(product.getPrice());
                        });

        // filter the product price with ifPresent Optional API, It will not shows the result
        Optional.of(Product.builder().id(1).firstName("HP Laptop").price(25000f).build())
                .filter(product -> product.getPrice() > 25000f)
                .ifPresent(product -> {
                    System.out.println(product.getId());
                    System.out.println(product.getFirstName());
                    System.out.println(product.getPrice());
                });

        // filter the product price with orElse Optional API
        Product result = Optional.of(Product.builder().id(1).firstName("HP Laptop").price(25000f).build())
                .filter(product -> product.getPrice() > 25000f)
                .orElse(Product.builder().id(2).firstName("Dell Laptop").price(30000f).build());
        System.out.println(result.getId());
        System.out.println(result.getFirstName());
        System.out.println(result.getPrice());

        // filter the product price with orElseGet Optional API
        Product result2 = Optional.of(Product.builder().id(1).firstName("HP Laptop").price(25000f).build())
                .filter(product -> product.getPrice() > 25000f)
                .orElseGet(() -> Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        System.out.println(result2.getId());
        System.out.println(result2.getFirstName());
        System.out.println(result2.getPrice());

    }

    // Get the size of the list from the list of products.
    private static void mapOptionalAPI() {

        List<Product> productsList = new ArrayList<Product>();

        // Adding Products to the products list
        productsList.add(Product.builder().id(1).firstName("HP Laptop").price(25000f).build());
        productsList.add(Product.builder().id(2).firstName("Dell Laptop").price(30000f).build());
        productsList.add(Product.builder().id(3).firstName("Lenovo Laptop").price(28000f).build());
        productsList.add(Product.builder().id(4).firstName("Sony Laptop").price(28000f).build());
        productsList.add(Product.builder().id(5).firstName("Apple Laptop").price(95000f).build());

        Optional<List<Product>> optional = Optional.of(productsList);
        System.out.println(optional.map(List::size).orElse(0));
    }

    /**
     * flatMap() method as an alternative for transforming values.
     * The difference is that map transforms values only when they are unwrapped whereas
     * flatMap takes a wrapped value and unwraps it before transforming it.
     *
     */
    private static void flatMapOptinalAPI() {

        Person person = new Person("Hari", "Secret", 36);
        Optional<Person> personOptional = Optional.of(person);

        // With map
        Optional<Optional<String>> nameOptionalWrapper = personOptional.map(Person::getName);
        Optional<String> nameOptional = nameOptionalWrapper.orElseThrow(IllegalArgumentException::new);
        String name = nameOptional.orElse("");
        System.out.println(name);

        // With flatMap
        String password = personOptional
                        .flatMap(Person::getPassword)
                        .orElse("");
        System.out.println(password);
    }

    // Returns an Optional with the specified present non-null value.
    private static void isPresentOptionalAPI() {
        Optional<String> opt = Optional.of("Ramesh");
        System.out.println(opt.isPresent());
    }

    // Returns an Optional with the specified present non-null value.
    private static void createEmptyOptionalObject() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());

        // Optional object with the static of API:
        String name = "Ramesh";
        Optional.of(name);
    }

    private static void createEmptyOptionalObjectWithStaticAPI() {
        String name = "baeldung";
        Optional.of(name);
    }

    // If a value is present, invoke the specified consumer with the value, otherwise do
    // nothing.
    private static void ifPresentOptionalAPI() {
        // The ifPresent API enables us to run some code on the wrapped value if it is
        // found to be non-null.
        // Before Optional, we would do something like this:
        String name = "Ramesh";
        if (name != null) {
            System.out.println(name.length());
        }

        Optional<String> opt = Optional.of("Ramesh");
        opt.ifPresent(str -> System.out.println(str.length()));
    }

    // If a value is present, invoke the specified consumer with the value, otherwise do
    // nothing.
    private static void orElseOptionalAPI() {
        // With orElse, the wrapped value is returned if it is present and the argument
        // given to
        // orElse is returned if the wrapped value is absent
        String nullName = null;

        // If a value is present, invoke the specified consumer with the value, otherwise
        // do nothing.
        //
        String name = Optional.ofNullable(nullName).orElse("Ramesh");
        System.out.println(name);
    }

    // Return the value if present, otherwise invoke other and return the result of that
    // invocation.
    private static void orElseGetOptionalAPI() {
        String nullName = null;
        String name = Optional.ofNullable(nullName).orElseGet(() -> "Ramesh");
        System.out.println(name);
    }

    // Return the contained value, if present, otherwise throw an exception to be created
    // by the provided supplier.
    private static void orElseThrowOptionalAPI() {

        // This will throw exception
        String nullName = null;
        String name = Optional.ofNullable(nullName)
                .orElseThrow(IllegalArgumentException::new);
        System.out.println(name);
    }

    // If a value is present in this Optional, returns the value, otherwise throws NoSuchElementException
    private static void getOptionalAPI() {
        Optional<String> opt = Optional.of("Ramesh");
        String name = opt.get();
        System.out.println(name);
    }

}
