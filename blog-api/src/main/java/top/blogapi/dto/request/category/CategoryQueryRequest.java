package top.blogapi.dto.request.category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class CategoryQueryRequest {
    @Min(1)
    Integer pageNum = 1;
    @Min(1)
    @Max(100)
    Integer pageSize = 10;
    String sortBy = "id";
    String sortOrder = "DESC";
}
