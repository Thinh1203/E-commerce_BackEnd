package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {
    @NotBlank(message = "Brand's name cannot be null!")
    private String name;

    @NotBlank(message = "Brand's description cannot be null!")
    private String description;
}
