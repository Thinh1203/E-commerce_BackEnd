package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Watch;
import com.ecommerce.WatchStore.Model.WatchImage;
import com.ecommerce.WatchStore.dto.WatchDTO;
import com.ecommerce.WatchStore.dto.WatchImageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface WatchService {
    Watch createProduct(WatchDTO watchDTO);

    Watch getProductById(long id);

    Page<Watch> getAllProducts(PageRequest pageRequest);

    Watch updateProduct(long id, WatchDTO watchDTO);

    void deleteProduct(long id);

    WatchImage createWatchImage(Long watchId, WatchImageDTO watchImageDTO) throws Exception;
}
