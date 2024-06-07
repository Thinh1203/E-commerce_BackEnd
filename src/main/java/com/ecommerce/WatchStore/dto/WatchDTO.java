package com.ecommerce.WatchStore.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WatchDTO implements Serializable {

    @NotBlank(message = "UserGender cannot be empty!")
    private String userGender;

    @NotBlank(message = "Watch's machineType cannot be empty!")
    private String machineType;

    @NotBlank(message = "Watch's screen cannot be empty!")
    private String screen;

    @NotBlank(message = "Watch's caseMaterial cannot be empty!")
    private String caseMaterial;

    @NotNull(message = "Watch's caseDiameter cannot be empty!")
    @Min(value = 20, message = "Watch's caseDiameter must be greater than 20!")
    private Float caseDiameter;

    @NotBlank(message = "Watch's caseThickness cannot be empty!")
    private String caseThickness;

    @NotBlank(message = "Watch's strapMaterial cannot be empty!")
    private String strapMaterial;

    @NotNull(message = "Watch's strapWidth cannot be empty!")
    private Float strapWidth;

    @NotBlank(message = "Watch's glassMaterial cannot be empty!")
    private String glassMaterial;

    @NotNull(message = "Watch's warrantyPeriod cannot be empty!")
    private Float warrantyPeriod;

    @NotBlank(message = "waterResistance")
    private String waterResistance;

    @NotBlank(message = "Watch's productName cannot be empty!")
    @Size(min = 5, max = 200, message = "ProductName must be between 5 and 200 characters!")
    private String productName;

    private Integer discount;

    @NotNull(message = "Quantity cannot be empty!")
    @Min(value = 1, message = "Quantity must be greater than 0!")
    @Max(value = 100000, message = "Quantity must be less than 100000")
    private Long quantity;

    private Boolean newProduct;

    @NotNull(message = "Product price cannot be empty!")
    @Min(value = 100000, message = "Product price must be greater than 100000!")
    private Integer productPrice;

    @NotNull(message = "CategoryId cannot be empty!")
    private Long categoryId;

    @NotNull(message = "BrandId cannot be empty!")
    private Long brandId;

    private Boolean isDeleted;

    private List<MultipartFile> files;

}
