package top.blogapi.dto.request.tag;

import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class UpdateTagRequest {
    Long id;
    String tagName;
    String tagColor;
}
