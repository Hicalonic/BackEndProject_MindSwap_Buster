package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
@SQLDelete(sql = "UPDATE rentals SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JoinColumn(name = "client_id")
    @ManyToOne(targetEntity = Client.class)
    private Client client ;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;
    @OneToMany(targetEntity = Movie.class)
    @JoinColumn(name = "moviesRented")
    private List<Movie> moviesRented;
    @OneToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;


}
