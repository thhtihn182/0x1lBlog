package top.blogapi.dto.request.comment;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CommentQueryRequest {
    Integer page;
    Long blogId;
    @Max(100)
    @Min(1)
    Integer pageSize = 10;
    Integer pageNum = 1;
    String sortBy = "create_time";
    String sortOrder = "DESC";
}
