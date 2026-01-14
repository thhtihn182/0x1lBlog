package top.blogapi.dto.response._common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Builder
@ToString
public class ErrorResponse {
    Integer code;
    String errorCode;
    String msg;
    String cause;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime timestamp;
    String httpMethod;
    String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String domain;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String entityId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Map<String, Object> context;

}
