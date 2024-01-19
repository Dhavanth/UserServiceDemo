package org.example.userservicedemo.repositories;

import org.example.userservicedemo.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Address save(Address address);
}
