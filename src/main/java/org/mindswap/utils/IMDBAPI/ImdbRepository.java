package org.mindswap.utils.IMDBAPI;

import org.mindswap.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImdbRepository extends JpaRepository<ImdbMovieModel,Long> {

    ImdbMovieModel findByTitle(String title);
}