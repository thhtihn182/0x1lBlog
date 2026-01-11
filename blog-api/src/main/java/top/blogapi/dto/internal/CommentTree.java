package top.blogapi.dto.internal;

import lombok.Getter;
import lombok.Setter;
import top.blogapi.model.entity.Comment;
import top.blogapi.model.vo.BlogIdAndTitle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CommentTree {
    Long id;
    String nickname; // Biệt danh (tên hiển thị của người bình luận)
    String email; // Địa chỉ email
    String content; // Nội dung bình luận
    String avatar; // Ảnh đại diện (đường dẫn ảnh)
    Date createTime; // Thời gian bình luận
    String ip; // Địa chỉ IP của người bình luận
    boolean published; // Công khai hoặc đưa vào thùng rác
    boolean adminComment; // Bình luận của quản trị viên (chủ blog)
    Integer page; // 0: bài viết thông thường, 1: trang "Giới thiệu về tôi"
    boolean notice; // Có nhận thông báo qua email hay không

    Long blogId; // Bài viết mà bình luận này thuộc về
    Long parentCommentId; // Bình luận cha (nếu là trả lời)
    Long threadRoot;
    Long isReply;
    List<CommentTree> replyComments = new ArrayList<>(); // Danh sách các bình luận trả lời bình luận này

    @Override
    public String toString() {
        return "CommentTree{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", createTime=" + createTime +
                ", ip='" + ip + '\'' +
                ", published=" + published +
                ", adminComment=" + adminComment +
                ", page=" + page +
                ", notice=" + notice +
                ", blogId=" + blogId +
                ", parentCommentId=" + parentCommentId +
                ", threadRoot=" + threadRoot +
                ", isReply=" + isReply +
                ", replyComments=" + replyComments +
                '}';
    }
}
