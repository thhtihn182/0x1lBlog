package top.blogapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.category.CategoryQueryRequest;
import top.blogapi.exception.business_exception.BusinessException;
import top.blogapi.exception.system_exception.SystemException;
import top.blogapi.model.entity.Category;
import top.blogapi.repository.CategoryRepository;
import top.blogapi.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CategoryServiceImpl implements CategoryService {
    CategoryRepository categoryRepository;
    @Override
    public List<Category> getCategoryList() {
        return categoryRepository.getCategoryList();
    }

    @Override
    public PageInfo<Category> getCategoryList(CategoryQueryRequest request) {
        try(Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize(),
                request.getSortBy() + " " + request.getSortOrder());){
            return new PageInfo<>(categoryRepository.getCategoryList()) ;

        }
    }

    @Transactional
    @Override
    public Category saveCategory(String categoryName) {
        Category c = new Category(categoryName);
        if(categoryRepository.saveCategory(c)==0)
            throw SystemException.builder()
                    .message("Không thể thêm thể loại!")
                    .build();
        return c;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() ->
                        BusinessException.builder()
                                .notFound("CATEGORY")
                                .message("Thể loại này không tồn tại")
                                .context("categoryId", id)
                                .build());
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name)
                .orElseThrow(() ->
                        BusinessException.builder()
                                .notFound("CATEGORY")
                                .message("Thể loại này không tồn tại")
                                .context("categoryName", name)
                                .build()
                );
    }

    @Override
    public boolean categoryExist(String categoryName) {
        return categoryRepository.categoryExist(categoryName).isPresent();
    }

    @Override
    public void deleteCategoryById(Long id) {
        if(categoryRepository.deleteCategoryById(id)==0)
            throw BusinessException.builder()
                    .notFound("CATEGORY")
                    .message("Thể loại này không tồn tại")
                    .context("categoryId", id)
                    .build();
    }

    @Override
    public void updateCategory(Category category) {
        if( categoryRepository.updateCategory(category)==0)
            throw BusinessException.builder()
                    .notFound("CATEGORY")
                    .message("Thể loại này không tồn tại")
                    .context("categoryId", category.getId())
                    .build();
    }
}
