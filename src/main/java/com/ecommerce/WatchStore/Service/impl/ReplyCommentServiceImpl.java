package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.Model.ReplyComment;
import com.ecommerce.WatchStore.Model.User;
import com.ecommerce.WatchStore.Repository.CommentRepository;
import com.ecommerce.WatchStore.Repository.ReplyCommentRepository;
import com.ecommerce.WatchStore.Repository.UserRepository;
import com.ecommerce.WatchStore.Service.ReplyCommentService;
import com.ecommerce.WatchStore.dto.ReplyCommentDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReplyCommentServiceImpl implements ReplyCommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ReplyCommentRepository replyCommentRepository;

    @Override
    public ReplyComment createReplyComment(ReplyCommentDTO request) {
        User existingUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Comment existingComment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
        ReplyComment newComment = ReplyComment.builder()
                .comment(existingComment)
                .user(existingUser)
                .content(request.getContent())
                .build();
        return replyCommentRepository.save(newComment);
    }
}
