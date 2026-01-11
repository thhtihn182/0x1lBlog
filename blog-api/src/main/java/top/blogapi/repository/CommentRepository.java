package top.blogapi.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.blogapi.dto.internal.CommentTree;
import top.blogapi.model.entity.Comment;

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

    @Select("select * from comment where parent_comment_id is null")
    @Results({
            @Result(property = "published" , column = "is_published"),
            @Result(property = "adminComment" , column = "is_admin_comment"),
            @Result(property = "notice" , column = "is_notice")
    })
    List<Comment> getParentComment();

    @Update("UPDATE comment SET is_published = #{published} WHERE id = #{id}")
    int updateCommentPublishedById(Long id, boolean published);

    @Update("UPDATE comment SET is_notice = #{notice} WHERE id = #{id}")
    int updateCommentNoticeById(Long id, boolean notice);

    @Delete("DELETE FROM comment WHERE id = #{id}")
    int deleteCommentById(Long id);

    @Update("UPDATE comment SET nickname = #{nickname}, email = #{email}, content = #{content}, ip = #{ip} WHERE id =#{id}")
    int updateComment(Long id,String nickname, String email, String content, String ip);

                @Select("""
            
                        with recursive comment_tree as (
                select
                    *,
                    id as thread_root,
                    0 as is_reply
                from comment
                where parent_comment_id is null
                union all
                select
                    c.*,
                    ct.thread_root,
                    1 as is_reple
                from comment c
                inner join comment_tree ct
                on  c.parent_comment_id = ct.id
            )
            select * from comment_tree
        """)
    @Results({
            @Result(property = "published" , column = "is_published"),
            @Result(property = "adminComment" , column = "is_admin_comment"),
            @Result(property = "notice" , column = "is_notice")
    })
    List<CommentTree> commentTreeFlat();
}
