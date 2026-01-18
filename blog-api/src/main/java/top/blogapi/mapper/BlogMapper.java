package top.blogapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import top.blogapi.dto.response.blog.ArchiveBlogResponse;
import top.blogapi.dto.response.blog.BlogSummaryResponse;
import top.blogapi.model.entity.Blog;
import top.blogapi.model.vo.ArchiveBlog;

@Mapper(componentModel = "spring")
public interface BlogMapper {
    @Mapping(source = "category.name", target = "categoryName")
    BlogSummaryResponse toBlogSummaryResponse(Blog blog);


    ArchiveBlogResponse toArchiveBlogResponse(ArchiveBlog archiveBlog);
}
