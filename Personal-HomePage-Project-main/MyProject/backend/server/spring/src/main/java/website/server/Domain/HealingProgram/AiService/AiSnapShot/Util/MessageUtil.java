package website.server.Domain.HealingProgram.AiService.AiSnapShot.Util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageUtil {

    /* 질문 리스트 */
    public String[] questionArray = {
            "1번 질문 : 오늘의 기분은 어떠신가요? ",
            "2번 질문 : 오늘 왜 그런 기분이 느껴셨나요? ",
            "3번 질문 : 최근에 가장 기분이 좋았거나, 편안했던 순간은 언제일까요? \n" +
                    " 그리고 그 이유는 무엇인지 상세히 적어볼까요?",
            "4번 질문 : 당신은 무엇을 할때 가장 큰 편안함을 느끼나요? ",
            "5번 질문 : 돌아오는 한주간 당신에게는 원하는 무엇이든 할 수 있는 돈과 시간이 주어집니다.\n" +
                    " 당신은 그것들을 이용해서 무엇을 하고싶습니까? ",
            "6번 질문 : 당신의 삶을 언제나 지켜보고, \n" +
                    " 당신의 마음을 온전히 공감해주는 아주 가까운 친구가 있다고 생각해봅시다.\n" +
                    " 당신은 그 친구에게 어떠한 말을 듣고 싶나요?\n ",
            "7번 질문 : 마지막으로 내일 당신에게는 어떠한 하루가 펼쳐지면 좋을지 간단하게 이야기해볼까요? "};

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
