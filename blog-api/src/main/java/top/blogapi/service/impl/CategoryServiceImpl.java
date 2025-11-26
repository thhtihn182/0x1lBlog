package top.blogapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.category.CategoryQueryRequest;
import top.blogapi.model.entity.Category;
import top.blogapi.exception.business_exception.domain_exception.CategoryServiceException;
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

        }catch (DataAccessException e) {
            throw CategoryServiceException.builder()
                    .dataRetrievalFailed("Lỗi lấy danh sách thể loại")
                    .cause(e.getCause())
                    .context("pageSize", request.getPageSize())
                    .context("pageNum", request.getPageNum())
                    .build();
        }
    }

    @Transactional
    @Override
    public Category saveCategory(String categoryName) {
        Category category = new Category(categoryName);
        int r = categoryRepository.saveCategory(category);
        if(r == 1)
            return category;
        throw CategoryServiceException.builder()
                .operationCategoryUnsuccessful("BLOG", HttpStatus.INTERNAL_SERVER_ERROR,"Thêm thể loại không thành công")
                .build();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getCategoryById(id)
                .orElseThrow(() ->
                        CategoryServiceException.builder()
                                .categoryNotExist("BLOG","Thể loại không tồn tại")
                                .build());
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name)
                .orElseThrow(() ->
                        CategoryServiceException.builder()
                                .categoryNotExist("BLOG","Không thể thêm danh mục hiện có")
                                .build());
    }

    @Override
    public boolean categoryExist(String categoryName) {
        return categoryRepository.categoryExist(categoryName).isPresent();
    }

    @Override
    public int deleteCategoryById(Long id) {
        return categoryRepository.deleteCategoryById(id);
    }

    @Override
    public int updateCategory(Category category) {
        int r = categoryRepository.updateCategory(category);
        if(r == 1)
            return r;
        throw CategoryServiceException.builder()
                .operationCategoryUnsuccessful("CATEGORY",HttpStatus.INTERNAL_SERVER_ERROR,"Cập nhật thể loại không thành công !!")
                .build();
    }
}
