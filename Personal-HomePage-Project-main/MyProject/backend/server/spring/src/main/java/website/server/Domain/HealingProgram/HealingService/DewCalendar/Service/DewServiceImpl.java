package website.server.Domain.HealingProgram.HealingService.DewCalendar.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response.DiaryResponse;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Util.HealingMessageList;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Util.HealingMusicList;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Util.PromptUtils;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.Util.TextPurificationUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class DewServiceImpl implements DewService{

    private final ChatClient chatClient;

    /**
     * 일기 저장 메서드
     * @param diaryRequest 일기 제목 + 일기 본문
     * @return 일기 데이터에 관한 AI 연산 결과
     */
    @Override
    public DiaryResponse saveDiary(DiaryRequest diaryRequest) {

        String title = diaryRequest.title();
        String diary = diaryRequest.diary();

        /* 일기 감정 분석 */
        String rawEmotion = chatClient.call(PromptUtils.writeDiaryRequest(title,diary));
        String emotion = TextPurificationUtil.emotionPurify(rawEmotion);

        /* 감정에 맞는 힐링 메시지 & 힐링 뮤직 추천 */
        String healingMessage = HealingMessageList.getMessageByEmotion(emotion);
        String healingMusic = HealingMusicList.getMusicByEmotion(emotion);

        // todo : AiResponse 로 바꾸기
        return new DiaryResponse(emotion, healingMessage, healingMusic);
    }
}
