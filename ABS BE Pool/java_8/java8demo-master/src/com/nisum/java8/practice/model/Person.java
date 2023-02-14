package com.nisum.java8.practice.model;

import java.util.List;

/**
 * Person class.
 * 
 * @author Rjosula
 *
 */
public class Person {

	private int age;
	private String name;
	private String sex;
	private long salary;
	private long phoneNumber;
	private List<Address> addresses;
	private Experience experience;
	
	
	/**
	 * Person constructor.
	 * 
	 * @param age age
	 * @param name name
	 * @param sex person sex
	 */
	public Person(int age, String name, String sex) {
		this.age = age;
		this.name = name;
		this.sex = sex;
	}
	
	/**
	 * Person constructor.
	 * 
	 * @param age age
	 * @param name name
	 * @param sex person sex
	 * @param salary person salary
	 */
	public Person(int age, String name, String sex, long salary) {
		this.age = age;
		this.name = name;
		this.sex = sex;
		this.salary = salary;
	}
	
	/**
	 * Gets the person age.
	 * 
	 * @return returns person age
	 */
	public int getAge() {
		return age;
	}
	
	/**
	 * Sets the persons age.
	 * 
	 * @param age - person age
	 */
	public void setAge(int age) {
		this.age = age;
	}
	
	/**
	 * Gets the person name.
	 * 
	 * @return returns person name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the persons name.
	 * 
	 * @param age - person name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the person sex.
	 * 
	 * @return returns person sex
	 */
	public String getSex() {
		return sex;
	}
	
	/**
	 * Sets the persons sex.
	 * 
	 * @param age - person sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	/**
	 * Return the person name and age as string.
	 * 
	 * @param person person object
	 * @return person properties in terms of string
	 */
	public String displayPerson() {
		return "PersonName = " + this.getName() + " Age is " + this.getAge()
		+ " and Salary is " + this.getSalary();
	}
}
