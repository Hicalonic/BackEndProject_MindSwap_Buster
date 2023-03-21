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
@Table(name = "workers")
@SQLDelete(sql = "UPDATE workers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long storeId;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false)
    private boolean deleted = Boolean.FALSE;
}
