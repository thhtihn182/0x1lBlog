package top.blogapi.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.tag.TagQueryRequest;
import top.blogapi.model.entity.Tag;
import top.blogapi.exception.business_exception.domain_exception.TagServiceException;
import top.blogapi.repository.TagRepository;
import top.blogapi.service.TagService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;

    @Override
    public List<Tag> getTagList() {
        return tagRepository.getTagList();
    }

    @Override
    public PageInfo<Tag> getTagList(TagQueryRequest tagQueryRequest) {
        try(Page<Object> page = PageHelper.startPage(
                tagQueryRequest.getPageNum(),
                tagQueryRequest.getPageSize(),
                tagQueryRequest.getSortBy() + " " + tagQueryRequest.getSortOrder())){
            return new PageInfo<>(
                        tagRepository.getTagList()
                    );
        }catch (DataAccessException e){
            throw TagServiceException.builder()
                    .dataRetrievalFailed("TAG","Lỗi truy vấn tag")
                    .cause(e.getCause())
                    .context("pageNum", tagQueryRequest.getPageNum())
                    .build();
        }
    }

    @Transactional
    @Override
    public int saveTag(String name, String color) {

        try{
            return tagRepository.saveTag(new Tag(name,color));
        }catch (Exception e){
            throw TagServiceException.builder()
                    .operationTagUnsuccessful("TAG",
                            "CREATE_TAG_UNSUCCESSFUL",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Tạo mới Tag không thành công!!")
                    .msgCause(e.getMessage())
                    .cause(e.getCause())
                    .build();
        }

    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.getTagById(id).orElseThrow(() ->
                TagServiceException.builder()
                        .tagNotExist("BLOG", HttpStatus.BAD_REQUEST,"Tag không tồn tại")
                        .build());
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getTagByName(name).orElseThrow(() ->
                TagServiceException.builder()
                        .tagNotExist("BLOG",HttpStatus.BAD_REQUEST,"Không thể thêm Tag hiện có !!")
                        .build());
    }

    @Override
    public void deleteTagById(Long tagId) {
        int r = tagRepository.deleteTagById(tagId);
        if (r == 0)
            throw TagServiceException.builder()
                    .operationTagUnsuccessful("TAG",
                            "DELETE_TAG_UNSUCCESSFUL",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Xóa tag không thành công!! [id:"+ tagId+"]")
                    .entityId(tagId.toString())
                    .build();
    }

    @Override
    public void updateTag(String name, String color, Long id) {
        int r = tagRepository.updateTag(name, color, id);
        if (r == 0)
            throw TagServiceException.builder()
                    .operationTagUnsuccessful("TAG",
                            "UPDATE_TAG_UNSUCCESSFUL",
                            HttpStatus.INTERNAL_SERVER_ERROR,
                            "Cập nhật tag không thành công!! [id:"+ id+"]")
                    .entityId(id.toString())
                    .build();
    }
}
