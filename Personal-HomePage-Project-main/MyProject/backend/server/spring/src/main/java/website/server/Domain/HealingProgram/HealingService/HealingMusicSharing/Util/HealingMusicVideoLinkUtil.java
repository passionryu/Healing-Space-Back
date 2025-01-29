package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class HealingMusicVideoLinkUtil {

    public String extractThumbnailUrl(String youtubeUrl) {
        try {
            log.info("youtubeUrl: " + youtubeUrl);
            // URL에서 "v=" 다음의 영상 ID 추출
            String videoId = extractVideoId(youtubeUrl);
            if (videoId == null) {
                return null;
            }
            log.info("videoId : {}", videoId);
            // 중간 화질 썸네일 URL 생성
            String thumbnailUrl = "https://img.youtube.com/vi/" + videoId + "/hqdefault.jpg";

            return thumbnailUrl;
        } catch (Exception e) {
            return null;
        }
    }

    private String extractVideoId(String url) {
        String regex = "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

}
