package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.Model.ReplyComment;
import com.ecommerce.WatchStore.dto.ReplyCommentDTO;

import java.util.List;

public interface ReplyCommentService {
    ReplyComment createReplyComment (ReplyCommentDTO replyCommentDTO);

    List<ReplyComment> getAllReplyComment(long id);

    ReplyComment updateComment(long id, ReplyCommentDTO replyCommentDTO);

    void deleteComment(long id);
}
