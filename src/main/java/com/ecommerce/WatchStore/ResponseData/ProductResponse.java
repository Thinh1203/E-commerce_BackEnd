package com.ecommerce.WatchStore.ResponseData;

import com.ecommerce.WatchStore.Model.Watch;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class ProductResponse {
    List<Watch> watches;
    private int totalPages;
    private int currentPage;
}
