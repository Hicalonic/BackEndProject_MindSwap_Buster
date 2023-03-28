package org.mindswap.utils.IMDBAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ImdbRepository extends JpaRepository<ImdbMovieModel,Long> {
}