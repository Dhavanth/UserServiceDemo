package org.example.userservicedemo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Address extends BaseModel{
    private Long number;
    private String street;
    private String city;
    private String zipCode;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Geolocation geolocation;
}
