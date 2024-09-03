package com.example.security.users;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {
    @Id
    private String id;
    private String pwd;
    private String type;
}
