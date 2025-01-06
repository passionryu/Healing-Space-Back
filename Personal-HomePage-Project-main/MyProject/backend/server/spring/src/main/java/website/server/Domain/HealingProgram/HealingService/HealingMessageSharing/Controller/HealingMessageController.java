package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.HealingMessageCreateRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageThumbNailResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Service.HealingMessageService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/healingmessage")
@Tag(name = "Healing Message ", description = "Healing Message Sharing 서비스 API")
public class HealingMessageController {

    private final HealingMessageService healingMessageService;

    /**
     * 힐링 메시지 쉐어링 API
     * @param request 사용자 요청
     * @return 생성 완료 메시지
     */
    @Operation(summary = " 힐링 메시지 쉐어링 API ", description = "")
    @PostMapping("")
    public ResponseEntity<String> create(HttpServletRequest request, @RequestBody HealingMessageCreateRequest healingMessageCreateRequest){

        /* 힐링 메시지 쉐어링 */
        healingMessageService.create(request,healingMessageCreateRequest);

        return ResponseEntity.ok("힐링 메세지 쉐어링 완료");
    }

    /**
     * 힐링 메시지 삭제 API
     * @param request 사용자 요청
     * @return 삭제 완료 메시지
     */
    @Operation(summary = " 힐링 메시지 삭제 API ", description = "")
    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> delete(HttpServletRequest request, @PathVariable("messageId") Long messageId){

        /* 선택한 힐링 메시지 삭제 */
        healingMessageService.delete(request,messageId);

        return ResponseEntity.ok("힐링 메시지 삭제 완료");
    }

    /**
     * 힐링 메시지 게시판에서 힐링 메시지 리스트 조회 API
     * @param request 사용자 요청
     * @return 힐링 메시지 리스트
     */
    @Operation(summary = " 힐링 메시지 리스트 조회  API ", description = "")
    @GetMapping("/list")
    public ResponseEntity<List<HealingMessageThumbNailResponse>> getHealingMessageThumbNail(HttpServletRequest request){

        /* 힐링 메시지 리스트 조회 */
        List<HealingMessageThumbNailResponse> healingMessageThumbNailResponses = healingMessageService.getHealingMessageThumbNail();

        return ResponseEntity.ok(healingMessageThumbNailResponses);
    }

    //todo : workign
    /**
     * 힐링 메시지 게시판에서 선택한 힐링 메시지 상세 조회
     * @param request 사용자 요청
     * @return
     */
    @Operation(summary = " 힐링 메시지 상세 조회 API ", description = "")
    @GetMapping("/{messageId}")
    public ResponseEntity<String> getHealingMessage(HttpServletRequest request, @PathVariable("messageId") Long messageId){

        return ResponseEntity.ok("힐링 메시지 상세 조회 성공");
    }

    /**
     * 내가 올린 힐링 메시지 리스트 조회
     * @param request 사용자 요청
     * @return
     */
//    @Operation(summary = " 힐링 메시지 리스트 조회  API ", description = "")
//    @GetMapping("")
//    public ResponseEntity<String> getMyHealingMessageThumbNail(HttpServletRequest request){
//
//        return ResponseEntity.ok("");
//    }

    /**
     * 내가 올린 힐링 메시지 상세 조회
     * @param request 사용자 요청
     * @return
     */
//    @Operation(summary = " 힐링 메시지 상세 조회 API ", description = "")
//    @GetMapping("")
//    public ResponseEntity<String> getMyHealingMessage(HttpServletRequest request){
//
//        return ResponseEntity.ok("");
//    }

    /* 힐링 메시지 좋아요 누르기 */

    /* 좋아요 누른 힐링 메시지 리스트 조회 */

    /* 좋아요 누른 힐링 메시지 상세 조회 */

    /* 힐링 메시지에 댓글 달기 */

    /* 힐링 메시지 수정하기 */

}
