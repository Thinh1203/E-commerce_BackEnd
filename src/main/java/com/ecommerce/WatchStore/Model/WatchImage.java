package com.ecommerce.WatchStore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productImages")
public class WatchImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "imageUrl",nullable = false, length = 300)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "watchId", nullable = false)
    @JsonBackReference
    private Watch watch;
}
