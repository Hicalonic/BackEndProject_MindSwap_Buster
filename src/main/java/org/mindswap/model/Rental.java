package org.mindswap.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(nullable = false)
    @ManyToOne(targetEntity = Rental.class)
    private Client client ;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate enDDate;
    @Column(nullable = false)
    private List<Movie> moviesRented;


}
