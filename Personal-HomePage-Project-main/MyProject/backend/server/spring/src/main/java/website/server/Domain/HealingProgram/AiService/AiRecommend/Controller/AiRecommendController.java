package website.server.Domain.HealingProgram.AiService.AiRecommend.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseDetail;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseList;
import website.server.Domain.HealingProgram.AiService.AiRecommend.Service.AiRecommendService;

import java.util.List;

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

    /**
     * 추천 메시지 저장 API
     * @param aiResponse AI 추천 메시지 + 사용자 고유 번호
     * @return 추천 메시지 저장 성공 메시지
     */
    @Operation(summary = "추천 메시지 저장 API", description = "")
    @PostMapping("/save")
    public ResponseEntity<String> saveAiRecommend(@RequestBody AiResponse aiResponse) {

        /* 추천 메세지 저장 */
        aiRecommendService.saveAiRecommend(aiResponse);

        return ResponseEntity.ok("추천 메시지 저장 성공");
    }

    /**
     * 추천 메시지 리스트 조회 API
     * @param request 사용자 요청
     * @return AI답변 썸네일 리스트
     */
    @Operation(summary = "추천 메시지 리스트 조회 API", description = "")
    @GetMapping("/list")
    public ResponseEntity<List<AiResponseList>> getAiRecommendList(HttpServletRequest request) {

        /* 추천 메시지 리스트 조회 */
        List<AiResponseList> aiRecommendList = aiRecommendService.getAiRecommendList(request);

        return ResponseEntity.ok(aiRecommendList);
    }

    /**
     * 추천 메시지 상세 조회 API
     * @param AiRecommendMessageId 조회하고자 하는 추천 메시지 고유번호
     * @return 추천 메시지 상세 정보
     */
    @Operation(summary = "추천 메시지 상세 조회 API",description = "")
    @GetMapping("/{AiRecommendMessageId}")
    public ResponseEntity<AiResponseDetail> getAiRecommendDetail(@PathVariable("AiRecommendMessageId") Long AiRecommendMessageId) {

        /* 상세 정보 조회 */
        AiResponseDetail aiResponseDetail = aiRecommendService.getAiRecommendDetail(AiRecommendMessageId);

        return ResponseEntity.ok(aiResponseDetail);
    }

    /**
     * 추천 메시지 삭제 API
     * @param AiRecommendMessageId 삭제하고자 하는 추천 메시지 고유번호
     * @return 삭제 완료 메시지
     */
    @Operation(summary = "",description = "")
    @DeleteMapping("/{AiRecommendMessageId}")
    public ResponseEntity<String> deleteAiRecommend(@PathVariable("AiRecommendMessageId") Long AiRecommendMessageId) {

        /* 추천 메시지 삭제 */
        aiRecommendService.deleteAiRecommend(AiRecommendMessageId);

        return ResponseEntity.ok("삭제 완료");
    }




}
