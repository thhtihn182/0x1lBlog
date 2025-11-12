package top.blogapi.service.impl.orchestration;


import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.tag.CreateTagRequest;
import top.blogapi.dto.request.tag.TagQueryRequest;
import top.blogapi.dto.request.tag.UpdateTagRequest;
import top.blogapi.dto.response._page.TagListPageResponse;
import top.blogapi.dto.response.tag.TagResponse;
import top.blogapi.mapper.TagMapper;
import top.blogapi.service.TagService;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TagOrchestrator {
    TagService tagService;
    TagMapper tagMapper;

    public TagListPageResponse getTagListPage(TagQueryRequest tagQueryRequest) {
        PageInfo<TagResponse> pageInfo = tagService.getTagList(tagQueryRequest)
                .convert(tagMapper::tagToTagResponse);
        return new TagListPageResponse(pageInfo);
    }

    public String createTag(CreateTagRequest request){
        System.out.println(request);
        tagService.saveTag(request.getTagName(),request.getTagColor());
        return "Tạo Tag thành công !!";
    }

    public String deleteTagById(Long tagId){
        tagService.deleteTagById(tagId);
        return "Xóa tag thành công!!";
    }

    public String updateTag(UpdateTagRequest request){
        tagService.updateTag(request.getTagName(), request.getTagColor(), request.getId());
        return "Cập nhật tag thành công!!";
    }
}
