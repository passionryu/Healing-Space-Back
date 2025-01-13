package website.server.Domain.HealingProgram.HealingService.DewRecord.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Request.DiaryRequest;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryResponse;
import website.server.Domain.HealingProgram.HealingService.DewRecord.DTO.Response.DiaryThumbnailResponse;
import java.time.LocalDate;
import java.util.List;

public interface DewService {

    /**
     * 일기 AI 연산 메서드
     * @param diaryRequest 일기(제목, 본문)
     * @return 사용자 감정
     */
    AiResponse AiCalculation(DiaryRequest diaryRequest);

    /**
     * 사용자의 감정을 날씨와 매칭 시키는 메서드
     * @param emotion 사용자 감정
     * @return 감정과 매칭된 날씨
     */
    String matchEmotionToWeather(String emotion);

    /**
     * 일기 최종 저장 메서드
     * @param userNumber 사용자 고유 번호
     * @param diaryRequest 일기 제목 + 본문
     * @param aiResponse AI 연산결과
     */
    void saveDiary(Long userNumber,DiaryRequest diaryRequest,AiResponse aiResponse);

    /**
     * 일기 삭제 메서드
     * @param request 사용자 요청
     * @param date 일기 작성 날짜
     */
    void deleteDiary(HttpServletRequest request, LocalDate date);

    /**
     * 일기 썸네일 리스트 반환 메서드
     * @param request 사용자 요청
     * @return 사용자가 작성한 일기의 썸네일 리스트
     */
    List<DiaryThumbnailResponse> getDiaryThumbnails(HttpServletRequest request);

    /**
     * 선택한 날짜의 일기 조회 메서드
     * @param request 사용자 요청
     * @param messageId 선택한 일기의 고유번호
     * @return 일기장 (날짜,제목,본문,감정,힐링 메시지,힐링 뮤직)
     */
    DiaryResponse getDiary(HttpServletRequest request,Long diaryNumber);

}
