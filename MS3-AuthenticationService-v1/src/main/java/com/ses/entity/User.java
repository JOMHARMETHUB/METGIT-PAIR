package com.ses.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Java objects that are persisted to the database.
// An entity is created to persist the data to a database using the Java Persistence API.
// The implementation is Hibernate by default for spring applications.
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

//    @Column(name = "USER_NAME")
    private String userName;

//    @Column(name = "password")
    private String password;

//    @Column(name = "email")
    private String email;
}
