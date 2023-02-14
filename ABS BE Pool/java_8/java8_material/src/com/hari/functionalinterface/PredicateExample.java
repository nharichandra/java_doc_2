package com.hari.functionalinterface;

import com.hari.model.Employee;

import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {

        // Traditional Code approch, Without lamda expression
        Predicate<Employee> predicate = new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() > 6;
            }
        };
        System.out.println(predicate.test(Employee.builder().firstName("Hari").age(35).build()));
        System.out.println(predicate.test(Employee.builder().firstName("Priya").age(30).build()));
        System.out.println(predicate.test(Employee.builder().firstName("BrindaSri").age(6).build()));

        System.out.println("\n");

        // With lamda Expression
        Predicate<Employee> withLamda = (employee -> employee.getAge() > 30);
        System.out.println(withLamda.test(Employee.builder().firstName("Hari").age(35).build()));
        System.out.println(withLamda.test(Employee.builder().firstName("Priya").age(30).build()));
        System.out.println(withLamda.test(Employee.builder().firstName("BrindaSri").age(6).build()));

    }
}
