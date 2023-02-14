package com.hari.functionalinterface;

import com.hari.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * It represents an operation that accepts a single argument and returns no result.
 * The consumer interface contains exactly one abstract method accept(T arg0).
 * Hence we can apply lambda expression to it.
 * It is an Functional interface.
 *
 * @author Hari Chandra Prasad. Nimmagadda
 */
public class ConsumerExample {

    public static void main(String[] args) {

        // 1) Traditional Code approach
        Consumer<Employee> consumer = new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println(employee.getFirstName());
                System.out.println(employee.getAge());
            }
        };
        consumer.accept(Employee.builder().firstName("Hari").age(35).build());

        // 2) For instance, let�s greet everybody in a list of names by printing the greeting
        // in the console. The lambda passed to the List.forEach method implements the
        // Consumer functional interface:
        List<Employee> listOfEmployee = new ArrayList<>();

        listOfEmployee.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        listOfEmployee.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        listOfEmployee.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());

        listOfEmployee.forEach((employee) -> {
            System.out.println(" Employee name : " + employee.getFirstName());
            System.out.println(" Employee age : " + employee.getAge());
        });

        //  3) Using Consumer functional interface
        Consumer<Employee> employeeConsumer = employee -> {
            System.out.println(employee.getFirstName());
            System.out.println(employee.getAge());
        };
        employeeConsumer.accept(Employee.builder().firstName("Hari").age(35).build());

    }
}
