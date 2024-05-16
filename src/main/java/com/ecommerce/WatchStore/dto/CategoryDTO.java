package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Category's name cannot be empty!")
    private String name;

    @NotBlank(message = "Category's description cannot be empty!")
    private String description;
}
