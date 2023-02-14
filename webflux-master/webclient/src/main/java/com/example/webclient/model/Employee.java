package com.example.webclient.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection ="EMPLOYEE")
public class Employee {
    @Id
    private String id;
    private String name;
    private Long Salary;

}