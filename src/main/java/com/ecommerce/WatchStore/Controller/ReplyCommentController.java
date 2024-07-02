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

    @GetMapping("")
    public ResponseData<?> getAllComment() {
        return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
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
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "An unexpected error occurred");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteReplyComment(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Comment deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateReplyComment(@Valid @RequestBody ReplyCommentDTO replyCommentDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                "Comment updated successfully!",
                String.format("Update comment with id = %d, content = %s", id, replyCommentDTO.getContent())
        );
    }
}
