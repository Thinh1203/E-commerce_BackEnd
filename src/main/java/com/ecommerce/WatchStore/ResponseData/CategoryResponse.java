package com.ecommerce.WatchStore.ResponseData;


import com.ecommerce.WatchStore.Model.Category;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class CategoryResponse {
    List<Category> categories;
    private int totalPages;
    private int currentPage;
}
