package top.blogapi.service;

import com.github.pagehelper.PageInfo;
import top.blogapi.dto.request.blog.BlogQueryRequest;
import top.blogapi.dto.request.blog.BlogUpdatePublishedRequest;
import top.blogapi.dto.request.blog.BlogUpdateRecommendRequest;
import top.blogapi.model.entity.Blog;
import top.blogapi.model.vo.BlogIdAndTitle;
import top.blogapi.model.vo.BlogInfo;

import java.util.List;

public interface BlogService {
    PageInfo<Blog> getListByTitleOrCategory(BlogQueryRequest blogQueryRequest);

    void deleteBlogById(Long id);

    int deleteBlogTagByBlogId(Long id);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    Blog getBlogById(Long id);

    int saveBlogTag(Long blogId, Long tagId);

    void updateBlogPublishedById(BlogUpdatePublishedRequest blogUpdatePublishedRequest);

    void updateBlogRecommendById(BlogUpdateRecommendRequest blogUpdateRecommendRequest);

    int countBlogByCategoryId(Long categoryId);

    void updateBlogTopById(Long blogId, Boolean top);

    int countBlogByTagId(Long tagId);

    List<BlogIdAndTitle> getIdAndTitleList();

    List<BlogInfo> getBlogInfoListByIsPublished();

    List<BlogIdAndTitle> getIdAndTitleListByIsPublishedAndIsRecommend();
}
