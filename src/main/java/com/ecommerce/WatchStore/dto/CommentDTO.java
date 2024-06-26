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
public class CommentDTO implements Serializable {

    @NotNull(message = "UserId cannot be null")
    @Min(value = 1, message = "UserId must be > 0")
    private Long userId;

    @NotNull(message = "ProductId cannot be null")
    @Min(value = 1, message = "ProductId must be > 0")
    private Long productId;

    @NotNull(message = "Rating star cannot be null")
    @Min(value = 1, message = "Rating star must be > 0")
    private Integer rating;

    @NotBlank(message = "Content cannot be empty")
    private String content;
}
