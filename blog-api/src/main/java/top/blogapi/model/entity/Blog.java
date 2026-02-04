package top.blogapi.model.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Blog {
    Long id;
    String title; // Tiêu đề bài viết
    String content; // Nội dung bài viết
    String description; // Mô tả
    Boolean published; // Đã xuất bản hay bản nháp
    Boolean recommend; // Bật/tắt đề xuất
    Boolean appreciation; // Bật/tắt chức năng ủng hộ (donate)
    Boolean commentEnabled; // Bật/tắt bình luận
    Boolean top; // Ghim blog
    LocalDateTime createTime; // Thời gian tạo
    LocalDateTime updateTime; // Thời gian cập nhật
    Integer views; // Lượt xem
    Integer words; // Số lượng từ trong bài viết
    Integer readTime; // Thời gian đọc (phút)
    String musicId;
    User user; // Tác giả bài viết (vì là blog cá nhân nên có thể bỏ, tạm thời giữ lại)
    Category category; // Thể loại bài viết
    List<Tag> tags = new ArrayList<>(); // Danh sách thẻ (tag) của bài viết

}
