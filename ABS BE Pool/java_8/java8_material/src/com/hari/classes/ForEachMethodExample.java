package com.hari.classes;


import com.hari.model.Employee;
import com.hari.model.EmployeeDTO;
import com.hari.model.EmployeeEntity;

import java.util.*;

public class ForEachMethodExample {

    public static void main(String[] args) {
       /* 1. Arrays
            2. Collection
            3. List
            4. Set
            5. Map*/

        // 2. Using Collection interface - iterate the elements from the arrays.
        Collection collection = Arrays.asList("Hari", "Chandra", "Prasad");
        collection.forEach(o -> {
            System.out.println(o);
        });

        // 3. List interface - iterate the elements from the employee list
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        employeeList.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        employeeList.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());
        //  With lamda expression
        employeeList.forEach(employee -> System.out.println(employee.getFirstName()));
        // With Method reference
        employeeList.forEach(System.out::println);

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        EmployeeDTO employeeDTO = new EmployeeDTO("Hari", 36);
        EmployeeDTO employeeDTO1 = new EmployeeDTO("Priyanka", 30);
        EmployeeDTO employeeDTO2 = new EmployeeDTO("BrindaSri", 6);
        employeeDTOList.add(employeeDTO);
        employeeDTOList.add(employeeDTO1);
        employeeDTOList.add(employeeDTO2);

        List<EmployeeEntity> employeeEntityList = new ArrayList<>();
        employeeDTOList.forEach( dto -> {
            employeeEntityList.add(new EmployeeEntity(dto.getName(), dto.getAge()));
        });
        employeeEntityList.forEach(employeeEntity -> {
                    System.out.println(employeeEntity.getName());
                    System.out.println(employeeEntity.getAge());
                }
        );

        // 4. Set interface - iterate the elements from the employee set
        Set<Employee> employeeSet = new HashSet<>();
        employeeSet.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        employeeSet.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        employeeSet.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());
        // With lamda expression
        employeeSet.forEach(employee -> System.out.println(employee.getLastName()));
        // With method reference
        employeeSet.forEach(System.out::println);

        // 5. Map interface - iterate the elements from the employee map
        Map<Integer, Employee> employeeMap = new HashMap<>();
        employeeMap.put(1, Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").build());
        employeeMap.put(2, Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").build());
        employeeMap.put(3, Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").build());
        // With lamda expression
        employeeMap.forEach((integer, employee) -> {
            System.out.println(integer);
            System.out.println(employee.getAddress());
        });


    }

}
