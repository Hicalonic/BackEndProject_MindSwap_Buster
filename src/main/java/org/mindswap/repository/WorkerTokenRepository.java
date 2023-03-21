package org.mindswap.repository;

import org.mindswap.model.ClientToken;
import org.mindswap.model.WorkerToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkerTokenRepository extends JpaRepository<WorkerToken, Integer> {
    @Query(value = """
      select t from Token t inner join Worker w\s
      on t.client.id = w.id\s
      where w.id = :id and (w.expired = false or w.revoked = false)\s
      """)
    List<ClientToken> findAllValidTokenById(Long id);

    Optional<ClientToken> findByToken(String token);
}
