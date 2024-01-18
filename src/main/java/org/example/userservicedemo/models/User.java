package org.example.userservicedemo.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String email;
    private String username;
    private String password;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Name name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address address;

    private String phoneNumber;

}
