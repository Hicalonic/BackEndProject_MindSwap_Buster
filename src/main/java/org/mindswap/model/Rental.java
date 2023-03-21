package org.mindswap.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "rentals")
@SQLDelete(sql = "UPDATE rentals SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Rental {
}
