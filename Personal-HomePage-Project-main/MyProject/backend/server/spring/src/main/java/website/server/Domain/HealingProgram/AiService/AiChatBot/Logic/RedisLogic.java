package website.server.Domain.HealingProgram.AiService.AiChatBot.Logic;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisLogic {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 레디스에서 기존 메시지 반환 메서드
     * @param userNumber 사용자 고유 번호
     * @return Redis에 춝적된 기존 메시지 반환
     */
    public String getPreviousMessage(Long userNumber){

        /* 사용자 고유번호를 이용한 redis key 생성*/
        String key = userNumber + ":chat";

        /* 리스트에서 마지막 6개의 메시지를 반환 */
        List<String> messages = redisTemplate.opsForList().range(key, 0, 6);

        String everyMessage = String.join(" ", messages);
        log.info("every Messgae : {}",everyMessage);

        /* 여러개의 메시지를 하나의 문자열로 결합하여 반환 */
        return everyMessage;
    }

    /**
     * 메시지 프롬프팅 메서드
     * @param message 사용자의 이번 메시지
     * @param previousMessage 사용자의 기존 메시지
     * @return 프롬프트 메시지
     */
    public String makePromptMessage(String message,String previousMessage){

        return "당신은 AI 상담사다. 당신이 이전에 사용자와 나눈 내용은 다음과 같다"
                + previousMessage
                + "이를 참고하여 사용자의 다음 메시지를 보고 적절히 답변하라."
                + message;
    }

    /**
     * Redis에서 사용자와 챗봇의 메시지 저장
     * @param id 사용자 고유 번호
     * @param userMessage 사용자 메시지
     * @param aiResponse AI 챗봇의 답변
     */
    public void saveMessageInRedis(Long id,String userMessage ,String aiResponse){
        // Redis에 사용자와 챗봇의 메시지 저장
        saveMessage(id, userMessage, "user");
        saveMessage(id, aiResponse, "chatbot");
    }

    /**
     * Redis에 메시지 저장 형식 정의 메서드
     * @param id 사용자 고유 번호
     * @param message 사용자 메시지
     * @param sender 전송자
     */
    public void saveMessage(Long id, String message, String sender) {
        String key = id + ":chat" ;
        String taggedMessage = sender + ": " + message;
        redisTemplate.opsForList().rightPush(key, taggedMessage);
    }

    /**
     * 레디스에서 모든 메시지 반환 메서드
     * @param userNumber 사용자 고유 번호
     * @return 모든 채팅 메시지
     */
    public String getAllMessage(Long userNumber){

        String key = userNumber + ":chat";
        List<String> messages = redisTemplate.opsForList().range(key, 0, -1);

        /* 메시지를 하나의 문자열로 결합하여 반환 */
        return String.join(" ", messages);
    }

    /**
     * AI 추천 메서드에서의 프롬프트 메시지
     * @param everyMessages 유저와 챗봇의 모든 메시지
     * @return 프롬프트 메시지
     */
    public String resultPromptMessage(String everyMessages){

        return "다음 AI 상담사이다."
                + "다음은 유저와 너(AI 상담사)가 대화한 채팅 내용이다."
                + "다음 모든 대화 내용을 보고, 따뜻한 말투로 10~15줄 가량 공감과 격려의 편지를 써줘 "
                + everyMessages;
    }

    /**
     * 레디스에서 모든 메시지 삭제
     * @param userNumber 사용자 고유 번호
     */
    public void deleteAllMessages(Long userNumber) {
        String key = userNumber + ":chat";
        redisTemplate.delete(key);
        log.info("Redis에서 메시지 삭제 완료: {}", key);
    }

}
