package website.server.Domain.HealingProgram.HealingService.DewCalendar.Service;

import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewCalendar.DTO.Response.AiResponse;

public interface DewService {

    /**
     * 일기 저장 메서드
     * @param diaryRequest 일기(제목, 본문)
     * @return 사용자 감정
     */
    AiResponse saveDiary(DiaryRequest diaryRequest);

    String matchEmotionToWeather(String emotion);

}
