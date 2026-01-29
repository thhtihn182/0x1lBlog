package top.blogapi.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.exception.system_exception.SystemException;
import top.blogapi.model.entity.SiteSetting;
import top.blogapi.repository.SiteSettingRepository;
import top.blogapi.service.SiteSettingService;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class SiteSettingServiceImpl implements SiteSettingService {

    SiteSettingRepository siteSettingRepository;

    @Transactional(readOnly = true)
    @Override
    public List<SiteSetting> getList() {
        return siteSettingRepository.getList();
    }

    @Transactional
    @Override
    public void updateSiteSetting(SiteSetting siteSetting) {
        if(siteSettingRepository.updateSiteSetting(siteSetting) == 0)
            throw SystemException.builder()
                    .message("Thây đổi thiết lập thất bại")
                    .context("siteSettingId", siteSetting.getId())
                    .build();
    }

    @Transactional
    @Override
    public void deleteSettingById(Long id) {
        if(siteSettingRepository.deleteSettingById(id)==0)
            throw SystemException.builder()
                    .message("Xóa thiết lập thất bại")
                    .context("siteSettingId", id)
                    .build();
    }

    @Transactional
    @Override
    public void saveSiteSetting(SiteSetting siteSetting) {
        if(siteSettingRepository.saveSiteSetting(siteSetting) == 0)
            throw SystemException.builder()
                    .message("Cập nhật thiết lập thất bại")
                    .context("siteSettingId", siteSetting.getId())
                    .context("operate", "insert")
                    .build();
    }
}
