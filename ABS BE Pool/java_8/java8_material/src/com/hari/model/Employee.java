package com.hari.model;

/**
 * Simple representation of Employee data class.
 *
 * @author Hari Chandra Prasad. Nimmagadda
 */
public final class Employee {
    //All final attributes
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String address;
    private final float salary;

    /**
     * Person builder.
     */
    public static class Builder
    {
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;
        private float salary;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder age(int age)
        {
            this.age = age;
            return this;
        }
        public Builder phone(String phone)
        {
            this.phone = phone;
            return this;
        }
        public Builder address(String address)
        {
            this.address = address;
            return this;
        }

        public Builder salary(float salary)
        {
            this.salary = salary;
            return this;
        }

        //Return the finally constructed Person object
        public Employee build()
        {
            return new Employee(this);
        }

    }

    /**
     * Returns an instance of the builder.
     *
     * @return an instance of the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    private Employee(Builder builder)
    {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
        this.salary = builder.salary;
    }

    //All getter, and NO setter to provide immutability
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getAge() {
        return age;
    }
    public String getPhone() {
        return phone;
    }
    public String getAddress() {
        return address;
    }
    public float getSalary() { return salary; }

    @Override
    public String toString() {
        return "Person: "+this.firstName+", "+this.lastName+", "+this.age+", "+this.phone+", "+this.address+", "+this.salary;
    }


}
