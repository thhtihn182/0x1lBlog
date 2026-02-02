package top.blogapi.dto.response.comment;


import com.github.pagehelper.PageInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentByBlogIdResponse {

    PageInfo<CommentNode> comments;
    CommentStats commentStats;

    public CommentByBlogIdResponse(PageInfo<CommentNode> comments,Integer totalComments){
        this.comments = comments;
        this.commentStats = new CommentStats();
        this.commentStats.totalComments = totalComments;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentNode{
        Long id;
        String nickname;
        String content;
        String avatar;
        LocalDateTime createTime;
        Boolean adminComment;

        List<CommentNode> replyComment;
    }
    @Data
    public static class CommentStats {
        private Integer totalComments;
        private Integer uniqueCommenters;
    }
}
