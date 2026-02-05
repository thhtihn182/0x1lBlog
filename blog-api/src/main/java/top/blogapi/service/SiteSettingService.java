package top.blogapi.service;

import top.blogapi.model.entity.SiteSetting;

import java.util.List;
import java.util.Map;

public interface SiteSettingService {
    List<SiteSetting> getList();

    void updateSiteSetting(SiteSetting siteSetting);

    void deleteSettingById(Long id);

    void saveSiteSetting(SiteSetting siteSetting);

    List<SiteSetting> getMp3Setting(int type);
}
