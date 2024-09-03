package com.example.security.users;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersDto {
    private String id;
    private String pwd;
    private String type;
}
