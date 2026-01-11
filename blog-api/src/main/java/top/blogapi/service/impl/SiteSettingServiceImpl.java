package top.blogapi.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.model.entity.SiteSetting;
import top.blogapi.repository.SiteSettingRepository;
import top.blogapi.service.SiteSettingService;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class SiteSettingServiceImpl implements SiteSettingService {

    SiteSettingRepository siteSettingRepository;

    @Override
    public List<SiteSetting> getList() {
        return siteSettingRepository.getList();
    }
}
