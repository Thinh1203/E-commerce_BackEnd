package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Brand;
import com.ecommerce.WatchStore.Service.BrandService;
import com.ecommerce.WatchStore.dto.BrandDTO;
import com.ecommerce.WatchStore.Repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public long createBrand(BrandDTO request) {
        Brand newBrand = Brand
                .builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        brandRepository.save(newBrand);
        return newBrand.getId();
    }

    @Override
    public Brand getBrandById(long id) {
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
    }

    @Override
    public Page<Brand> getAllBrands(PageRequest pageRequest) {
        return brandRepository.findAll(pageRequest);
    }

    @Override
    public Brand updateBrand(long id, BrandDTO request) {
        Brand existingBrand = getBrandById(id);
        existingBrand.setName(request.getName());
        existingBrand.setDescription(request.getDescription());
        brandRepository.save(existingBrand);
        return existingBrand;
    }

    @Override
    public void deleteBrand(long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        optionalBrand.ifPresent(brandRepository::delete);
    }
}
