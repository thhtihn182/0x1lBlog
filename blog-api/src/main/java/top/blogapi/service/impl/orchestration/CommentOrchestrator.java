package top.blogapi.service.impl.orchestration;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.dto.request.comment.CommentQueryRequest;
import top.blogapi.model.entity.Comment;
import top.blogapi.service.CommentService;

import java.util.List;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CommentOrchestrator {
    CommentService commentService;

    public PageInfo<Comment> getListByPageAndParentCommentId(CommentQueryRequest request) {
        try(Page<Object> page1 = PageHelper.startPage(request.getPageNum(), request.getPageSize(),
                request.getSortBy() + " " + request.getSortOrder())){
            List<Comment> comments = commentService.getListByPageAndParentCommentId(request.getPage(),
                    null,request.getBlogId());
            return new PageInfo<>(comments );
        }catch (Exception e){

        }
        return null;
    }
}
