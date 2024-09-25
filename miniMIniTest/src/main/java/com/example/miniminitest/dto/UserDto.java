package com.example.miniminitest.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
    private int id;

    private String loginId;
    private String pwd;
    private String name;
}
