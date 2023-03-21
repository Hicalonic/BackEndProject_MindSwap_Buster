package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
@SQLDelete(sql = "UPDATE invoices SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private Long price;

    @OneToOne(mappedBy = "invoice")
    @JoinColumn(name = "rental_ID")
    private Rental rental;

    @ManyToOne(targetEntity = Worker.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "worker_ID")
    private Worker worker;
}
