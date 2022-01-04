package com.dobro.fitnessappserver.model.user;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String phoneNumber;

    private String name;

    private String surname;

    private Date birthDate;

//    private List<User> coaches;
//
//    private List<User> trainee;


}