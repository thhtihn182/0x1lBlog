package top.blogapi.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.blogapi.model.entity.Comment;
import top.blogapi.repository.CommentRepository;
import top.blogapi.service.CommentService;

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

}
