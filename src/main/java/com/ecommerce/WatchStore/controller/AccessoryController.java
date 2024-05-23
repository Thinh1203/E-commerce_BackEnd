package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.AccessoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/accessory")
public class AccessoryController {

    @GetMapping("")
    public ResponseData<?> getAllAccessory(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        return new ResponseData<>(HttpStatus.OK.value(), "success", String.format("getAllWatchProduct, page = %d, limit = %d", page, limit));
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneAccessory(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "WatchId: ", 1);
    }

    @PostMapping("")
    public ResponseData<?> createAccessory(@Valid @RequestBody AccessoryDTO accessoryDTO) {
        return new ResponseData<>(HttpStatus.CREATED.value(), "Accessory added successfully!", accessoryDTO.getColor());
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneAccessory(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Accessory deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneAccessory(@Valid @RequestBody AccessoryDTO accessoryDTO, @PathVariable("id") Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Accessory updated successfully!", String.format("Updated accessory for id: %d", id));
    }
}
