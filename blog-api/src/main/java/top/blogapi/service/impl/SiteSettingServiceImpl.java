package top.blogapi.service.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.exception.business_exception.domain_exception.SiteSettingException;
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
        try{
            int r = siteSettingRepository.updateSiteSetting(siteSetting);
            if(r == 0)
                throw  SiteSettingException.builder()
                        .operationSiteSettingUnsuccessful("update","Cập nhật Site Setting không thành công!",
                                HttpStatus.NO_CONTENT)
                        .build();
        }catch ( DataAccessException e){
            throw SiteSettingException.builder()
                    .dataRetrievalFailed("Lỗi Server !!", e)
                    .build();
        }
    }

    @Transactional
    @Override
    public void deleteSettingById(Long id) {
        try{
            int r = siteSettingRepository.deleteSettingById(id);
            if(r == 0)
                throw  SiteSettingException.builder()
                        .operationSiteSettingUnsuccessful("delete","Xóa Site Setting không thành công!",
                                HttpStatus.NO_CONTENT)
                        .build();
        }catch ( DataAccessException e){
            throw SiteSettingException.builder()
                    .dataRetrievalFailed("Lỗi Server !!", e)
                    .build();
        }
    }

    @Transactional
    @Override
    public void saveSiteSetting(SiteSetting siteSetting) {
        try{
            int r = siteSettingRepository.saveSiteSetting(siteSetting);
            if(r == 0)
                throw  SiteSettingException.builder()
                        .operationSiteSettingUnsuccessful("insert","Tạo mới Site Setting không thành công!",
                                HttpStatus.NO_CONTENT)
                        .build();
        }catch ( DataAccessException e){
            throw SiteSettingException.builder()
                    .dataRetrievalFailed("Lỗi Server !!", e)
                    .build();
        }
    }
}
