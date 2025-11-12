package top.blogapi.dto.request.tag;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagQueryRequest {
    @Min(1)
    Integer pageNum = 1;
    @Max(100)
    @Min(1)
    Integer pageSize = 10;
    String sortBy = "id";
    String sortOrder = "DESC";
}
