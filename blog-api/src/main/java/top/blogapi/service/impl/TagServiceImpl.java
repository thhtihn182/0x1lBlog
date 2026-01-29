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
import top.blogapi.exception.business_exception.BusinessException;
import top.blogapi.exception.system_exception.SystemException;
import top.blogapi.model.entity.Tag;
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
                tagQueryRequest.getSortBy() + " " + tagQueryRequest.getSortOrder())) {
            return new PageInfo<>(
                    tagRepository.getTagList()
            );
        }
    }

    @Transactional
    @Override
    public Tag saveTag(String name, String color) {
            Tag t =new Tag (name,color);
            if(tagRepository.saveTag(t)==0)
                throw SystemException.builder()
                        .message("Thêm tag thất bại")
                        .operate("insert")
                        .build();
            return  t;
    }

    @Override
    public Tag getTagById(Long id) {
        return tagRepository.getTagById(id).orElseThrow(() ->
                BusinessException.builder()
                        .notFound("TAG")
                        .message("Tag không tồn tại")
                        .context("operate", "select")
                        .build()
        );
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRepository.getTagByName(name).get();
    }

    @Override
    public boolean tagExist(String name) {
        return tagRepository.tagExist(name).isPresent();
    }

    @Override
    public void deleteTagById(Long tagId) {
        if(tagRepository.deleteTagById(tagId) ==0)
            throw SystemException.builder()
                    .message("Xóa Tag thất bại")
                    .context("tagId", tagId)
                    .operate("delete")
                    .build();
    }

    @Override
    public void updateTag(String name, String color, Long id) {
       if(tagRepository.updateTag(name, color, id)==0)
           throw SystemException.builder()
                   .message("Cập nhật Tag thất bại")
                   .context("tagId", id)
                   .operate("update")
                   .build();
    }

    @Override
    public List<Tag> getTagListByBlogId(Long blogId) {
        return tagRepository.getTagListByBlogId(blogId);
    }
}
