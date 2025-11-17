package top.blogapi.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.comment.CommentUpdateRequest;
import top.blogapi.exception.business_exception.domain_exception.CategoryServiceException;
import top.blogapi.exception.business_exception.domain_exception.CommentServiceException;
import top.blogapi.model.entity.Comment;
import top.blogapi.repository.CommentRepository;
import top.blogapi.service.CommentService;
import top.blogapi.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    @Override
    public List<Comment> getListByPageAndParentCommentId(Integer page, Long parentCommentId, Long blogId) {
        List<Comment> comments = commentRepository.getListByPageAndParentCommentId(page, parentCommentId, blogId);
        for (Comment c: comments) {
            c.setReplyComments(commentRepository.getListByPageAndParentCommentId(page, c.getId(), blogId));
        }
        return comments;
    }
    @Transactional
    @Override
    public void updateCommentPublishedById(Long id, boolean published) {
        try{
            int r = commentRepository.updateCommentPublishedById(id,published);
            if(r == 0)
                throw CommentServiceException.builder()
                        .operationCommentUnsuccessful("COMMENT","NOT_EXIST_TO_UPDATE",
                                id+" không tồn tại để cập nhật", HttpStatus.NOT_FOUND)
                        .build();
        }catch (DataAccessException e){
            getDataAccessException("Lỗi cập nhật Published không thành công!!",e, id);
        }
    }
    @Transactional
    @Override
    public void updateCommentNoticeById(Long id, boolean notice) {
        try{
            int r = commentRepository.updateCommentNoticeById(id, notice);
            if(r == 0)
                throw CommentServiceException.builder()
                        .operationCommentUnsuccessful("COMMENT","NOT_EXIST_TO_UPDATE",
                                id+" không tồn tại để cập nhật", HttpStatus.NOT_FOUND)
                        .build();
        }catch (DataAccessException e){
            getDataAccessException("Lỗi cập nhật Notice không thành công!!",e, id);
        }
    }
    @Transactional
    @Override
    public void deleteCommentById(Long id) {
        try{
            int r = commentRepository.deleteCommentById(id);
            if(r == 0)
                throw CommentServiceException.builder()
                        .operationCommentUnsuccessful("COMMENT","NOT_EXIST_TO_DELETE",
                                id+" không tồn tại để xóa", HttpStatus.NOT_FOUND)
                        .build();
        }catch (DataAccessException e){
            getDataAccessException("Lỗi xóa Comment không thành công!!",e, id);
        }
    }
    @Transactional
    @Override
    public void updateComment(CommentUpdateRequest request) {
        if(StringUtils.isEmpty(request.getContent(), request.getIp(), request.getEmail(), request.getNickname()))
            throw CommentServiceException.builder()
                    .invalidParameters(request.getId().toString(),"Dữ liệu nhập vào không đúng")
                    .build();
        try{
            int r = commentRepository.updateComment(request.getId(), request.getNickname(), request.getEmail(), request.getContent(), request.getIp());
            if(r == 0)
                throw CommentServiceException.builder()
                        .operationCommentUnsuccessful("COMMENT","NOT_EXIST_TO_DELETE",
                                request.getId()+" không tồn tại để cập nhật", HttpStatus.NOT_FOUND)
                        .build();
        }catch (DataAccessException e){
            getDataAccessException("Lỗi cập nhật Comment không thành công!!",e, request.getId());
        }
    }


    private void getDataAccessException(String msg, Throwable e, Long id){
        throw CommentServiceException.builder()
                .dataRetrievalFailed(msg,e )
                .entityId(id.toString())
                .build();
    }

}
