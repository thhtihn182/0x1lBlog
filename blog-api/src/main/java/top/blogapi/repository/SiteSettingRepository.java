package top.blogapi.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.blogapi.model.entity.SiteSetting;

import java.util.List;

@Mapper
@Repository
public interface SiteSettingRepository {
    @Select("SELECT * FROM site_setting")
    List<SiteSetting> getList();
}
