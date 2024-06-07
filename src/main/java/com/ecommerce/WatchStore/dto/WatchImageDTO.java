package com.ecommerce.WatchStore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WatchImageDTO {
    @Min(value = 1, message = "WatchId must be > 0")
    private Long watchId;

    @Size(min = 5,max = 200, message = "Image's name")
    private String imageUrl;
}
