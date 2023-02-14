package com.nisum.java8.practice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.nisum.java8.practice.enums.Gender;
import com.nisum.java8.practice.model.Address;
import com.nisum.java8.practice.model.Employee;

/**
 * Employee utils to generate the employee object.
 * 
 * @author Rjosula
 *
 */
public class EmployeeUtil {

	/**
	 * Fetch employees.
	 * 
	 * @return employee optional object
	 */
	public Optional<List<Employee>> fetchEmployees() {
		
		List<Employee> employeesList = new ArrayList<Employee>();
		
		Address empAddress1 = new Address("Hyderabad, address1", 0);
		Address empAddress2 = new Address("Hyderabad, address2", 0);
		Address empAddress3 = new Address("Hyderabad, address3", 0);
		List<Address> empAddresses = Arrays.asList(empAddress1, empAddress2, empAddress3);
		Employee emp = new Employee(10101, "Naga ", "maheshwar", empAddresses, 9000, "computers",
		        Gender.MALE);
		
		employeesList.add(emp);
		Address emp2Address1 = new Address("Vikarabad, address1", 37468787);
		Address emp2Address2 = new Address("Kondapur, address2", 387468);
		Address emp2Address3 = new Address("Hyderabad, address3", 0);
		List<Address> emp2Addresses = Arrays.asList(emp2Address1, emp2Address2, emp2Address3);
		Employee emp2 = new Employee(202020, "Lakshmi", "K", emp2Addresses, 5000, "chemical",
		        Gender.FEMALE);
		employeesList.add(emp2);
		
		Address emp3Address1 = new Address("Miyapur, address1", 0);
		Address emp3Address2 = new Address("Hitech city, address2", 37473657);
		Address emp3Address3 = new Address("Gachibowli, address3", 0);
		List<Address> emp3Addresses = Arrays.asList(emp3Address1, emp3Address2, emp3Address3);
		Employee emp3 = new Employee(303030, "Nagalakshmi", "K", emp3Addresses, 6000, "civil",
		        Gender.FEMALE);
		employeesList.add(emp3);
		
		Address emp4Address1 = new Address("Anantapur, address1", 0);
		Address emp4Address2 = new Address("Kurnool, address3", 0);
		List<Address> emp4Addresses = Arrays.asList(emp4Address1, emp4Address2, empAddress3);
		Employee emp4 = new Employee(4040404, "Naga Mohan", "M", emp4Addresses, 9000, "computers",
		        Gender.MALE);
		employeesList.add(emp4);
		
		Address emp5Address1 = new Address("Bangalore, address1", 0);
        Address emp5Address2 = new Address("Chennai, address3", 0);
        List<Address> emp5Addresses = Arrays.asList(emp5Address1, emp5Address2);
        Employee emp5 = new Employee(203404, "Rajendran", "T", emp5Addresses, 7000, "civil",
                Gender.MALE);
        employeesList.add(emp5);
        
        Address emp6Address1 = new Address("Delhi, address1", 0);
        Address emp6Address2 = new Address("Pune", 0);
        List<Address> emp6Addresses = Arrays.asList(emp6Address1, emp6Address2);
        Employee emp6 = new Employee(101010, "Rajeshwari", "T", emp6Addresses, 17000, "civil",
                Gender.FEMALE);
        employeesList.add(emp6);
		
		return Optional.of(employeesList);
	}
}
