/**
 * 
 */
package com.nisum.java8.practice.model;

import java.util.List;
import java.util.Optional;

import com.nisum.java8.practice.enums.Gender;

/**
 * Employee class.
 * 
 * @author Rjosula
 *
 */
public class Employee {

    private String firstName;
	
	private String lastName;
	
	private String fullName;
	
	private List<Address>  addresses;
	
	private Long salary;
	
	private String departmentName;
	
	private Integer emplId;
	
	private Gender gender;
	
	/**
	 * Parameterised constructor.
	 * 
	 * @param firstName first name
	 * @param lastName last name
	 * @param address addresses
	 * @param salary salary
	 * @param departmentName department name
	 * @param gender gender(male or female)
	 */
	public Employee(int emplId, String firstName, String lastName,
			List<Address> address, long salary, String departmentName,
			Gender gender) {
		
	    this.emplId = emplId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addresses = address;
		this.salary = salary;
		this.departmentName = departmentName;
		this.gender = gender;
	}

	public Optional<String> getFirstName() {
		return Optional.ofNullable(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Optional<String> getLastName() {
		return Optional.ofNullable(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Optional<List<Address>> getAddresses() {
		return Optional.ofNullable(addresses);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Optional<Long> getSalary() {
		return Optional.ofNullable(salary);
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Optional<String> getFullName() {
		return Optional.ofNullable(fullName);
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public Optional<String> getDepartmentName() {
		return Optional.ofNullable(departmentName);
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Optional<Integer> getEmplId() {
        return Optional.ofNullable(emplId);
    }

    public void setEmplId(Integer emplId) {
        this.emplId = emplId;
    }

    public Optional<Gender> getGender() {
        return Optional.ofNullable(gender);
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
	 * Displays employee information.
	 * 
	 * @return string which displays employee full name and salary 
	 */
	public void displayEmployee() {
	    System.out.println(new StringBuilder().append("Employee Id : ").append(this.emplId)
        .append("Employee Name:: ").append(this.firstName).append(this.lastName)
        .append(" and Salary :: ").append(this.salary)
        .append(" Dept ").append(this.departmentName)
        .toString());
	}
}
