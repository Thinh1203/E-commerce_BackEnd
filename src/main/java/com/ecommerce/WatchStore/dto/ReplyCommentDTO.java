package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyCommentDTO implements Serializable {

    @NotNull(message = "commentId cannot be null")
    @Min(value = 1, message = "commentId must be > 0")
    private Long commentId;

    @NotBlank(message = "Content cannot be empty")
    private String content;

    @NotNull(message = "UserId cannot be null")
    @Min(value = 1, message = "UserId must be > 0")
    private Long userId;
}
