package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.Comment;
import com.ecommerce.WatchStore.Service.CommentService;
import com.ecommerce.WatchStore.dto.CommentDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}")
    public ResponseData<?> getAllComment(@PathVariable Long id,
        @RequestParam(defaultValue = "0", required = false) int page,
        @RequestParam(defaultValue = "10", required = false) int limit
        ) {
        try {
//            List<Comment> listComment = commentService.getAllComment();
            return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
        }
    }

    @PostMapping("")
    public ResponseData<?> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        try {
            Comment newComment = commentService.createComment(commentDTO);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name", newComment.getUser().getLastName());
            userMap.put("gender", newComment.getUser().getGender());
            userMap.put("role", Map.of("name", newComment.getUser().getRole().getName()));

            Map<String, Object> commentMap = new HashMap<>();
            commentMap.put("createdAt", newComment.getCreatedAt());
            commentMap.put("updatedAt", newComment.getUpdatedAt());
            commentMap.put("id", newComment.getId());
            commentMap.put("rating", newComment.getRating());
            commentMap.put("content", newComment.getContent());
            commentMap.put("user", userMap);
            return new ResponseData<>(HttpStatus.OK.value(), "Comment added successfully", commentMap);
        } catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteComment(@PathVariable Long id) {
        try {
            commentService.deleteComment(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Comment deleted successfully");
        } catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long id) {
       try {
           Comment updateComment = commentService.updateComment(id, commentDTO);
           Map<String, Object> userMap = new HashMap<>();
           userMap.put("name", updateComment.getUser().getLastName());
           userMap.put("gender", updateComment.getUser().getGender());
           userMap.put("role", Map.of("name", updateComment.getUser().getRole().getName()));

           Map<String, Object> updateCommentMap = new HashMap<>();
           updateCommentMap.put("createdAt", updateComment.getCreatedAt());
           updateCommentMap.put("updatedAt", updateComment.getUpdatedAt());
           updateCommentMap.put("id", updateComment.getId());
           updateCommentMap.put("rating", updateComment.getRating());
           updateCommentMap.put("content", updateComment.getContent());
           updateCommentMap.put("user", userMap);
           return new ResponseData<>(HttpStatus.OK.value(), "Comment updated successfully", updateCommentMap);
       } catch (EntityNotFoundException e) {
           return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
       } catch (Exception e) {
           return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
       }
    }
}
