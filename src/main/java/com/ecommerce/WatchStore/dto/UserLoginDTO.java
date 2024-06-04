package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {

    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    @NotBlank(message = "NumberPhone is required!")
    private String numberPhone;

    @NotBlank(message = "Password is required!")
    private String password;
}
