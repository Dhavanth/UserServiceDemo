package org.example.userservicedemo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class LoginRequest extends BaseModel{
    private String username;
    private String password;
}
