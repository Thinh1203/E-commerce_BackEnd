package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO implements Serializable {
    @NotBlank(message = "Brand's name cannot be null!")
    private String name;

    @NotBlank(message = "Brand's description cannot be null!")
    private String description;
}
