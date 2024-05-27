package com.ecommerce.WatchStore.dto;

import com.ecommerce.WatchStore.Model.Watch;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    @NotBlank(message = "Category's name cannot be empty!")
    private String name;

    @NotBlank(message = "Category's description cannot be empty!")
    private String description;
}
