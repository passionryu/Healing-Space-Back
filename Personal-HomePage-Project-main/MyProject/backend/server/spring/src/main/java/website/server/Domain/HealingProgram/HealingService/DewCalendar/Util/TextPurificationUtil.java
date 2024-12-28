package website.server.Domain.HealingProgram.HealingService.DewCalendar.Util;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class TextPurificationUtil {

    public static String emotionPurify(String rawEmotion){

        List<String> emotions = Arrays.asList("기쁨","설렘&사랑","평온","외로움","피곤함","슬픔","화남","좌절");

        // rawEmotion 문자열에서 리스트 내의 감정을 찾아 반환
        for (String emotion : emotions) {
            if (rawEmotion.contains(emotion)) {
                log.info("추출된 감정 : {}",emotion);
                return emotion; // 감정 단어를 반환
            }
        }
        return "감정을 분석하는데 실패했습니다.";
    }

}
