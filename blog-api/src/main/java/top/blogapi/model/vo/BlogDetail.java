package top.blogapi.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import top.blogapi.model.entity.Category;
import top.blogapi.model.entity.Tag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BlogDetail {
    Long id;
    String title;
    String content;
    String flag;
    Boolean appreciation;
    Boolean shareStatement;
    Boolean commentEnabled;
    Boolean top;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    Integer views;
    Integer words;
    Integer readTime;

    Category category;
    List<Tag> tags = new ArrayList<>();
}
