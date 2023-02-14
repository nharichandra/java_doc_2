package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("profiles")
@Setter
@Getter
public class Profile {
    @Id
    private String id;
    private String name;
    private int experience;
    private String domain;
    private String designation;
}
