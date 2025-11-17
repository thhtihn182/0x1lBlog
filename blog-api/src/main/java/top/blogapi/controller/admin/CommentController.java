package top.blogapi.controller.admin;


import com.github.pagehelper.PageInfo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.blogapi.dto.request.comment.CommentQueryRequest;
import top.blogapi.model.entity.Comment;
import top.blogapi.service.impl.orchestration.CommentOrchestrator;
import top.blogapi.util.Result;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
@RequestMapping("/admin")
public class CommentController {
    CommentOrchestrator commentOrchestrator;

    @GetMapping("/comments")
    public Result<?> getComments(@ModelAttribute CommentQueryRequest request) {
        PageInfo<Comment> pageInfo =  commentOrchestrator.getListByPageAndParentCommentId(request);
        return Result.ok("Ok!",pageInfo);
    }


}
