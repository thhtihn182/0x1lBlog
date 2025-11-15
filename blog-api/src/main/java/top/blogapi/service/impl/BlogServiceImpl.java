package top.blogapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.blog.BlogQueryRequest;
import top.blogapi.dto.request.blog.BlogUpdatePublishedRequest;
import top.blogapi.dto.request.blog.BlogUpdateRecommendRequest;
import top.blogapi.entity.Blog;
import top.blogapi.entity.Tag;
import top.blogapi.exception.business_exception.domain_exception.BlogServiceException;
import top.blogapi.repository.BlogRepository;
import top.blogapi.service.BlogService;
import top.blogapi.vo.BlogIdAndTitle;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class BlogServiceImpl implements BlogService {
    BlogRepository blogRepository;

    @Override
    public PageInfo<Blog> getListByTitleOrCategory(BlogQueryRequest blogQueryRequest) {
        try(Page<Object> page = PageHelper.startPage(
                blogQueryRequest.getPageNum()
                , blogQueryRequest.getPageSize(),
                blogQueryRequest.getSortBy() + " " + blogQueryRequest.getSortOrder())) {
            return new PageInfo<>(
                    blogRepository.getListByTitleOrCategory(
                            blogQueryRequest.getQuery(),
                            blogQueryRequest.getCategoryId()
                    )
            );
        }catch (DataAccessException e){
            throw BlogServiceException.builder()
                    .dataRetrievalFailed("getListByTitleOrCategory")
                    .cause(e.getCause())
                    .context("query", blogQueryRequest.getQuery())
                    .context("categoryId", blogQueryRequest.getCategoryId())
                    .context("pageNum", blogQueryRequest.getPageNum())
                    .build();
        }
    }


    @Transactional
    @Override
    public int deleteBlogById(Long id) {
        return blogRepository.deleteBlogById(id);
    }

    @Transactional
    @Override
    public int deleteBlogTagByBlogId(Long id) {
        return blogRepository.deleteBlogTagByBlogId(id);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        LocalDateTime now = LocalDateTime.now();
        blog.setCreateTime(now);
        blog.setUpdateTime(now);
        blog.setViews(0);
        return blogRepository.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(LocalDateTime.now());
        return blogRepository.updateBlog(blog);
    }

    @Transactional
    @Override
    public Blog getBlogById(Long id) {
        Blog blog = blogRepository.getBlogById(id)
                .orElseThrow(() -> BlogServiceException.builder()
                        .blogNotFound(id.toString())
                        .build());
        List<Tag> tags = blogRepository.findTagsByBlogId(id);
        blog.setTags(tags);
        return blog;
    }

    @Transactional
    @Override
    public int saveBlogTag(Long blogId, Long tagId) {
        return blogRepository.saveBlogTag(blogId, tagId);
    }

    @Transactional
    @Override
    public void updateBlogPublishedById(BlogUpdatePublishedRequest blogUpdatePublishedRequest) {
         int r = blogRepository.updateBlogPublishedById(blogUpdatePublishedRequest.getId(),
                 blogUpdatePublishedRequest.isPublished());
         if(r != 1)
             throw BlogServiceException.builder()
                     .dataRetrievalFailed("updateBlogRecommendById")
                     .build();
    }

    @Transactional
    @Override
    public void updateBlogRecommendById(BlogUpdateRecommendRequest blogUpdateRecommendRequest) {
        int r  = blogRepository.updateBlogRecommendById(blogUpdateRecommendRequest.getId(), blogUpdateRecommendRequest.isRecommend());
        if(r != 1)
            throw BlogServiceException.builder()
                    .dataRetrievalFailed("updateBlogRecommendById")
                    .build();
    }

    @Override
    public int countBlogByCategoryId(Long categoryId) {
        return blogRepository.countBlogByCategoryId(categoryId);
    }

    @Override
    public int countBlogByTagId(Long tagId) {
        return blogRepository.countBlogByTagId(tagId);
    }

    @Override
    public List<BlogIdAndTitle> getIdAndTitleList() {
        return blogRepository.getIdAndTitleList();
    }

}
