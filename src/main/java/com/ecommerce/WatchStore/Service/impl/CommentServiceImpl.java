package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.Model.User;
import com.ecommerce.WatchStore.Model.Watch;
import com.ecommerce.WatchStore.Repository.CommentRepository;
import com.ecommerce.WatchStore.Repository.UserRepository;
import com.ecommerce.WatchStore.Repository.WatchRepository;
import com.ecommerce.WatchStore.Service.CommentService;
import com.ecommerce.WatchStore.dto.CommentDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final WatchRepository watchRepository;
    private final UserRepository userRepository;

    @Override
    public Comment createComment(CommentDTO request) {
        Watch existingProduct = watchRepository.findById(request.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        User existingUser = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Comment newComment = Comment.builder()
                .user(existingUser)
                .watch(existingProduct)
                .content(request.getContent())
                .rating(request.getRating())
                .build();
        return commentRepository.save(newComment);
    }

    @Override
    public Comment getCommentById(long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found"));
    }

    @Override
    public Page<Comment> getAllComment(PageRequest pageRequest) {
        return null;
    }

    @Override
    public Comment updateComment(long id, CommentDTO request) {
        Comment existingComment = getCommentById(id);
        User existingUser = userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User not found"));
        Watch existingProduct = watchRepository.findById(request.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        existingComment.setUser(existingUser);
        existingComment.setWatch(existingProduct);
        existingComment.setRating(request.getRating());
        existingComment.setContent(request.getContent());
        return commentRepository.save(existingComment);
    }

    @Override
    public void deleteComment(long id) {
        Comment deleteComment = getCommentById(id);
        commentRepository.delete(deleteComment);
    }
}
