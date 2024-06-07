package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Brand;
import com.ecommerce.WatchStore.Model.Category;
import com.ecommerce.WatchStore.Model.Watch;
import com.ecommerce.WatchStore.Model.WatchImage;
import com.ecommerce.WatchStore.Repository.BrandRepository;
import com.ecommerce.WatchStore.Repository.CategoryRepository;
import com.ecommerce.WatchStore.Repository.WatchImageRepository;
import com.ecommerce.WatchStore.Repository.WatchRepository;
import com.ecommerce.WatchStore.Service.WatchService;
import com.ecommerce.WatchStore.dto.WatchDTO;
import com.ecommerce.WatchStore.dto.WatchImageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WatchServiceImpl implements WatchService {

    private final WatchRepository watchRepository;
    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final WatchImageRepository watchImageRepository;

    @Override
    public Watch createProduct(WatchDTO watchDTO) {
        Brand existingBrand = brandRepository.findById(watchDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Cannot find brand with id: "+ watchDTO.getBrandId()));
        Category existingCategory = categoryRepository.findById(watchDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Cannot find category with id: " + watchDTO.getCategoryId()));
        Watch newProduct = Watch.builder()
                .userGender(watchDTO.getUserGender())
                .productName(watchDTO.getProductName())
                .discount(watchDTO.getDiscount())
                .machineType(watchDTO.getMachineType())
                .productPrice(watchDTO.getProductPrice())
                .caseMaterial(watchDTO.getCaseMaterial())
                .screen(watchDTO.getScreen())
                .caseDiameter(watchDTO.getCaseDiameter())
                .caseThickness(watchDTO.getCaseThickness())
                .strapMaterial(watchDTO.getStrapMaterial())
                .strapWidth(watchDTO.getStrapWidth())
                .glassMaterial(watchDTO.getGlassMaterial())
                .warrantyPeriod(watchDTO.getWarrantyPeriod())
                .waterResistance(watchDTO.getWaterResistance())
                .quantity(watchDTO.getQuantity())
                .newProduct(watchDTO.getNewProduct())
                .brand(existingBrand)
                .category(existingCategory)
                .build();
        watchRepository.save(newProduct);
        return newProduct;
    }

    @Override
    public Watch getProductById(long id) {
        return watchRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }

    @Override
    public Page<Watch> getAllProducts(PageRequest pageRequest) {
        return watchRepository.findAll(pageRequest);
    }

    @Override
    public Watch updateProduct(long id, WatchDTO request) {
        Watch existingProduct = watchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find watch with id: " + id));

        Brand existingBrand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Cannot find brand with id: " + request.getBrandId()));
        Category existingCategory = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Cannot find category with id: " + request.getCategoryId()));

        existingProduct.setUserGender(request.getUserGender());
        existingProduct.setProductName(request.getProductName());
        existingProduct.setDiscount(request.getDiscount());
        existingProduct.setMachineType(request.getMachineType());
        existingProduct.setProductPrice(request.getProductPrice());
        existingProduct.setCaseMaterial(request.getCaseMaterial());
        existingProduct.setScreen(request.getScreen());
        existingProduct.setCaseDiameter(request.getCaseDiameter());
        existingProduct.setCaseThickness(request.getCaseThickness());
        existingProduct.setStrapMaterial(request.getStrapMaterial());
        existingProduct.setStrapWidth(request.getStrapWidth());
        existingProduct.setGlassMaterial(request.getGlassMaterial());
        existingProduct.setDiscount(request.getDiscount());
        existingProduct.setWarrantyPeriod(request.getWarrantyPeriod());
        existingProduct.setWaterResistance(request.getWaterResistance());
        existingProduct.setQuantity(request.getQuantity());
        existingProduct.setNewProduct(request.getNewProduct());
        existingProduct.setBrand(existingBrand);
        existingProduct.setCategory(existingCategory);

        return watchRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(long id) {
        Watch checkProduct = getProductById(id);
        watchRepository.delete(checkProduct);
    }

    @Override
    public WatchImage createWatchImage(Long watchId, WatchImageDTO watchImageDTO) throws Exception {
        Watch watch = getProductById(watchId);
        WatchImage watchImage = WatchImage.builder()
                .watch(watch)
                .imageUrl(watchImageDTO.getImageUrl())
                .build();
        return watchImageRepository.save(watchImage);
    }
}
