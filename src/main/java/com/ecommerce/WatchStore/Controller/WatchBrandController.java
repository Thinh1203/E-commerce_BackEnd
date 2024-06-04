package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.Brand;
import com.ecommerce.WatchStore.ResponseData.BrandResponse;
import com.ecommerce.WatchStore.Service.BrandService;
import com.ecommerce.WatchStore.dto.BrandDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/brand")
@RequiredArgsConstructor
public class WatchBrandController {

    private final BrandService brandService;

    @GetMapping("")
    public ResponseData<BrandResponse> getAllBrand(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit);
            Page<Brand> brandPage = brandService.getAllBrands(pageRequest);
            int totalPage = brandPage.getTotalPages();
            int currentPage = brandPage.getNumber();
            List<Brand> brands= brandPage.getContent();
            return new ResponseData<>(HttpStatus.OK.value(), "List brands: ",
                    BrandResponse
                            .builder()
                            .brands(brands)
                            .totalPages(totalPage)
                            .currentPage(currentPage)
                            .build());
        } catch (RuntimeException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneBrand(@PathVariable Long id) {
        try {
            Brand brand = brandService.getBrandById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "Brand: ", brand);
        } catch (RuntimeException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PostMapping("")
    public ResponseData<?> insertBrand(@Valid @RequestBody BrandDTO brandDTO) {
        try {
            long newBrand = brandService.createBrand(brandDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Brand added successfully!", newBrand);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneBrand(@PathVariable Long id) {
        try {
            brandService.deleteBrand(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully!");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneBrand(@Valid @RequestBody BrandDTO brandDTO, @PathVariable("id") Long id) {
        try {
            Brand updateBrand = brandService.updateBrand(id, brandDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Brand updated successfully!", updateBrand);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
