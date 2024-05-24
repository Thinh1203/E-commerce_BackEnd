package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.ReplyCommentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/replyComment")
public class ReplyCommentController {

    @GetMapping("")
    public ResponseData<?> getAllComment() {
        return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
    }

    @PostMapping("")
    public ResponseData<?> createReplyComment( @Valid @RequestBody ReplyCommentDTO replyCommentDTO) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Reply comment added successfully! ",
                String.format(
                        "Reply comment  with id = %d, content = %s",
                        replyCommentDTO.getCommentId(),
                        replyCommentDTO.getContent()
                )
                );
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
