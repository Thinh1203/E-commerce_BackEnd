package com.ecommerce.WatchStore.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    @NotBlank(message = "Email cannot be empty!")
    @Email(message = "email invalid format")
    private String email;

    @NotBlank(message = "numberPhone cannot be empty!")
    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    private String numberPhone;

    @NotBlank(message = "password cannot be empty!")
    private String password;

    @NotNull(message = "Birthday cannot be null!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthday;

    @NotBlank(message = "fistName cannot be empty!")
    private String fistName;

    @NotBlank(message = "lastName cannot be empty!")
    private String lastName;

    @NotBlank(message = "gender cannot be empty!")
    private String gender;

    private Integer facebookAccountId;
    private Integer googleAccountId;

    @NotNull(message = "RoleID cannot be empty!")
    private Long roleId;
}
