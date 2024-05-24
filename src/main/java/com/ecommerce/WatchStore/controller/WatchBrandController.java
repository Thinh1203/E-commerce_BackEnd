package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.BrandDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/brand")
public class WatchBrandController {

    @GetMapping("")
    public ResponseData<?> getAllBrand() {
        return new ResponseData<>(HttpStatus.OK.value(), "success", "data");
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneBrand(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "Brand: ", 1);
    }

    @PostMapping("")
    public ResponseData<?> insertBrand(@Valid @RequestBody BrandDTO brandDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Brand added successfully!", brandDTO.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneBrand(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Brand deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneBrand(@Valid @RequestBody BrandDTO brandDTO, @PathVariable("id") Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Brand updated successfully!", String.format("Updated accessory for id: %d", id));
    }
}
