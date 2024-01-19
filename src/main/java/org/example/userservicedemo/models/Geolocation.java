package org.example.userservicedemo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Geolocation extends BaseModel{
    private String latitude;
    private String longitude;
}