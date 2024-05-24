package com.ecommerce.WatchStore.controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.dto.CategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    @GetMapping("")
    public ResponseData<?> getAllCategory() {
        return new ResponseData<>(HttpStatus.OK.value(), "success", "data");
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneCategory(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(), "CategoryId: ", 1);
    }

    @PostMapping("")
    public ResponseData<?> createCategory( @Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseData<>(HttpStatus.OK.value(), "Category added successfully! ", 1);
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneCategory(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Category deleted successfully!");
    }

    @PutMapping ("/{id}")
    public ResponseData<?> updateOneCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Category updated successfully!");
    }
}
