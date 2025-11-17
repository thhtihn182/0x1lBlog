package top.blogapi.dto.request.comment;


import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CommentUpdateRequest {
    Long id;
    String content;
    String ip;
    String email;
    String nickname;
}
