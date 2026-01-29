package top.blogapi.service;

import top.blogapi.dto.request.comment.CommentUpdateRequest;
import top.blogapi.model.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Comment> getListByPageAndParentCommentId(Integer page, Long parentCommentId, Long blogId);

    void updateCommentPublishedById(Long id, boolean published);

    void updateCommentNoticeById(Long id, boolean notice);

    void deleteCommentById(Long id);

    void updateComment(CommentUpdateRequest request);
}
