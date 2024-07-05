package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.ReplyComment;
import com.ecommerce.WatchStore.Service.ReplyCommentService;
import com.ecommerce.WatchStore.dto.ReplyCommentDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/replyComment")
@RequiredArgsConstructor
public class ReplyCommentController {

    private final ReplyCommentService replyCommentService;

    @GetMapping("/{id}")
    public ResponseData<?> getAllReplyComment(@PathVariable Long id) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(), "List Comment: ", replyCommentService.getAllReplyComment(id));
        } catch (Exception e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseData<?> createReplyComment( @Valid @RequestBody ReplyCommentDTO replyCommentDTO) {
        try {
            ReplyComment newComment = replyCommentService.createReplyComment(replyCommentDTO);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("name", newComment.getUser().getLastName());
            userMap.put("gender", newComment.getUser().getGender());
            userMap.put("role", Map.of("name", newComment.getUser().getRole().getName()));

            Map<String, Object> newCommentMap = new HashMap<>();
            newCommentMap.put("createdAt", newComment.getCreatedAt());
            newCommentMap.put("updatedAt", newComment.getUpdatedAt());
            newCommentMap.put("id", newComment.getId());
            newCommentMap.put("content", newComment.getContent());
            newCommentMap.put("comment",
                    Map.of("content", newComment.getComment().getContent(),
                            "rating", newComment.getComment().getRating()));
            newCommentMap.put("user", userMap);
            return new ResponseData<>(HttpStatus.OK.value(),"New comment added successfully!", newCommentMap);
        } catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteReplyComment(@PathVariable Long id) {
        try {
            replyCommentService.deleteComment(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Comment deleted successfully!");
        } catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        }catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateReplyComment(@Valid @RequestBody ReplyCommentDTO replyCommentDTO, @PathVariable Long id) {
        try {
            ReplyComment updateFeedback = replyCommentService.updateComment(id, replyCommentDTO);
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("firstName", updateFeedback.getUser().getFirstName());
            userMap.put("lastName", updateFeedback.getUser().getLastName());
            userMap.put("role", Map.of("name", updateFeedback.getUser().getRole().getName()));

            Map<String, Object> feedbackMap = new HashMap<>();
            feedbackMap.put("createdAt", updateFeedback.getCreatedAt());
            feedbackMap.put("updatedAt", updateFeedback.getUpdatedAt());
            feedbackMap.put("content", updateFeedback.getContent());
            feedbackMap.put("user", userMap);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                    "Feedback updated successfully!",
                    feedbackMap
            );
        } catch (EntityNotFoundException e) {
            return new ResponseError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }
}
