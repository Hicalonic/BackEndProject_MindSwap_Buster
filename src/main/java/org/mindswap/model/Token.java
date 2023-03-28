package org.mindswap.model;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {

    @Id
    @GeneratedValue
    public Long id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;
    public boolean expired;


    public String email;

    @Enumerated(EnumType.STRING)
    public Role role;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}