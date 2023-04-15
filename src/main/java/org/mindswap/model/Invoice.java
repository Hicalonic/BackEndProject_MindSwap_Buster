package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoices")
@SQLDelete(sql = "UPDATE invoices SET deleted = true WHERE id=?")
@Where(clause = "deleted_invoice=false")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double price;

    @OneToOne(mappedBy = "invoice")
    @JoinColumn(name = "rental_ID")
    private Rental rental;

    @JoinColumn(name = "store_id")
    @ManyToOne(targetEntity = Store.class)
    private Store store;

    @Column(nullable = false)
    private boolean deletedInvoice = Boolean.FALSE;


}
