package org.mindswap.repository;

import org.mindswap.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {

    Optional<Client> findByEmail(String email);
}

