package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date deliveryDate;

    @Column(nullable = false)
    private Boolean isDelivered;

    @Column(nullable = false)
    private Long rentalPrice;

    @ManyToMany(mappedBy = "rental")
    private List<Movie> movies;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    private Client client;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;


}
