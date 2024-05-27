package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailDTO {

    @Min(value = 1, message = "OrderID must be > 0")
    private Long orderId;

    @Min(value = 1, message = "ProductID must be > 0")
    private Long productId;

    @Min(value = 0, message = "price must be > 0")
    private Float price;

    @Min(value = 1, message = "Quantity must be > 0")
    private Integer quantity;

    @Min(value = 0, message = "TotalMoney must be > 0")
    private Float totalMoney;

}
