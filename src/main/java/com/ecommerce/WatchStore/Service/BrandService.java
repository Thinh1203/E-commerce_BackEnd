package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Brand;
import com.ecommerce.WatchStore.dto.BrandDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface BrandService {

    long createBrand (BrandDTO brand);

    Brand getBrandById (long id);

    Page<Brand> getAllBrands(PageRequest pageRequest);

    Brand updateBrand(long id, BrandDTO brand);

    void deleteBrand (long id);

}
