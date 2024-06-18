package com.ecommerce.WatchStore.Service;

import com.ecommerce.WatchStore.Model.Category;
import com.ecommerce.WatchStore.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface CategoryService {

    long createCategory(CategoryDTO category);

    Category getCategoryById(long id);

    Page<Category> getAllCategories(PageRequest pageRequest);

    Category updateCategory(long id, CategoryDTO category);

    void deleteCategory(long id);
}
