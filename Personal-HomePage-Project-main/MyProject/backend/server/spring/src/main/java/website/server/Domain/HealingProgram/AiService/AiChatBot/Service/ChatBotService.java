package website.server.Domain.HealingProgram.AiService.AiChatBot.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response.ChatBotDetaillResponse;
import website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response.ChatbotListResponse;

import java.util.List;

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

    /**
     * 내 챗봇 기록 리스트 반환 메서드
     * @param request 사용자 요청
     * @return 챗봇 기록 리스트 반환
     */
    List<ChatbotListResponse> getList(HttpServletRequest request);

    /**
     * 내 채팅 결과 기록 상세 조회 메서드
     * @param chat_id 채팅 ID
     * @return 채팅 Letter 상세 조회
     */
    ChatBotDetaillResponse getChatDetail(Long chat_id);


}
