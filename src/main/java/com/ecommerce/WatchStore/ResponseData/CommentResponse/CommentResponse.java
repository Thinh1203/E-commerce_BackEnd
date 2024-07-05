package com.ecommerce.WatchStore.ResponseData.CommentResponse;

import com.ecommerce.WatchStore.Model.Comment;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class CommentResponse {
    private List<CommentResponseDTO> comments;
    private int totalPages;
    private int currentPage;
}
