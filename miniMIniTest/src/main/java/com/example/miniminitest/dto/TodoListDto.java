package com.example.miniminitest.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
public class TodoListDto {
    private int num;
    private String title;
    private String content;
}
