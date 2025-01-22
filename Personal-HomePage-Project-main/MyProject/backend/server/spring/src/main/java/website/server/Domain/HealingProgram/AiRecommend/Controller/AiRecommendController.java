package website.server.Domain.HealingProgram.AiRecommend.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import website.server.Domain.HealingProgram.AiRecommend.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiRecommend.Service.AiRecommendService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/airecommend")
@Tag(name = "AI Recommend", description = "AI Recommend API")
public class AiRecommendController {

    private final AiRecommendService aiRecommendService;

    /**
     * AI 추천 API
     * @param request 사용자 요청
     * @param content 사용자 메시지
     * @return AI 추천 메시지 + 사용자 고유번호
     */
    @Operation(summary = " AI 추천 API ", description = "")
    @PostMapping("")
    public ResponseEntity<AiResponse> postAiRecommend(HttpServletRequest request,
                                                      @RequestBody String content) {
        /* AI 연산값 반환 */
        AiResponse aiResponse = aiRecommendService.postAiRecommend(request,content);

    return ResponseEntity.ok(aiResponse);
    }

    /* 추천 메시지 저장 API */

    /* 추천 메시지 리스트 조회 API */

    /* 추천 메시지 상세 조회 API */

    /* 추천 메시지 삭제 API */


}
