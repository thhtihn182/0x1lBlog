package top.blogapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import top.blogapi.dto.response.tag.TagResponse;
import top.blogapi.model.entity.Tag;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mappings({
            @Mapping(source = "id", target = "tagId"),
            @Mapping(source = "name", target = "tagName"),
            @Mapping(source = "color", target = "tagColor")
    })
    TagResponse tagToTagResponse(Tag tag);
}
