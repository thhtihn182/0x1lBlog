package top.blogapi.repository;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.blogapi.model.entity.SiteSetting;

import java.util.List;

@Mapper
@Repository
public interface SiteSettingRepository {
    @Select("SELECT * FROM site_setting")
    List<SiteSetting> getList();

    @Update("UPDATE site_setting SET value = #{value} WHERE id = #{id}")
    int updateSiteSetting(SiteSetting siteSetting);

    @Delete("DELETE FROM site_setting WHERE id = #{id}")
    int deleteSettingById(@Param("id") Long id);

    @Insert("INSERT INTO site_setting (name_en, name_vn, value, type) " +
            "VALUES (#{nameEn},#{nameVn},#{value},#{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveSiteSetting(SiteSetting siteSetting);

    @Select("""
        SELECT *
        FROM site_setting
        WHERE type = #{type}
""")
    List<SiteSetting> mp3Setting(int type);
}
