package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "socialAccounts")
public class SocialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "providerId", nullable = false)
    private String providerId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
}
