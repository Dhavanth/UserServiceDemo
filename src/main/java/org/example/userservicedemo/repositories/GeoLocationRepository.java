package org.example.userservicedemo.repositories;

import org.example.userservicedemo.models.Geolocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GeoLocationRepository extends JpaRepository<Geolocation, Long> {

    Optional<Geolocation> findByLatitudeAndLongitude(String latitude, String longitude);
    Geolocation save(Geolocation geolocation);
}
