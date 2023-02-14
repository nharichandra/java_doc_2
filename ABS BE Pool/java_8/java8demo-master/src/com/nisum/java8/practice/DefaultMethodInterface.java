package com.nisum.java8.practice;

/**
 * Default methods in interfaces to utilise wherever those methods are required not required to implement in all classes.
 * 
 * @author Rjosula
 *
 */
public interface DefaultMethodInterface {

	void display(String str);
	
	
	
	
	default void newMethod() {  
        System.out.println("Newly added default method");  
    } 
	
	default void newMethod2() {  
        System.out.println("Newly added default method");  
    } 
}
