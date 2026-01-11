package top.blogapi.controller;

import com.github.pagehelper.PageHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.blogapi.dto.internal.CommentTree;
import top.blogapi.model.entity.Blog;
import top.blogapi.model.vo.BlogIdAndTitle;
import top.blogapi.model.vo.Result;
import top.blogapi.repository.CommentRepository;
import top.blogapi.service.impl.orchestration.BlogOrchestrator;
import top.blogapi.service.impl.orchestration.SiteSettingOrchestrator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true,level = AccessLevel.PRIVATE)
public class IndexController {
    BlogOrchestrator blogOrchestrator;
    SiteSettingOrchestrator siteSettingOrchestrator;
    CommentRepository commentRepository;

    @GetMapping("/site")
    public Result<?> site(){
        Map<String, Object> map = siteSettingOrchestrator.getSiteInfo();
        List<BlogIdAndTitle> newBLogList = blogOrchestrator.getIdAndTitleListByIsPublishedAndIsRecommend();
        map.put("newBlogList", newBLogList);
        return Result.ok("Yêu cầu thành công !!", map);
    }
    @GetMapping("/comment-tree")
    public  Map<Long, CommentTree> commentTree(){
        List<CommentTree> l = commentRepository.commentTreeFlat();
        Map<Long,List<CommentTree>> childMap = new HashMap<>();
        Map<Long, CommentTree> parentMap = new HashMap<>();
        for(CommentTree c : l){
            if(c.getParentCommentId()==null)
                parentMap.put(c.getId(),c);
            else {
                childMap.computeIfAbsent(c.getThreadRoot(),k -> new ArrayList<CommentTree>()).add(c);
            }
        }
        for (Long key: parentMap.keySet()){
            parentMap.get(key).setReplyComments(childMap.get(key))   ;
        }
        return parentMap;
    }
}