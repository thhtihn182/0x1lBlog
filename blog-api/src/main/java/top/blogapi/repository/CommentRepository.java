package top.blogapi.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.blogapi.entity.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentRepository {

    @Select("<script>" +
                "SELECT c.id, c.nickname, c.email, c.content, c.avatar, c.create_time, c.ip, " +
                "c.is_published, c.is_admin_comment, c.page, c.is_notice, c.blog_id , " +
                "c.parent_comment_id, b.title as blog_title " +
                "FROM comment c LEFT JOIN blog b "+
                "ON c.blog_id = b.id " +
            "<where> " +
                "c.page = #{page} " +
                "<if test=' page == 0 and blogId != null'> " +
                    "AND c.blog_id = #{blogId} " +
                "</if> " +
                "<if test=' parentCommentId != null'> " +
                    "AND c.parent_comment_id = #{parentCommentId} " +
                "</if> " +
                "<if test=' parentCommentId == null'> " +
                    "AND c.parent_comment_id IS NULL " +
                "</if> " +
            "</where> " +
            "</script>")
    @Results({
            @Result(property = "published" , column = "is_published"),
            @Result(property = "adminComment" , column = "is_admin_comment"),
            @Result(property = "notice" , column = "is_notice"),
            @Result(property = "blog.id", column = "blog_id"),
            @Result(property = "blog.title", column = "blog_title"),
    })
    List<Comment> getListByPageAndParentCommentId(Integer page, Long parentCommentId, Long blogId);
}
