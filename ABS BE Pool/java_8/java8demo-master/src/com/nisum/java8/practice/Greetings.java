package com.nisum.java8.practice;

/**
 * Greetings interface which has a method sayHello to greet.
 * 
 * @author Rjosula
 *
 */
public interface Greetings {

	void sayHello(String name);
	
	static void sayHello() {
		System.out.println("Static method");
	}
	
	static void greetings(String input) {
		System.out.println("Hello " + input);
	}

}
