package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "tokenType")
    private String tokenType;

    @Column(name = "expirationDate")
    private LocalDateTime expirationDate;

    private boolean revoked;
    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
