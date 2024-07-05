package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.ResponseData.CommentResponse.CommentResponseDTO;
import com.ecommerce.WatchStore.dto.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CommentService {
    Comment createComment (CommentDTO commentDTO);

    Comment getCommentById(long id);

    Page<CommentResponseDTO> getAllComment(PageRequest pageRequest);

    Comment updateComment(long id, CommentDTO commentDTO);

    void deleteComment(long id);
}
