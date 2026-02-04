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
    Boolean appreciation;
    Boolean commentEnabled;
    Boolean top;
    LocalDateTime createTime;
    LocalDateTime updateTime;
    Integer views;
    Integer words;
    Integer readTime;
    String musicId;
    Category category;
    List<Tag> tags = new ArrayList<>();
    MusicInfo musicInfo;
    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @NoArgsConstructor
    public static class MusicInfo{
        String name; // tên bài hát
        String lrc;  // lyric
        String artist; // Tên ca sĩ
        String url; // media
        String theme;
        String cover; // Ảnh bìa
    }
}
