package com.nisum.java8.practice.model;

import java.util.Optional;

/**
 * Address class containing address info and contact info.
 * 
 * @author Rjosula
 *
 */
public class Address {

	private Optional<String> addressText;
	
	private Optional<Long> contactNumber;
	
	/**
	 * Parameterised constructor
	 * @param address
	 * @param contactNumber
	 */
	public Address(String address, long contactNumber) {
		this.addressText = Optional.ofNullable(address);
		this.contactNumber = Optional.ofNullable(contactNumber);
	}

	public Optional<String> getAddressText() {
		return addressText;
	}

	public void setAddressText(Optional<String> addressText) {
		this.addressText = addressText;
	}

	public Optional<Long> getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Optional<Long> contactNumber) {
		this.contactNumber = contactNumber;
	}
	
}
