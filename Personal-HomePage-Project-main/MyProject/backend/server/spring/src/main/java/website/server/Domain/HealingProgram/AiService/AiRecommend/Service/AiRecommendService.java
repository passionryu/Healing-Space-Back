package website.server.Domain.HealingProgram.AiService.AiRecommend.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseDetail;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseList;

import java.util.List;

public interface AiRecommendService {

    /**
     * AI 추천 메서드
     * @param request 사용자 요청
     * @param content 사용자 메시지
     * @return AI 추천 메시지
     */
    AiResponse postAiRecommend(HttpServletRequest request, String content);

    /**
     * 추천 메시지 저장 메서드
      * @param response AI 추천 메시지 + 사용자 고유 번호
     */
    void saveAiRecommend(AiResponse response);

    /**
     * 추천 메시지 리스트 조회 메서드
     * @param request 사용자 요청
     * @return AI 답변 썸네일 리스트
     */
    List<AiResponseList> getAiRecommendList(HttpServletRequest request);

    /**
     * 추천 메시지 상세 조회 메서드
     * @param AiRecommendMessageId 조회하고자 하는 메시지 고유번호
     * @return 상세 정보 반환
     */
    AiResponseDetail getAiRecommendDetail(Long AiRecommendMessageId);

    /**
     * 추천 메시지 삭제 메서드
     * @param AiRecommendMessageId 삭제하고자 하는 추천 메시지 고유번호
     */
    void deleteAiRecommend(Long AiRecommendMessageId);


}
