package com.hari.util;

import com.hari.model.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeUtil {

    public static List<Employee> getEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList.add(Employee.builder().firstName("Hari").lastName("Nimmagadda").age(35).phone("1234567890").address("Hyderabad").salary(40000).build());
        employeeList.add(Employee.builder().firstName("Priya").lastName("Battina").age(30).phone("2345678901").address("Hyderabad").salary(50000).build());
        employeeList.add(Employee.builder().firstName("BrindaSri").lastName("Nimmagadda").age(6).phone("3456789012").address("Hyderabad").salary(60000).build());

        return employeeList;
    }
}
