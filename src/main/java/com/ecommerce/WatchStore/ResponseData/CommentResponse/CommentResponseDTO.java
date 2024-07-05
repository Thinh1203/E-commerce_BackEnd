package com.ecommerce.WatchStore.ResponseData.CommentResponse;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class CommentResponseDTO {
    private Long id;
    private int rating;
    private String content;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    private UserResponseDTO user;
    private List<ReplyCommentResponseDTO> replyComments;
    private int replyCount;
}
