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
@Table(name = "replyComments")
public class ReplyComment extends AbstractEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "commentId", nullable = false)
    @JsonBackReference
    private Comment comment;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;
}
