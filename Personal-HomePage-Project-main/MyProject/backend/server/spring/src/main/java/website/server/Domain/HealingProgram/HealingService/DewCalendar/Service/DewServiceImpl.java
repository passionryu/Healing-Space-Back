package website.server.Domain.HealingProgram.HealingService.DewCalendar.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.FullDiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Entity.Diary;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Mapper.DewMapper;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Util.*;
import website.server.Global.JWT.JwtService;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class DewServiceImpl implements DewService{

    private final ChatClient chatClient;
    private final DewMapper dewMapper;
    private final JwtService jwtService;

    /**
     * 일기 저장 메서드
     * @param diaryRequest 일기 제목 + 일기 본문
     * @return AI 연산 결과(감정,날씨,힐링 메시지,힐링 뮤직)
     */
    @Override
    public AiResponse AiCalculation(DiaryRequest diaryRequest) {

        /* 일기 감정 분석 */
        String rawEmotion = chatClient.call(PromptUtils.writeDiaryRequest(diaryRequest.title(),diaryRequest.diary()));
        String emotion = TextPurificationUtil.emotionPurify(rawEmotion);

        /* 감정에 맞는 힐링 메시지 & 힐링 뮤직 추천 */
        String healingMessage = HealingMessageList.getMessageByEmotion(emotion);
        String healingMusic = HealingMusicList.getMusicByEmotion(emotion);

        /* 사용자 감정을 날씨에 매칭 */
        String weather = matchEmotionToWeather(emotion);

        return new AiResponse(emotion,weather,healingMessage, healingMusic);
    }

    /**
     * 일기-날씨 매칭 메서드
     * @param emotion 일기를 통해 추출된 사용자의 감정
     * @return 날씨
     */
    @Override
    public String matchEmotionToWeather(String emotion) {

        return switch (emotion.toLowerCase()) {
            case "기쁨" -> "맑음";
            case "설렘&사랑" -> "봄 비";
            case "평온" -> "노을";
            case "외로움" -> "구름";
            case "슬픔" -> "비";
            case "화남" -> "천둥";
            default -> throw new IllegalArgumentException("Unknown emotion: " + emotion);
        };

    }

    /**
     * 일기 최종 저장 메서드
     * @param userNumber 사용자 고유 번호
     * @param diaryRequest 일기 제목 + 본문
     * @param aiResponse AI 연산결과
     */
    @Override
    public void saveDiary(Long userNumber, DiaryRequest diaryRequest, AiResponse aiResponse) {

        FullDiaryRequest fullDiaryRequest = new FullDiaryRequest(
                null,
                userNumber,
                diaryRequest.title(),
                diaryRequest.diary(),
                aiResponse.emotion(),
                aiResponse.weather(),
                LocalDate.now(),
                aiResponse.healingMessage(),
                aiResponse.healingMusic()
        );

        Diary diary = fullDiaryRequest.toEntity();

        dewMapper.saveDiary(diary);

    }

    /**
     * 일기 삭제 메서드
     * @param request 사용자 요청
     * @param date 일기 작성 날짜
     */
    @Override
    public void deleteDiary(HttpServletRequest request, LocalDate date) {

        /* 사용자 고유번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 다이어리 삭제 */
        dewMapper.deleteDiary(userNumber,date);

    }

}
