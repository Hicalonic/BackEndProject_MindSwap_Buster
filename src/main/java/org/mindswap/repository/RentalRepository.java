package org.mindswap.repository;

import org.mindswap.model.Rental;
import org.mindswap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    List<Rental> findByUser(User user);
}
