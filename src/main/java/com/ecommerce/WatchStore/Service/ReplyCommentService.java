package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.Model.ReplyComment;
import com.ecommerce.WatchStore.dto.CommentDTO;
import com.ecommerce.WatchStore.dto.ReplyCommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ReplyCommentService {
    ReplyComment createReplyComment (ReplyCommentDTO replyCommentDTO);

//    Comment getCommentById(long id);

//    Page<Comment> getAllComment(PageRequest pageRequest);
//
//    Comment updateComment(long id, CommentDTO commentDTO);
//
//    void deleteComment(long id);
}
