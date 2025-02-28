package website.server.Domain.HealingProgram.AiService.AiChatBot.Service;

import jakarta.servlet.http.HttpServletRequest;

public interface ChatBotService {

    /**
     * 채팅 메서드
     * @param request 사용자 요청
     * @param message 사용자 메시지
     * @return AI 답변
     */
    String chat(HttpServletRequest request,String message);

    /**
     * 채팅 결과 출력 메서드
     * @param request 사용자 요청
     * @return AI 최종 답변
     */
    String chatResult(HttpServletRequest request);

}
