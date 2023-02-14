/**
 * 
 */
package com.nisum.java8.practice.enums;

/**
 * Gender enum to contain the male and female values.
 * 
 * @author Rjosula
 *
 */
public enum Gender {

    MALE, FEMALE;

    public static void main(String[] args) {
        Gender s = Gender.MALE;
        System.out.println(s.name().toLowerCase());
    }
}
