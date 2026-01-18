package top.blogapi.dto.response.blog;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArchiveBlogResponse {
    Long id;
    String title;
    String day;
}
