package top.blogapi.model.vo;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@Setter
@Getter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Favorite {
    String title;
    String content;
}
