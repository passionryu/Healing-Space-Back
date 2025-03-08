package website.server.Domain.HealingProgram.AiService.AiChatBot.Controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response.ChatbotListResponse;
import website.server.Domain.HealingProgram.AiService.AiChatBot.Service.ChatBotService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/chatbot")
@Tag(name = "chatbot", description = "chatbot")
public class ChatBotController {

    private final ChatBotService chatBotService;

    /**
     * Chatting API
     * @param request 사용자 요청
     * @param userMessage 사용자 메시지
     * @return AI 상담사의 답변
     */
    @Operation(summary = " Chatting API ", description = "")
    @PostMapping("")
    public ResponseEntity<String> chat(HttpServletRequest request,
                                       @RequestBody String userMessage){

        /* AI 상담사와 채팅 */
        String aiResponse = chatBotService.chat(request, userMessage);
        return ResponseEntity.ok(aiResponse);
    }

    /**
     * Get Chatting Result API
     * @param request 사용자 요청
     * @return AI 상담사의 편지 반환
     */
    @Operation(summary = " Chatting result API ", description = "")
    @PostMapping("/result")
    public ResponseEntity<String> chatResult(HttpServletRequest request){

        /* 기존의 대화 내용을 바탕으로 AI 상담사의 편지 반환 */
        String letter = chatBotService.chatResult(request);
        return ResponseEntity.ok(letter);
    }

    /**
     * 내 챗봇 기록 리스트 조회 API
     * @param request 사용자 요청
     * @return 챗봇 기록 리스트 반환
     */
    @Operation(summary = "내 챗봇 기록 리스트 조회 API", description = "")
    @GetMapping("/list")
    public ResponseEntity<List<ChatbotListResponse>> getList(HttpServletRequest request){

        /* 챗봇 기록 리스트 반환 */
        List<ChatbotListResponse> list = chatBotService.getList(request);

        return ResponseEntity.ok(list);
    }

    /* 내 챗봇 상세 조회 API */

}
