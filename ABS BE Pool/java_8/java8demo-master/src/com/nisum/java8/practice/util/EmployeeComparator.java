package com.nisum.java8.practice.util;

import java.util.Comparator;

import com.nisum.java8.practice.model.Employee;

/**
 * Employee comparator uses to arrange employees in a specific order.
 * 
 * @author Rjosula
 *
 */
public class EmployeeComparator implements Comparator<Employee>{

    /**
     * Compare method to arrange objects.
     */
    @Override
    public int compare(Employee o1, Employee o2) {
        return o1.getFirstName().get().compareTo(o2.getFirstName().get());
    }
}
