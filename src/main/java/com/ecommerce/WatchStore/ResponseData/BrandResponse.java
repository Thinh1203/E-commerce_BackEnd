package com.ecommerce.WatchStore.ResponseData;

import com.ecommerce.WatchStore.Model.Brand;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class BrandResponse {
    private List<Brand> brands;
    private int totalPages;
    private int currentPage;
}
