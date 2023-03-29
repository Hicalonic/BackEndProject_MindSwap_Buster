package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stores")
@SQLDelete(sql = "UPDATE stores SET deleted = true WHERE id=?")
@Where(clause = "deleted_store=false")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Long managerId;

    @Column(nullable = false)
    private boolean deletedStore = Boolean.FALSE;

    @OneToMany(targetEntity = User.class)
    @JoinColumn(name = "worker_id")
    private List<User> workers;

/*    @Column(nullable = false)
    @OneToMany(mappedBy = "store")
    private List<User> employeesList;*/

}
