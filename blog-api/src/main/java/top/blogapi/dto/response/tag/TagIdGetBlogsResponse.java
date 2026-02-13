package top.blogapi.dto.response.tag;

import com.github.pagehelper.PageInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;
import top.blogapi.model.entity.Category;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagIdGetBlogsResponse {
    Tag queryTag;
    PageInfo<BlogInfo> blogInfos;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class BlogInfo{
        Long id;
        String title;
        String description;
        LocalDateTime createTime;
        Integer views;
        Integer words;
        Integer readTime;
        Boolean top;
        Category category;
        List<Tag> tags;
    }
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Tag{
        Long id;
        String name;
        String color;
    }
}


