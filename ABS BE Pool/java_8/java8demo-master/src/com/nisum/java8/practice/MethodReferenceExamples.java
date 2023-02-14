package com.nisum.java8.practice;

import java.util.Arrays;
import java.util.function.BiFunction;

/**
 * Method reference example with operator :: on class object.
 * 
 * @author Rjosula
 *
 */
public class MethodReferenceExamples implements MyInterface {

	public static void main(String[] args) {
		
		MethodReferenceExamples instanceMethod = new MethodReferenceExamples();
		MyInterface interfaceRef = instanceMethod::display;
		interfaceRef.display();
		
		/** Method reference to static methods. */
		BiFunction<Integer, Integer, Integer> product = Multiplication::multiply;
		
		BiFunction<Integer, Integer, Integer> product1 = (a, b) -> {
			return Multiplication.multiply(a, b);
		};
		int pr = product.apply(11, 5); 
		
		System.out.println("Product of given number is: " + pr);
		
		String[] stringArray = { "Steve", "Rick", "Aditya", "Raghu", "Lucy", "Sansa", "Jon"};
		/* Method reference to an instance method of an arbitrary 
		 * object of a particular type
		 */
		Arrays.sort(stringArray, String::compareToIgnoreCase);
		
		Arrays.asList(stringArray).forEach(System.out :: println);
		//Arrays.asList(stringArray).forEach(str -> System.out.println(str));
		
		LambdaExample.sumOf2.apply(30, 40);
	}

	@Override
	public void display() {
		System.out.println("Display interface method");

	}
}
