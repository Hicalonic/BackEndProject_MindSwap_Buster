package org.mindswap.repository;

import org.mindswap.model.ClientToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientTokenRepository extends JpaRepository<ClientToken, Integer> {

    @Query(value = """
      select t from Token t inner join Client c\s
      on t.client.id = c.id\s
      where w.id = :id and (c.expired = false or c.revoked = false)\s
      """)
    List<ClientToken> findAllValidTokenById(Long id);

    Optional<ClientToken> findByToken(String token);
}
