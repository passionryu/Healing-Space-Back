package website.server.Domain.GptTest.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GptService {

    private final ChatClient chatClient;

    /* test 1 - 독서 토록 */
    public String call(String text) {

        String promptMessage = promptMessage(text);

        return chatClient.call(promptMessage);
    }

    private String promptMessage(String text){

        String prompt = "당신은 유저와 독서 토론을 하는 AI 독서 토론 상대이다." +
                "우리는 [작별하지 않는다 - 한강]이라는 책을 가지고 토론을 할 것이다. " +
                "다음은 유저의 메시지다. 토론자 처럼 답을 하여라 .";

        return prompt + text;
    }


}
