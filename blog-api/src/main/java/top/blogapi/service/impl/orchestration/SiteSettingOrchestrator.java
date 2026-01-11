package top.blogapi.service.impl.orchestration;


import com.alibaba.fastjson2.JSON;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.blogapi.model.entity.SiteSetting;
import top.blogapi.model.vo.Badge;
import top.blogapi.model.vo.Copyright;
import top.blogapi.model.vo.Favorite;
import top.blogapi.model.vo.Introduction;
import top.blogapi.service.SiteSettingService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class SiteSettingOrchestrator {
    // Constants cho các loại setting
    private static final int TYPE_SITE_INFO = 1;
    private static final int TYPE_BADGE = 2;
    private static final int TYPE_INTRODUCTION = 3;

    SiteSettingService siteSettingService;

   public Map<String, List<SiteSetting>> getList(){
       return siteSettingService.getList().stream()
               .collect(Collectors.groupingBy(siteSetting -> "type" + siteSetting.getType()));
   }

   public Map<String, Object> getSiteInfo(){
       List<SiteSetting> siteSettings = siteSettingService.getList();

       // Phân nhóm theo type
       Map<Integer, List<SiteSetting>> groupedByType = siteSettings.stream()
               .collect(Collectors.groupingBy(SiteSetting::getType));

       // Xử lý từng nhóm
       Map<String, Object> siteInfoMap = processSiteInfo(groupedByType.getOrDefault(TYPE_SITE_INFO, List.of()));
       List<Badge> badges = processBadges(groupedByType.getOrDefault(TYPE_BADGE, List.of()));
       Introduction introduction = processIntroduction(groupedByType.getOrDefault(TYPE_INTRODUCTION, List.of()));
       Map<String, Object> map = new HashMap<>();
       map.put("siteInfo", siteInfoMap);
       map.put("badges", badges);
       map.put("introduction", introduction);
       return map;
   }


    /// Xử lý thông tin site (type 1)
    private Map<String, Object> processSiteInfo(List<SiteSetting> siteInfos){
        Map<String, Object> result = new HashMap<>();
        for(SiteSetting info : siteInfos){
            if("copyright".equals(info.getNameEn()))
                result.put(info.getNameEn(), JSON.parseObject(info.getValue(), Copyright.class));
            else
                result.put(info.getNameEn(), info.getValue());
        }
        return result;
    }

    /// Xử lý Badge (type 2)
    private List<Badge> processBadges(List<SiteSetting> badgeSettings){
        return badgeSettings.stream()
                .map(setting -> JSON.parseObject(setting.getValue(), Badge.class))
                .collect(Collectors.toList());
    }

    /// Xử lý thông tin giới thiệu (type 3)
    private Introduction processIntroduction(List<SiteSetting> introSettings){
        Introduction introduction = new Introduction();
        List<Favorite> favorites = new ArrayList<>();
        List<String> rollTexts = new ArrayList<>();
        for (SiteSetting info : introSettings) {
            processIntroField(info, introduction, favorites, rollTexts);
        }
        introduction.setFavorites(favorites);
        introduction.setRollText(rollTexts);
        return introduction;
    }

    /// Xử lý từ Field trong Introduction
    private void processIntroField(SiteSetting siteSetting, Introduction intro,
                                   List<Favorite> favorites, List<String> rollTexts){
        String nameEn = siteSetting.getNameEn();
        String value = siteSetting.getValue();
        switch (nameEn){
            case "avatar" -> intro.setAvatar(value);
            case "name" -> intro.setName(value);
            case "github" -> intro.setGithub(value);
            case "email" -> intro.setEmail(value);
            case "facebook" -> intro.setFacebook(value);
            case "leetCode" -> intro.setLeetCode(value);
            case "instagram" -> intro.setInstagram(value);
            case "favorite" -> favorites.add(JSON.parseObject(value, Favorite.class));
            case "rollText" -> rollTexts.addAll(extractRollTexts(value));
        }
    }

    /// Trích xuất Roll Texts
    private List<String> extractRollTexts(String value){
        Pattern pattern = Pattern.compile("\"(.*?)\""); // Bắt pattern dạng 'text?'
        Matcher matcher = pattern.matcher(value);
        List<String> texts = new ArrayList<>();
        while(matcher.find())
            texts.add(matcher.group(1)); // lấy từng nhóm '()'
        return texts;
    }
}
