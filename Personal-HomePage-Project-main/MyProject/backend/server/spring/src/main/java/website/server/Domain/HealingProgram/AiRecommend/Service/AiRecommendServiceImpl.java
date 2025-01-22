package website.server.Domain.HealingProgram.AiRecommend.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.AiRecommend.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiRecommend.Mapper.AiRecommendMapper;
import website.server.Global.JWT.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiRecommendServiceImpl implements AiRecommendService{

    private final ChatClient chatClient;
    private final JwtService jwtService;
    private final AiRecommendMapper aiRecommendMapper;;

    /**
     * AI 추천 메서드
     * @param request 사용자 요청
     * @param content 사용자 메시지
     * @return AI 추천 메시지
     */
    @Override
    public AiResponse postAiRecommend(HttpServletRequest request, String content) {

        /* 사용자 고유번호 반환 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 프롬프트 메시지 작성 */
        String prompt = "너는 AI 상담사다.\n" +
                        "현재 사용자의 스토리를 듣고, 그 사용자의 마음을 분석해서 그 사람에게 현재 어떠한 활동이 어울리며 도움이 될지를 추천해주는 상담사다.\n" +
                        "사용자에게 어떠한 것이 어울리는지 말을 해주고, 그에 대한 이유를 4줄 ~5줄 가량 친절하게 설명해줘라.\n" +
                        "\n" +
                        "사용자의 스토리는 다음과 같다. => ";

        /* AI 연산값 반환 */
        String response = chatClient.call(prompt + content);

        return new AiResponse(userNumber, response);
    }
}
