package top.blogapi.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 *  description: Cài đặt web
 * */

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class SiteSetting {
    Long id;
    String nameEn;
    String nameVn;
    String value;
    Integer type;
}
