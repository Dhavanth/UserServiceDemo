package org.example.userservicedemo.repositories;

import org.example.userservicedemo.models.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameRepository extends JpaRepository<Name, Long> {

    Optional<Name> findByFirstNameAndLastName(String firstName, String lastName);

    Name save(Name name);
}
