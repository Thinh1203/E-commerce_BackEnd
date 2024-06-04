package com.ecommerce.WatchStore.Service.impl;

import com.ecommerce.WatchStore.Model.Category;
import com.ecommerce.WatchStore.Service.CategoryService;
import com.ecommerce.WatchStore.dto.CategoryDTO;
import com.ecommerce.WatchStore.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public long createCategory(CategoryDTO request) {
        Category newCategory = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
        categoryRepository.save(newCategory);
        return newCategory.getId();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    @Override
    public Page<Category> getAllCategories(PageRequest pageRequest) {
        return categoryRepository.findAll(pageRequest);
    }

    @Override
    public Category updateCategory(long id, CategoryDTO request) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(request.getName());
        existingCategory.setDescription(request.getDescription());
        categoryRepository.save(existingCategory);
        return existingCategory;
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }
}
