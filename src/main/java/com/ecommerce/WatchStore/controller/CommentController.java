package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.CommentDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/comment")
public class CommentController {

    @GetMapping("")
    public ResponseData<?> getAllComment() {
        return new ResponseData<>(HttpStatus.OK.value(), "Successfully", "data");
    }

    @PostMapping("")
    public ResponseData<?> createComment( @Valid @RequestBody CommentDTO commentDTO) {
        return new ResponseData<>(HttpStatus.OK.value(), "Comment added successfully! ",
                String.format("Update comment with Rating = %d, content = %s", commentDTO.getRating(), commentDTO.getContent())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteComment(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Comment deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateComment(@Valid @RequestBody CommentDTO commentDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(),
                "Comment updated successfully!",
                String.format("Update comment with id = %d, content = %s", id, commentDTO.getContent())
        );
    }
}
