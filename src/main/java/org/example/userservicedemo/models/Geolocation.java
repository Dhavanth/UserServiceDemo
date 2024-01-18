package org.example.userservicedemo.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Geolocation extends BaseModel{
    private String latitude;
    private String longitude;
}
