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
import top.blogapi.dto.request.comment.CommentUpdateRequest;
import top.blogapi.model.entity.Comment;
import top.blogapi.service.CommentService;
import top.blogapi.util.StringUtils;

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
        }catch (Exception ignored){

        }
        return null;
    }

    public String updateCommentPublishedById(Long id, boolean published){
        commentService.updateCommentPublishedById(id, published);
        return "Cập nhật thành công!!";
    }
    public String updateCommentNoticeById(Long id, boolean notice){
        commentService.updateCommentNoticeById(id, notice);
        return "Cập nhật thành công!!";
    }
    public String deleteCommentById(Long id){
        commentService.deleteCommentById(id);
        return "Xóa thành công!!";
    }

    public String updateComment(CommentUpdateRequest request){
        commentService.updateComment(request);
        return "Cập nhật Comment thành công!!";
    }

}
