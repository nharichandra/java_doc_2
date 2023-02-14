package com.nisum.java8.practice;

/**
 * 
 * @author Rjosula
 *
 */
public class MethodInterfaceExample implements DefaultMethodInterface {

	@Override
	public void display(String str) {
		System.out.println("String is: " + str);
	}
	
	public static void main(String[] args) {
		MethodInterfaceExample defaultInterfaceExample = new MethodInterfaceExample();
		
		defaultInterfaceExample.display("Hello World");
		defaultInterfaceExample.newMethod();
	}

}
