package top.blogapi.dto.response.tag;


import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagResponse {
    Long tagId;
    String tagName;
    String tagColor;
}
