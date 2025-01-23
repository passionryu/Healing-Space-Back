package website.server.Domain.HealingProgram.AiService.AiSnapShot.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Request.AiRequest;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponse;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponseList;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.Service.AiSnapShotService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/aisnapshot")
@Tag(name = "AI SnapShot", description = "AI SnapShot API")
public class AiSnapShotController {

    private final AiSnapShotService aiSnapShotService;

    /**
     * 사용자 답변을 Redis에 임시 저장하는 API
     * @param request 사용자 요청
     * @param aiRequest 질문 번호 + 답변 내용
     * @return Redis에 답변 저장 완료 메시지
     */
    @Operation(summary = "사용자 답변 저장 API",description = "")
    @PostMapping("")
    public ResponseEntity<String> postAiSnapShot(HttpServletRequest request,
                                                 @RequestBody AiRequest aiRequest) {

        /* 유저 번호,질문 번호,사용자 답변을 Redis에 저장 */
        aiSnapShotService.postAiSnapShot(request, aiRequest.questionNumber(), aiRequest.answer());

        /* 현재 저장한 답변이 몇번 답변인지 조회 */
        return ResponseEntity.ok(aiRequest.questionNumber() +"번 메시지 Redis에 저장 완료");
    }

    /**
     * Redis에서 사용자 답변 전원 회수 후 AI 작업 API
     * @param request 사용자 요청
     * @return AI 응답 메시지 반환
     */
    @Operation(summary = "답변 최종 회수 후 AI 연산 API",description = "")
    @PostMapping("/report")
    public ResponseEntity<String> getAiReport(HttpServletRequest request){

        /* 답변 최종 회수 및 AI 연산 */
        String aiResponse = aiSnapShotService.getAiResponse(request);

        return ResponseEntity.ok(aiResponse);
    }

    /**
     * ai 응답 메시지 저장 API
     * @param request 사용자 요청
     * @param aiResponse ai 응답 메시지
     * @return 저장 완료 메시지
     */
    @Operation(summary = "ai 응답 메시지 저장 API",description = "")
    @PostMapping("/report/save")
    public ResponseEntity<String> saveAiReport(HttpServletRequest request,
                                               @RequestBody AiResponse aiResponse){

        /* ai 응답 메시지 저장 */
        aiSnapShotService.saveAiReport(request, aiResponse);

        return ResponseEntity.ok("저장 완료");
    }

    /**
     * 응답 메시지 리스트 조회 API
     * @param request 사용자 요청
     * @return 응답 메시지 리스트
     */
    @Operation(summary = "",description = "")
    @GetMapping("/list")
    public ResponseEntity<List<AiResponseList>> getAiResponseList(HttpServletRequest request){

        /* 응답 메시지 리스트 반환 */
        List<AiResponseList> aiResponseList = aiSnapShotService.getAiResponseList(request);

        return ResponseEntity.ok(aiResponseList);
    }

    /* 레포트 상세 조회 API */

    /* 레포트 삭제 API */

}
