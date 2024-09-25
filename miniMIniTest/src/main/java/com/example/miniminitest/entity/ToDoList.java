// ToDoList.java
package com.example.miniminitest.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "to_do_list")
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer num;
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ToDoList(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }
}
