package com.hari.lamda;

import com.hari.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorFunctionalInterface {

    // Comparator and Runnable interfaces provides one abstract method i.e
    // Comparator functional interface provides compare() abstract method.
    // Runnable functional interface provides run() abstract method.

    // 1. Comparator

    public static void main(String[] args) {

        List<Employee> listOfEmployees = new ArrayList<>();

        listOfEmployees.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        listOfEmployees.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        listOfEmployees.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        employeeList.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        employeeList.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());

        //  Without lamda expression
        //  Sort list by age
        Comparator<Employee> comparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        Collections.sort(listOfEmployees, comparator);

        for(Employee employee : listOfEmployees) {
            System.out.println("First Name : " + employee.getFirstName());
            System.out.println("Last Name : " + employee.getLastName());
            System.out.println("Age : " + employee.getAge());
            System.out.println("Phone : " + employee.getPhone());
            System.out.println("Address : " + employee.getAddress());
        }

        // With lamda expression.
        // Sort list by age
        Collections.sort(employeeList, (o1, o2) ->
                o1.getAge() - o2.getAge());

        // Use forEach method added in java 8
        employeeList.forEach(employee -> {
            System.out.println("First Name : " + employee.getFirstName());
            System.out.println("Last Name : " + employee.getLastName());
            System.out.println("Age : " + employee.getAge());
            System.out.println("Phone : " + employee.getPhone());
            System.out.println("Address : " + employee.getAddress());
        });
    }
}
