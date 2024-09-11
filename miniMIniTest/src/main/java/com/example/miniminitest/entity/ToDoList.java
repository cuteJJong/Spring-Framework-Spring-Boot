package com.example.miniminitest.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ToDoList {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String title;
    private String content;

    public ToDoList(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
