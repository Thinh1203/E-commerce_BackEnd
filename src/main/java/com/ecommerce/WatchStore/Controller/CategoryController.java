package com.ecommerce.WatchStore.Controller;

import com.ecommerce.WatchStore.Exception.ResponseData;
import com.ecommerce.WatchStore.Exception.ResponseError;
import com.ecommerce.WatchStore.Model.Category;
import com.ecommerce.WatchStore.ResponseData.CategoryResponse;
import com.ecommerce.WatchStore.Service.CategoryService;
import com.ecommerce.WatchStore.dto.CategoryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseData<Long> createCategory( @Valid @RequestBody CategoryDTO categoryDTO) {
        try {
            long newCategoryId = categoryService.createCategory(categoryDTO);
            return new ResponseData<>(HttpStatus.OK.value(), "Category added successfully! ", newCategoryId);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Add category fail");
        }
    }

    @GetMapping("")
    public ResponseData<CategoryResponse> getAllCategory(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int limit) {
        try {
            PageRequest pageRequest = PageRequest.of(page, limit);
            Page<Category> categoryPage = categoryService.getAllCategories(pageRequest);
            int totalPage = categoryPage.getTotalPages();
            int currentPage = categoryPage.getNumber();
            List<Category> categories = categoryPage.getContent();
            return new ResponseData<>(HttpStatus.OK.value(), "success", CategoryResponse
                    .builder()
                    .categories(categories)
                    .totalPages(totalPage)
                    .currentPage(currentPage)
                    .build());
        }catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getOneCategory(@PathVariable Long id) {
        try {
            Category category = categoryService.getCategoryById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "CategoryId: ", category);
        } catch (RuntimeException e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteOneCategory(
            @PathVariable @Min(value = 1, message = "CategoryId must be greater than 0") Long id) {
        try {
            categoryService.deleteCategory(id);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Category deleted successfully!");
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseData<Category> updateOneCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Long id) {
        try {
            Category updateCategory = categoryService.updateCategory(id, categoryDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Category updated successfully!", updateCategory);
        } catch (Exception e) {
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
