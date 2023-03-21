package org.mindswap.repository;

import org.mindswap.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Worker,Long> {
}
