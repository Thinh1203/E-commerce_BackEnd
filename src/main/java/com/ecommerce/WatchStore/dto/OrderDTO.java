package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "email invalid format")
    private String email;

    @NotBlank(message = "Number phone is required")
    @Pattern(regexp = "^\\d{10}$", message = "phone invalid format")
    private String numberPhone;

    @NotBlank(message = "Address cannot be null")
    private String address;

    private String note;

    @Min(value = 0, message = "Total money must be > 0")
    private Float totalMoney;

    private String shippingMethod;

    private String shippingAddress;

    private Boolean isDeleted; // xoa mem

    @NotBlank(message = "Payment method is required")
    private String paymentMethod;
}
