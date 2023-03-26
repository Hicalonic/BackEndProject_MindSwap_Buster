package org.mindswap.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double price;

    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;

    @ManyToMany(mappedBy = "movies")
    private Set<Rental> rentals = new HashSet<>();

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;

    @Column(nullable = false)
    private boolean available = Boolean.TRUE;




    //avg rating

    //total number of votes

}
