package top.blogapi.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.comment.CommentUpdateRequest;
import top.blogapi.dto.response.comment.CommentByBlogIdResponse;
import top.blogapi.exception.business_exception.BusinessException;
import top.blogapi.exception.system_exception.SystemException;
import top.blogapi.mapper.CommentMapper;
import top.blogapi.model.entity.Comment;
import top.blogapi.model.vo.CommentTree;
import top.blogapi.repository.CommentRepository;
import top.blogapi.service.CommentService;
import top.blogapi.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@Slf4j
public class CommentServiceImpl implements CommentService {
    CommentRepository commentRepository;
    CommentMapper commentMapper;

    @Override
    @Transactional(readOnly = true)
    public List<Comment> getListByPageAndParentCommentId(Integer page, Long parentCommentId, Long blogId) {
        List<Comment> comments = commentRepository.getListByPageAndParentCommentId(page, parentCommentId, blogId);

        for (Comment c: comments) {
            c.setReplyComments(commentRepository.getListByPageAndParentCommentId(page, c.getId(), blogId));
        }
        return comments;
    }

    private List<Long> extractPostIds(List<Comment> comments) {
        List<Long> commentIdList = new ArrayList<>();
        for (Comment c: comments)
            commentIdList.add(c.getId());
        return commentIdList;
    }
    @Transactional
    @Override
    public void updateCommentPublishedById(Long id, boolean published) {
         if(commentRepository.updateCommentPublishedById(id,published)==0)
                throw SystemException.builder()
                        .message("Thao tác thất bại!!")
                        .operate("update")
                        .build();

    }
    @Transactional
    @Override
    public void updateCommentNoticeById(Long id, boolean notice) {
        if(commentRepository.updateCommentNoticeById(id, notice)==0)
            throw SystemException.builder()
                    .message("Thao tác thất bại!!")
                    .operate("update")
                    .build();
    }
    @Transactional
    @Override
    public void deleteCommentById(Long id) {
        if(commentRepository.deleteCommentById(id) == 0)
            throw SystemException.builder()
                    .message("Xóa comment thất baại!!")
                    .context("commentId",id)
                    .build();
    }
    @Transactional
    @Override
    public void updateComment(CommentUpdateRequest request) {
        if(StringUtils.isEmpty(request.getContent(), request.getIp(), request.getEmail(), request.getNickname()))
            throw BusinessException.builder()
                    .message("Thông tin không hợp lệ")
                    .build();
        if(commentRepository.updateComment(request.getId(), request.getNickname(),
                request.getEmail(), request.getContent(), request.getIp())==0)
            throw SystemException.builder()
                    .message("Chỉnh sửa comment thất baại!!")
                    .context("commentId",request.getId())
                    .build();
    }

    @SuppressWarnings("resource")
    @Transactional(readOnly = true)
    public PageInfo<CommentByBlogIdResponse.CommentNode> commentRootTrees (int pageNum, int pageSize,Long blogId, Integer page){
        PageHelper.startPage(pageNum, pageSize, "id desc");
        List<CommentTree> commentRootTrees = commentRepository.findRootComments(blogId,page);
        return new PageInfo<>(commentRootTrees).convert(commentMapper::toCommentNode);
    }

    @Transactional(readOnly = true)
    public Map<Long, List<CommentByBlogIdResponse.CommentNode>> commentChildTrees(List<Long> commentRootIds){
        List<CommentTree> commentChildTrees = commentRepository.findRepliesByRootIds(commentRootIds);

        Map<Long, List<CommentByBlogIdResponse.CommentNode>> map = new HashMap<>();
        for(int i = commentRootIds.size() ; i < commentChildTrees.size() ;i++)
            map.computeIfAbsent(commentChildTrees.get(i).getThreadRoot(),
                    key -> new LinkedList<>())
                    .add(commentMapper.toCommentNode(commentChildTrees.get(i)));
        return map;
    }
}
