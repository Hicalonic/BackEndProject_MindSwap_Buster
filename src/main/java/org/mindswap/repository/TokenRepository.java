package org.mindswap.repository;

import org.mindswap.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
    select t from Token t inner join Client c\s
    on t.client.id = c.id\s 
    where c.id = :id and (t.expired = false or t.revoked = false)\s
""")
    List<Token> findAllValidTokenByEmail(String email);

    Optional<Token> findByToken(String token);
}
