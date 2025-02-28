package website.server.Domain.HealingProgram.AiService.AiSnapShot.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageUtil {

    /* 질문 리스트 */
    public String[] questionArray = {
            "1번 질문 : 오늘 하루 어떠한 일들이 있었는지 간단하게 이야기 해볼까요? ",
            "2번 질문 : 이 일들 중에서 오늘 하루 가장 기억에 남는 순간은 무엇인가요?",
            "3번 질문 : 가장 기억에 남는 그 순간 당신의 기분 혹은 감정은 어땟나요? ",
            "4번 질문 : 왜 그러한 기분과 감정을 느꼈을까요? ",
            "5번 질문 : 오늘 하루도 고생한 나에게 해주고 싶은 말이 있다면? ",
            "6번 질문 : 내일은 어떤 일정들이 계획되어 있는지 간단하게 이야기 해볼까요? ",
            "7번 질문 : 그러한 일정 가운데 내일 어떠한 하루를 기대하고 있나요?\n"};

    /**
     * 질문 + 답변 7쌍을 통합하여 fullMessage 생성 메서드
     * @param answerArray 질문에 대한 답변 배열
     * @return fullMessage 답변
     */
    public String getFullMessage(String[] answerArray){

        /* 최종 반환 할 full message */
        StringBuilder fullMessage = new StringBuilder();

        for(int i = 0; i < 7; i++){
            fullMessage.append(questionArray[i]).append(answerArray[i]);
        }

        return fullMessage.toString();
    }

    /**
     * 프롬프트 메시지 + full message
     * @param fullMessage 질문 + 답변 7쌍
     * @return 질문 + 답변 + 프롬프트 메시지
     */
    public String addFullMessageAndPromptMessage(String fullMessage){

        String fullRequest = fullMessage
                + "-> 이것은 사용자가 7개의 질문에 각각 답한 것이다. "
                + "이 내용을 보고, 사용자에게 힘이 될 수 있게 위로와 격려의 편지를 존댓말과 평안한 말투로 10~15줄 적어줘 ";

        return fullRequest;
    }

    /**
     * aiResponse에서 불필요한 문자열 정제 작업
     * @param aiResponseToString 정제되기 이전의 aiResponse
     * @return 정제된 aiResponse
     */
    public String aiResponsePurifier(String aiResponseToString){

        /* aiResponse에서 불필요한 문자열 정제 작업 */
        String purifiedAiResponse = aiResponseToString
                .replace("AiResponse[aiResponse=", "")
                .replace("]", "");

        /* 로그 확인 */
        log.info(purifiedAiResponse);

        return purifiedAiResponse;
    }


}
