package com.hari.model;

public class EmployeeDTO {

    private String name;
    private int age;

    public EmployeeDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
