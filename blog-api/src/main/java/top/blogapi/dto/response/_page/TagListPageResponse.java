package top.blogapi.dto.response._page;

import com.github.pagehelper.PageInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;
import top.blogapi.dto.response.tag.TagResponse;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TagListPageResponse {
    PageInfo<TagResponse> tags;
}
