package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.CategoryDTO;
import com.ecommerce.WatchStore.dto.WatchDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/watch")
public class WatchController {
    @GetMapping("")
    public ResponseData<?> getAllWatchProduct(WatchDTO watchDTO) {
        return new ResponseData<>(HttpStatus.OK.value(), "success", "data");
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneWatch(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "WatchId: ", 1);
    }

    @PostMapping("")
    public ResponseData<?> createWatch(@Valid @RequestBody WatchDTO watchDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Watch added successfully!", watchDTO.getCaseDiameter());
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneWatch(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Watch deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneWatch(@Valid @RequestBody WatchDTO watchDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Watch updated successfully!");
    }
}
