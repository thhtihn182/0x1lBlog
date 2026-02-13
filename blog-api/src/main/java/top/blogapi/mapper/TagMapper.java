package top.blogapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import top.blogapi.dto.response.tag.TagIdGetBlogsResponse;
import top.blogapi.dto.response.tag.TagResponse;
import top.blogapi.model.entity.Tag;
import top.blogapi.model.vo.BlogTagsInfo;
import top.blogapi.util.StringUtils;
import top.blogapi.util.markdown.MarkdownUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    @Mappings({
            @Mapping(source = "id", target = "tagId"),
            @Mapping(source = "name", target = "tagName"),
            @Mapping(source = "color", target = "tagColor")
    })
    TagResponse tagToTagResponse(Tag tag);

    TagIdGetBlogsResponse.Tag toTagIdGetBlogsResponse_Tag(Tag tag);

    List<TagIdGetBlogsResponse.BlogInfo> toTagIdGetBlogInfoList(List<BlogTagsInfo> blogTagsInfos);

    @Mapping(target = "tags", source = ".", qualifiedByName = "convertTagToList")
    @Mapping(target = "description", source = "description", qualifiedByName = "convertMarkdownToHtml")
    TagIdGetBlogsResponse.BlogInfo toTagIdGetBlogsResponse (BlogTagsInfo blogTagsInfo);

    @Named("convertMarkdownToHtml")
    default String convertMarkdownToHtml(String description) {
        if (StringUtils.isEmpty(description))
            return "";
        return MarkdownUtils.markdownToHtmlExtensions(description);
    }

    @Named("convertTagToList")
    default List<TagIdGetBlogsResponse.Tag> convertToTagList(BlogTagsInfo source){
        if (source.getAllTagIds() == null || source.getAllTagIds().isEmpty())
            return List.of();

        String[] ids = source.getAllTagIds().split(",");
        String[] names = source.getAllTagNames().split(",");
        String[] colors = source.getAllTagColors().split(",");

        List<TagIdGetBlogsResponse.Tag> tags = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            TagIdGetBlogsResponse.Tag tag = new TagIdGetBlogsResponse.Tag();
            tag.setId(Long.parseLong(ids[i].trim()));
            tag.setName(names[i].trim());
            tag.setColor(colors[i].trim());
            tags.add(tag);
        }
        return tags;
    }

}
