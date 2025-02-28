package website.server.Domain.HealingProgram.AiService.AiChatBot.Service;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.AiService.AiChatBot.Logic.RedisLogic;
import website.server.Domain.HealingProgram.AiService.AiChatBot.Mapper.ChatBotMapper;
import website.server.Global.JWT.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatBotServiceImpl implements ChatBotService {

    private final ChatBotMapper chatBotMapper;
    private final ChatClient chatClient;
    private final JwtService jwtService;
    private final RedisLogic redisLogic;

    /**
     * 채팅 메서드
     * @param request 사용자 요청
     * @param message 사용자 메시지
     * @return AI 답변
     */
    @Override
    public String chat(HttpServletRequest request, String message) {

        /* userNumber 반환 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* Redis DB에서 기존의 대화내용이 있으면 반환 */
        String previousMessage  = redisLogic.getPreviousMessage(userNumber);

        /* 사용자 메시지 + AI 프롬프트 메시징 */
        String promptMessage = redisLogic.makePromptMessage(message, previousMessage);

        /* AI 상담사 챗봇 답변 반환 */
        String aiResponse = chatClient.call(promptMessage);

        /* 유저 메시지,챗봇 답변 Redis에 업로드 */
        redisLogic.saveMessageInRedis(userNumber,message,aiResponse);

        /* 최종적으로 AI 챗봇의 답변을 반환 */
        return aiResponse;
    }

    /**
     * 채팅 결과 출력 메서드
     * @param request 사용자 요청
     * @return AI 최종 답변
     */
    @Override
    public String chatResult(HttpServletRequest request) {

        /* userNumber 반환 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* Redis에서 해당 유저의 모든 대화 기록을 반환 */
        String everyMessages = redisLogic.getAllMessage(userNumber);

        /* 메시지 프롬프팅 */
        String promptMessage = redisLogic.resultPromptMessage(everyMessages);

        /* AI 상담사 챗봇의 Letter(편지) 반환 */
        String letter = chatClient.call(promptMessage);

        /* Redis에 있는 유저의 모든 채팅 데이터 삭제 */
        redisLogic.deleteAllMessages(userNumber);

        /* AI 상담사 챗봇의 Letter(편지) DB에 저장 */
        chatBotMapper.saveLetter(userNumber,letter);

        /* 유저에게 Ai Letter 반환 */
        return letter;
    }
}
