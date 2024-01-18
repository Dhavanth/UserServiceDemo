package org.example.userservicedemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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

    @OneToOne
    private Geolocation geolocation;
}
