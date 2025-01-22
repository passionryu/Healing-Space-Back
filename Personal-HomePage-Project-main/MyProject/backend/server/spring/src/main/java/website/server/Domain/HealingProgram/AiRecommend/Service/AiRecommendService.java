package website.server.Domain.HealingProgram.AiRecommend.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.AiRecommend.DTO.Response.AiResponse;

public interface AiRecommendService {

    /**
     * AI 추천 메서드
     * @param request 사용자 요청
     * @param content 사용자 메시지
     * @return AI 추천 메시지
     */
    AiResponse postAiRecommend(HttpServletRequest request, String content);

    /* 추천 메시지 저장 API */

    /* 추천 메시지 리스트 조회 API */

    /* 추천 메시지 상세 조회 API */

    /* 추천 메시지 삭제 API */


}
