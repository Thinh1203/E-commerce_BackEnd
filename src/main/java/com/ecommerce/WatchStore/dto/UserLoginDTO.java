package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @NotBlank(message = "NumberPhone is required!")
    private String numberPhone;

    @NotBlank(message = "Password is required!")
    private String password;
}
