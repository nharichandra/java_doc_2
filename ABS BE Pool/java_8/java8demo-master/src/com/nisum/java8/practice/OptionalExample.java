package com.nisum.java8.practice;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.nisum.java8.practice.enums.Gender;
import com.nisum.java8.practice.model.Employee;
import com.nisum.java8.practice.util.EmployeeComparator;
import com.nisum.java8.practice.util.EmployeeUtil;

/**
 * Optional examples.
 * 
 * @author Rjosula
 *
 */
public class OptionalExample {

    /** Employee util object. */
    private EmployeeUtil employeeUtil = new EmployeeUtil();

    /** Employee list extraction from employee util class. */
    private Optional<List<Employee>> employeesList = employeeUtil.fetchEmployees();

    public static void main(String[] args) {
        OptionalExample optionalExmaple = new OptionalExample();

        optionalExmaple.displayFirstEmployeeFromList();
        optionalExmaple.groupEmployeesByDepartment();
        optionalExmaple.getEmployeesMatchingWithString();
        optionalExmaple.changeStoreIdBasedOnCurrentLength();
        optionalExmaple.getEmployeesNotMatchingDepartment();
        optionalExmaple.arrangeEmployeesByFirstName();
        optionalExmaple.getEmployeeWhoseIdIsHighest();
        optionalExmaple.printAllEmployeeNamesWithPipe();
        optionalExmaple.getNthElementFromEmployee(5);
        optionalExmaple.fetchEmployeeMatchingWithIds();
        optionalExmaple.fetchEmployeesByGender();
        optionalExmaple.printMalesAndFemalesList();

    }

    /**
     * Display first employee from list and also print the full name of the employee
     * (1).
     * 
     */
    private void displayFirstEmployeeFromList() {
        Optional<Employee> firstEmployee = this.employeesList.orElseGet(Collections::emptyList).stream()
                .map(fetchEmployeeFullName).findFirst();
        firstEmployee.ifPresent(employee -> employee.displayEmployee());
        // this.employeesList.map(Collection::stream).orElseGet
    }

    /**
     * Prepares list of employees objects as employee department as a key and value
     * as number of employees present in that departments (2).
     */
    private void groupEmployeesByDepartment() {
        Map<Optional<String>, Long> employeeMap = this.employeesList.orElseGet(Collections::emptyList).stream()
                .collect(Collectors.groupingBy(Employee::getDepartmentName, Collectors.counting()));
        System.out.println(employeeMap);
    }

