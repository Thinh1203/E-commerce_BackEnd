package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccessoryDTO {

    @NotBlank(message = "Accessory's productName cannot be empty!")
    @Size(min = 5, max = 200, message = "ProductName must be between 5 and 200 characters!")
    private String productName;

    private Integer discount;

    private Boolean newProduct;

    @NotNull(message = "Product price cannot be empty!")
    @Min(value = 10000, message = "Product price must be greater than 10000!")
    private Integer productPrice;

    @NotNull(message = "Accessory's productName cannot be empty!")
    @Min(value = 1, message = "Quantity must be greater than 0!")
    @Max(value = 100000, message = "Quantity must be less than 100000")
    private Long quantity;

    @NotBlank(message = "Accessory's material cannot be empty!")
    private String material;

    @NotNull(message = "Accessory's wireWidth cannot be empty!")
    @Min(value = 10, message = "Watch's strapWidth must be greater than 10!")
    private Float wireWidth;

    @NotBlank(message = "Accessory's color cannot be empty!")
    private String color;

    private Boolean isDeleted;

    @NotNull(message = "CategoryID cannot be empty!")
    private Long categoryID;

    private MultipartFile file;
}
