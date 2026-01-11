package top.blogapi.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.blogapi.model.entity.Tag;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface TagRepository {
    @Select("SELECT t.id, t.name, t.color FROM tag t ORDER BY id DESC ")
    List<Tag> getTagList();

    @Insert("INSERT INTO tag (name, color) VALUES (#{name}, #{color})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveTag(Tag tag);

    @Select("SELECT t.id, t.name, t.color FROM tag t WHERE id = #{id}")
    Optional<Tag> getTagById(@Param("id") Long id);

    @Select("SELECT t.id, t.name, t.color FROM tag t WHERE t.name LIKE #{name}")
    Optional<Tag> getTagByName(String name);

    @Select("SELECT * FROM tag WHERE name = #{name}")
    Optional<Tag> tagExist(String name);

    @Delete("DELETE FROM tag WHERE id = #{tagId}")
    int deleteTagById(Long tagId);

    @Update("UPDATE tag SET name = #{name}, color = #{color} WHERE id = #{id}")
    int updateTag(String name, String color, Long id);

    @Select("SELECT t.id, t.name, t.color " +
            "FROM blog_tag bt " +
            "JOIN tag t ON bt.tag_id = t.id " +
            "WHERE bt.blog_id = #{blogId}")
    List<Tag> getTagListByBlogId(@Param("blogId") Long blogId);
}
