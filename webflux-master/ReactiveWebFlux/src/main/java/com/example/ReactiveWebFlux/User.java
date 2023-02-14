package com.example.ReactiveWebFlux;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private LocalDateTime recordDate;
}
