package com.example.webclient.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("profiles")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Profile {
    @Id
    private String id;
    private String name;
    private int experience;
    private String domain;
    private String designation;
}
