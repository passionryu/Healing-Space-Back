package website.server.Domain.HealingProgram.AiService.AiSnapShot.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponseDetail;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponseList;

import java.util.List;

public interface AiSnapShotService {

    /**
     * 사용자 답변 저장 메서드
     * @param request 사용자 요청
     * @param questionNumber 질문 번호
     * @param answer 사용자 답변
     */
    void postAiSnapShot(HttpServletRequest request, int questionNumber,String answer);

    /**
     * 답변 최종 회수 후 AI 연산 메서드
     * @param request 사용자 요청
     * @return 답변 레포트
     */
    String getAiResponse(HttpServletRequest request);

    /**
     * 레포트 저장 메서드
     * @param request 사용자 요청
     * @param aiResponse ai 응답 메시지
     */
    void saveAiReport(HttpServletRequest request,AiResponse aiResponse);

    /**
     * 응답 메시지 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 응답 메시지 리스트 반환
     */
    List<AiResponseList> getAiResponseList(HttpServletRequest request);

    /**
     * 레포트 상세 조회 메서드
     * @param aiResponseNumber 상세 조회하고자 하는 응답 메시지 고유 번호
     * @return 응답 메시지 상세 데이터 반환
     */
    AiResponseDetail getAiResponseDetail(Long aiResponseNumber);

    /* 레포트 삭제 메서드 */

}
