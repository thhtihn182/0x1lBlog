package top.blogapi.service;

import com.github.pagehelper.PageInfo;
import top.blogapi.dto.request.category.CategoryQueryRequest;
import top.blogapi.dto.response.category.CategoryResponse;
import top.blogapi.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getCategoryList();

    PageInfo<Category> getCategoryList(CategoryQueryRequest request);

    Category saveCategory(String categoryName);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    int deleteCategoryById(Long id);

    int updateCategory(Category category);
}
