package com.ecommerce.WatchStore.Model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "watchId", nullable = false)
    private Watch watch;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
