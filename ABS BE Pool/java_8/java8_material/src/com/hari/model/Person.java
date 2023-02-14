package com.hari.model;

import java.util.Optional;

/**
 * Simple representation of Person data class.
 *
 * @author Hari Chandra Prasad. Nimmagadda
 */
public class Person {

    private String name;
    private String password;
    private int age;

    public Person(String name, String password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
