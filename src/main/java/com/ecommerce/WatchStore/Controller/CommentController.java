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

@RestController
@RequestMapping("${api.prefix}/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping("")
    public ResponseData<?> getAllComment() {
        return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
    }

    @PostMapping("")
    public ResponseData<?> createComment(@Valid @RequestBody CommentDTO commentDTO) {
        try {
            Comment newComment = commentService.createComment(commentDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Comment added successfully", newComment);
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
           return new ResponseData<>(HttpStatus.OK.value(), "Comment updated successfully", updateComment);
       } catch (EntityNotFoundException e) {
           return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
       } catch (Exception e) {
           return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
       }
    }
}
