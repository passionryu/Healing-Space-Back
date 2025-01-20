package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.CommentRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.HealingMessageCreateRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.CommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageResponse;
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

    // API connection complete
    /**
     * 힐링 메시지 쉐어링 API
     * @param request 사용자 요청
     * @return 생성 완료 메시지
     */
    @Operation(summary = " 힐링 메시지 쉐어링 API ", description = "")
    @PostMapping("")
    public ResponseEntity<String> create(HttpServletRequest request,
                                         @RequestBody HealingMessageCreateRequest healingMessageCreateRequest){

        /* 힐링 메시지 쉐어링 */
        healingMessageService.create(request,healingMessageCreateRequest);

        return ResponseEntity.ok("힐링 메세지 쉐어링 완료");
    }

    // API connection complete
    /**
     * 힐링 메시지 삭제 API
     * @param request 사용자 요청
     * @return 삭제 완료 메시지
     */
    @Operation(summary = " 힐링 메시지 삭제 API ", description = "")
    @DeleteMapping("/{messageId}")
    public ResponseEntity<String> delete(HttpServletRequest request,
                                         @PathVariable("messageId") Long messageId){

        /* 선택한 힐링 메시지 삭제 */
        healingMessageService.delete(request,messageId);

        return ResponseEntity.ok("힐링 메시지 삭제 완료");
    }

    // API connection complete
    /**
     * 힐링 메시지 게시판에서 힐링 메시지 리스트 조회 API
     * @return 힐링 메시지 리스트 (messageId,title,nickname,createdDate)
     */
    @Operation(summary = " 힐링 메시지 리스트 조회  API ", description = "")
    @GetMapping("/list")
    public ResponseEntity<List<HealingMessageThumbNailResponse>> getHealingMessageThumbNail(){

        /* 힐링 메시지 리스트 조회 */
        return ResponseEntity.ok(healingMessageService.getHealingMessageThumbNail());
    }

    // API connection complete
    /**
     * 힐링 메시지 게시판에서 선택한 힐링 메시지 상세 조회 API
     * @return 게시물 요소(제목,프로필 사진,닉네임,작성일,게시글 사진,본문)
     */
    @Operation(summary = " 힐링 메시지 상세 조회 API ", description = "")
    @GetMapping("/{messageId}")
    public ResponseEntity<HealingMessageResponse> getHealingMessage(@PathVariable("messageId") Long messageId){

        /* 힐링 메시지 상세 조회 */
        return ResponseEntity.ok(healingMessageService.getHealingMessage(messageId));
    }

    // API connection complete
    /**
     * 내가 올린 힐링 메시지 리스트 조회 API
     * @param request 사용자 요청
     * @return 내가 올린 힐링 메시지 리스트
     */
    @Operation(summary = " 내가 올린 힐링 메시지 리스트 조회  API ", description = "")
    @GetMapping("/list/my")
    public ResponseEntity<List<HealingMessageThumbNailResponse>> getMyHealingMessageThumbNail(HttpServletRequest request){

        /* 내가 올린 힐링 메세지 리스트 조회 */
        return ResponseEntity.ok(healingMessageService.getMyHealingMessageThumbNail(request));
    }

    // API connection complete
    /**
     * 내가 올린 힐링 메세지 상세 조회 API
     * @param request 사용자 요청
     * @return 게시물 요소(제목,프로필 사진,닉네임,작성일,좋아요 수,게시글 사진,본문,댓글 리스트)
     */
    @Operation(summary = " 내가 올린 힐링 메시지 상세 조회 API ", description = "")
    @GetMapping("/my/{messageId}")
    public ResponseEntity<HealingMessageResponse> getMyHealingMessage(HttpServletRequest request,
                                                      @PathVariable("messageId") Long messageId){

        /* 내가 올린 힐링 메세지 상세 조회 */
        return ResponseEntity.ok(healingMessageService.getMyHealingMessage(request,messageId));
    }

    // API connection complete
    /**
     * 힐링 메시지 좋아요 누르기
      * @param request 사용자 요청
     * @param messageId 게시글 고유 번호
     * @return 좋아요 누른 수
     */
    @Operation(summary = " 힐링 메시지에 좋아요 누르기 API ", description = "")
    @PostMapping("/like/{messageId}")
    public ResponseEntity<Long> clickLike(HttpServletRequest request,
                                          @PathVariable("messageId") Long messageId){

        /* 해당 게시글에 눌린 좋아요 총합 반환 */
        return ResponseEntity.ok(healingMessageService.clickLike(request,messageId));
    }

    // API connection complete
    /**
     * 좋아요 누른 힐링 메시지 리스트 조회 API
     * @param request 사용자 요청
     * @return 좋아요 누른 힐링 메시지 리스트 반환
     */
    @Operation(summary = " 좋아요 누른 힐링 메시지 리스트 조회 API ", description = "")
    @GetMapping("/like/list")
    public ResponseEntity<List<HealingMessageThumbNailResponse>> getMyLikeMessageList(HttpServletRequest request){

        /* 좋아요 누른 힐링 메시지 리스트 반환 */
        return ResponseEntity.ok(healingMessageService.getMyLikeMessageList(request));
    }

    // API connection complete
    /**
     * 좋아요 누른 힐링 메시지 상세 조회 API
     * @param request 사용자 요청
     * @param messageId 조회하고자 하는 게시글 고유번호
     * @return 조회하고자 하는 게시글 내용
     */
    @Operation(summary = " 좋아요 누른 힐링 메시지 상세 조회  API ", description = "")
    @GetMapping("/like/{messageId}")
    public ResponseEntity<HealingMessageResponse> getMyLikeMessage(HttpServletRequest request,
                                                 @PathVariable("messageId") Long messageId){

        /* 조회하고자 하는 게시글 내용 반환 */
        return ResponseEntity.ok(healingMessageService.getMyLikeHealingMessage(request,messageId));
    }

    // todo : 메시지 아이디를 넣어서 반환값에 바로 댓글이 보이게 해야 함.
    //  일단은 댓글이 달리면 프론트에서 알아서 새로고침을 하게 임시 조취를 취해놓음
    /**
     * 힐링 메시지에 댓글 달기 API
     * @param request 사용자 요청
     * @param commentRequest 댓글 내용
     * @return 댓글 달기 성공 메시지
     */
    @Operation(summary = "힐링 메시지에 댓글 달기 API", description = "")
    @PostMapping("/comment")
    public ResponseEntity<String> postComment(HttpServletRequest request,
                                              @RequestBody CommentRequest commentRequest){

        /* 댓글 달기 */
        healingMessageService.postComment(request,commentRequest);

        return ResponseEntity.ok("댓글 달기 성공");
    }

    /**
     * 힐링 메시지에 댓글 조회 API
     * @param request 사용자 요청
     * @param messageId 댓글을 조회하고자 하는 힐링 메시지 고유번호
     * @return CommentResponse DTO
     * @see CommentResponse 댓글 등록 후 반환 DTO
     */
    @Operation(summary = " 힐링 메시지에 댓글 조회 API")
    @GetMapping("/comment/{messageId}")
    public ResponseEntity<List<CommentResponse>> getComment(HttpServletRequest request,
                                                            @PathVariable("messageId") Long messageId){

        /* 댓글 조회 */
        return ResponseEntity.ok(healingMessageService.getComment(request,messageId));
    }

    /**
     * 힐링 메시지 댓글 삭제 API
     * @param request 사용자 요청
     * @param commentId 삭제 하고자 하는 댓글 고유 번호
     * @return 메시지 삭제 성공 메시지
     */
    @Operation(summary = "힐링 메시지 댓글 삭제 API", description = "")
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(HttpServletRequest request,
                                                @PathVariable("commentId") Long commentId){

        return ResponseEntity.ok("메시지 삭제 성공 ");
    }


    /* 힐링 메시지 수정하기 - 추후 ver 2 업그레이드 */

}