    /**
     * Gets the employees who are all matching with the string (3).
     */
    private void getEmployeesMatchingWithString() {

        System.out.println("\n --------- Matching employees from various departments---------");
        Map<String, List<Employee>> employeeMap = this.employeesList.orElseGet(Collections::emptyList).stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartmentName().get()));

        List<Employee> empList = employeeMap.values().stream()
                .flatMap(list -> list.stream().collect(Collectors.toList()).stream()).collect(Collectors.toList());

        List<Employee> matchedEmployees = fetchEmployeesMatchingToKey.apply("MOHAN", empList);
        matchedEmployees.forEach(Employee::displayEmployee);
    }

    /**
     * Appending zero's if store id length is less than 4 (4)
     */
    private void changeStoreIdBasedOnCurrentLength() {
        String storeId = "02";
        String store = Optional.ofNullable(storeId).filter(s -> s.length() < 4)
                .map(s -> new StringBuilder(appendZero.apply(4 - s.length())).append(s).toString())
                .orElseGet(() -> storeId);
        System.out.println("store :: " + store);
    }

    /**
     * Gets the list of employees who does not fall under the department (5).
     */
    private void getEmployeesNotMatchingDepartment() {
        String inputDeptName = "computers";
        this.employeesList.orElseGet(Collections::emptyList).stream()
                .filter(emp -> filterEmployee.negate().test(inputDeptName, emp)).collect(Collectors.toList())
                .forEach(emp -> emp.displayEmployee());
    }

    /**
     * Sort the employee in ascending order (6).
     */
    private void arrangeEmployeesByFirstName() {
        System.out.println("Before sort applied");
        this.employeesList.orElseGet(Collections::emptyList).forEach(e -> e.displayEmployee());
        System.out.println("\n After sort applied");
        this.employeesList.orElseGet(Collections::emptyList).sort(new EmployeeComparator());
        this.employeesList.orElseGet(Collections::emptyList).forEach(e -> e.displayEmployee());
    }

    /**
     * Get the employee from employee list whose Id is greater than any other
     * employee (7).
     * 
     */
    private void getEmployeeWhoseIdIsHighest() {
        System.out.println("\n --------- Printing employee whose id is maximum---------");
        Optional<Employee> maxIdEmployee = this.employeesList.orElseGet(Collections::emptyList).stream()
                .max((o1, o2) -> o1.getEmplId().get().compareTo(o2.getEmplId().get()));
        maxIdEmployee.ifPresent(employee -> employee.displayEmployee());
    }

    /**
     * Print all employee full names with pipe symbol as separation (8). Using
     * joining static method on collectors.
     */
    private void printAllEmployeeNamesWithPipe() {
        System.out.println("\n --------- Printing all employees full name " + "separated by pipe symbol  ---------");
        String employeeNames = this.employeesList.orElseGet(Collections::emptyList).stream()
                .collect(Collectors.toList()).stream().map(emp -> fetchFullNameOfEmployee.apply(emp))
                .collect(Collectors.joining("|", "", ""));
        System.out.println(employeeNames);

    }

    /**
     * Fetch nth element from eployee list and print fullname, dept of employee(9).
     * 
     * @param element element index
     */
    private void getNthElementFromEmployee(int element) {

        this.employeesList.orElseGet(Collections::emptyList).stream().collect(Collectors.toList()).get(element)
                .displayEmployee();
    }

    /**
     * Fetching the employee's falling under the id's provided (10).
     */
    private void fetchEmployeeMatchingWithIds() {
        this.employeesList.orElseGet(Collections::emptyList).stream().collect(Collectors.toList()).stream()
                .filter(isEmployeeValid).forEach(e -> e.displayEmployee());
    }

    /**
     * Fetch the number of employee's by their gender (11).
     */
    private void fetchEmployeesByGender() {
        Map<Optional<Gender>, Long> map = this.employeesList.orElseGet(Collections::emptyList).stream()
                .collect(Collectors.toList()).stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(map);
    }

    private void printMalesAndFemalesList() {
        List<Employee> malesList = this.employeesList.orElseGet(Collections::emptyList).stream()
                .collect(Collectors.toList()).stream().filter(emp -> (Gender.MALE.name().equals(emp.getGender())))
                .collect(Collectors.toList()).stream().map(emp -> this.fetchEmployeeFullName.apply(emp))
                .collect(Collectors.toList());

        System.out.println(malesList);
    }

    // ---- Functions created to use for the methods. ------------------
    /**
     * Fetches the full name of every employee.
     */
    private Function<Employee, String> fetchFullNameOfEmployee = (emp) -> {
        return emp.getFirstName().get() + emp.getLastName().get();
    };

    private BiPredicate<String, Employee> filterEmployee = (dept, employee) -> {
        return employee.getDepartmentName().get().equals(dept);
    };

    /**
     * Function to append the zero's based on the length input of string.
     */
    private Function<Integer, String> appendZero = (length) -> {
        return IntStream.rangeClosed(1, 3).mapToObj(value -> "0").limit(length).reduce("", (a, b) -> a + "" + b);
    };

    /**
     * Function to return list of employees who are all matching to the criteria.
     */
    private BiFunction<String, List<Employee>, List<Employee>> fetchEmployeesMatchingToKey = (searchName,
            employeeList) -> {
        List<Employee> filteredList = employeeList.stream().filter(emp -> this.employeeNameFilter.test(emp, searchName))
                .collect(Collectors.toList());
        return filteredList;
    };

    /**
     * Bi predicate filter to search any employee whose first name or last name
     * matching the input string.
     */
    private BiPredicate<Employee, String> employeeNameFilter = (empl, searchName) -> {
        boolean isMathcing = (empl.getFirstName().get().toLowerCase().contains(searchName.toLowerCase())
                || empl.getLastName().get().toLowerCase().contains(searchName.toLowerCase()));
        return isMathcing;
    };

    /**
     * Function to extract the employee fullName.
     * This is an example of identity function input and return output is as same type.
     */
    private Function<Employee, Employee> fetchEmployeeFullName = employeeInput -> {
        String fullName = new StringBuilder(employeeInput.getFirstName().get())
                .append(employeeInput.getLastName().get()).toString();
        employeeInput.setFullName(fullName);
        return employeeInput;
    };

    /**
     * Predicate to check whether the input employee is in provided employee id's.
     */
    private Predicate<Employee> isEmployeeValid = (employee) -> {
        List<Integer> emplIds = Arrays.asList(10101, 303030);

        return emplIds.contains(employee.getEmplId().get());
    };
}
