package top.blogapi.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.blogapi.dto.response.comment.CommentByBlogIdResponse;
import top.blogapi.model.vo.Result;
import top.blogapi.service.impl.orchestration.CommentOrchestrator;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class CommentController {
    CommentOrchestrator commentOrchestrator;


    @GetMapping("/comment-tree")
    public Result<CommentByBlogIdResponse> commentTree(@RequestParam Integer page,
                                                      @RequestParam(defaultValue = "") Long blogId,
                                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize){
        return Result.ok("Yêu cầu thành công!",commentOrchestrator.listCommentByBlogId(pageNum, pageSize, blogId, page));
    }
}
