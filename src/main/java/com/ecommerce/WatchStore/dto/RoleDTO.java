package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO implements Serializable {

    @NotBlank(message = "Role cannot be empty")
    private String role;

    @NotBlank(message = "Description cannot be empty")
    private String description;
}
