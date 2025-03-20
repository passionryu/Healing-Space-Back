package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostCommentRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicCommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicListResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service.HealingMusicSharingService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/healingmusic")
@Tag(name = "Healing Music ", description = "Healing Music Sharing 서비스 API")
public class HealingMusicSharingController {

    private final HealingMusicSharingService healingMusicSharingService;

    /**
     * 힐링 뮤직 쉐어링 API
     * @param request 사용자 요청
     * @param postRequest 포스트 요청 데이터
     * @return 포스팅 성공 메시지
     */
    @Operation(summary = "힐링 뮤직 쉐어링 API",description = "")
    @PostMapping("")
    public ResponseEntity<String> postHealingMusic(HttpServletRequest request,
                                                   @RequestBody PostRequest postRequest){

        /* 힐링 뮤직 포스팅 */
        healingMusicSharingService.postHealingMusic(request,postRequest);

        return ResponseEntity.ok("힐링 뮤직 포스팅 성공");
    }

    /**
     * 게시판에서 힐링 뮤직 리스트 조회 API
     * @return 힐링 뮤직 레스트
     */
    @Operation(summary = "게시판에서 힐링 뮤직 리스트 조회 API",description = "")
    @GetMapping("/list")
    public ResponseEntity<List<HealingMusicListResponse>> getHealingMusicList(){

        /* 힐링 뮤직 리스트 조회 */
        List<HealingMusicListResponse> healingMusicListResponse = healingMusicSharingService.getHealingMusicList();

        return ResponseEntity.ok(healingMusicListResponse);
    }

    /**
     * 게시판에서 힐링 뮤직 상세 조회 API
     * @param musicId 힐링 뮤직 게시글 고유 번호
     * @return 힐링 뮤직 상세 데이터
     */
    @Operation(summary = "게시판에서 힐링 뮤직 상세 조회 API",description = "")
    @GetMapping("/{musicId}")
    public ResponseEntity<HealingMusicResponse> getHealingMusic(@PathVariable("musicId") String musicId){

        /* 힐링 뮤직 상세 조회 */
        HealingMusicResponse healingMusicResponse = healingMusicSharingService.getHealingMusic(musicId);

        return ResponseEntity.ok(healingMusicResponse);
    }

    /**
     * 힐링 뮤직 좋아요 누르기
     * @param request 사용자 요청
     * @param musicId 힐링 뮤직 게시글 고유 번호
     * @return 좋아요 Total 수
     */
    @Operation(summary = "힐링 뮤직 좋아요 누르기 API ", description = "")
    @PostMapping("/like/{musicId}")
    public ResponseEntity<Integer> likeHealingMusic(HttpServletRequest request,
                                                    @PathVariable("musicId") Long musicId){

        /* 좋아요 누르기 & 좋아요 Total value 반환 */
        Integer likeCount = healingMusicSharingService.likeHealingMusic(request,musicId);

        return ResponseEntity.ok(likeCount);
    }

    /**
     * 힐링 뮤직 댓글 달기 API
     * @param request 사용자 요청
     * @param postCommentRequest 댓글 요청 데이터
     * @return 댓글 내용 반환
     */
    @Operation(summary = "힐링 뮤직 댓글 달기 API", description = "")
    @PostMapping("/comment")
    public ResponseEntity<String> postComment(HttpServletRequest request,
                                              @RequestBody PostCommentRequest postCommentRequest){

        /* 힐링 뮤직 게시글에 댓글 달기 */
        String comment = healingMusicSharingService.postComment(request,postCommentRequest);

        return ResponseEntity.ok(comment);
    }

    /**
     * 힐링 뮤직 댓글 조회 API
     * @param request 사용자 요청
     * @param musicId 음악 게시물 고유 번호
     * @return 댓글 리스트
     */
    @Operation(summary = "힐링 뮤직 댓글 조회 API",description = "")
    @GetMapping("/comment/{musicId}")
    public ResponseEntity<List<HealingMusicCommentResponse>> getComment(HttpServletRequest request,
                                                                        @PathVariable("musicId") Long musicId){

        /* 힐링 뮤직 댓글 리스트 조회 */
        List<HealingMusicCommentResponse> healingMusicCommentResponseList = healingMusicSharingService.getComment(request,musicId);

        return ResponseEntity.ok(healingMusicCommentResponseList);
    }

    /**
     * 힐링 뮤직 댓글 삭제 API
     * @param request 사용자 요청
     * @param commentId 댓글 고유 번호
     * @return 댓글 삭제 완료 메시지
     */
    @Operation(summary = "힐링 뮤직 댓글 삭제 API", description = "")
    @DeleteMapping("/comment/{commentId}")
    public ResponseEntity<String> deleteComment(HttpServletRequest request,
                                                @PathVariable("commentId") Long commentId){

        /* 힐링 뮤직 댓글 삭제 */
        healingMusicSharingService.deleteComment(request,commentId);

        return ResponseEntity.ok(commentId + "번 댓글 삭제 완료");
    }

    //todo : problem 2025.03.20
    /**
     * 내가 올린 힐링 뮤직 리스트 조회 API
     * @param request 사용자 요청
     * @return 힐링 뮤직 리스트
     */
    @Operation(summary = "내가 올린 힐링 뮤직 리스트 조회 API",description = "")
    @GetMapping("/list/my")
    public ResponseEntity<List<HealingMusicListResponse>> getMyHealingMusicList(HttpServletRequest request){
        log.info("method name : getMyHealingMusicList request : {}", request);

        /* 힐링 뮤직 리스트 조회 */
        List<HealingMusicListResponse> healingMusicListResponses = healingMusicSharingService.getMyHealingMusicList(request);

        return ResponseEntity.ok(healingMusicListResponses);
    }

    /**
     * 내가 올린 힐링 뮤직 상세 조회 API
     * @param request 사용자 요청
     * @param musicId 힐링 뮤직 고유 번호
     * @return 힐링 뮤직 게시물
     */
    @Operation(summary = "내가 올린 힐링 뮤직 상세 조회 API", description = "")
    @GetMapping("my/{musicId}")
    public ResponseEntity<HealingMusicResponse> getMyHealingMusic(HttpServletRequest request,
                                                    @PathVariable("musicId") Long musicId){
        /* 요청한 힐링 뮤직 상세 조회 */
        HealingMusicResponse healingMusicResponse = healingMusicSharingService.getMyHealingMusic(request,musicId);

        return ResponseEntity.ok(healingMusicResponse);
    }

    /**
     * 내가 좋아요 누른 힐링 뮤직 리스트 조회 API
     * @param request 사용자 요청
     * @return 좋아요 누른 힐링 뮤직 리스트
     */
    @Operation( summary = "내가 좋아요 누른 힐링 뮤직 리스트 조회 API", description = "")
    @GetMapping("/like/list")
    public ResponseEntity<List<HealingMusicListResponse>> getLikeHealingMusicList(HttpServletRequest request){

        /* 좋아요 누른 힐링 뮤직 리스트 반환 */
        List<HealingMusicListResponse> healingMusicListResponses = healingMusicSharingService.getLikeHealingMusicList(request);

        return ResponseEntity.ok(healingMusicListResponses);
    }

    /**
     * 내가 좋아요 누른 힐링 뮤직 상세 조회 API
     * @param request 사용자 요청
     * @param musicId 조회하고자 하는 힐링 뮤직 고유 번호
     * @return 선택한 힐링 뮤직 게시물
     */
    @Operation(summary = "내가 좋아요 누른 힐링 뮤직 상세 조회 API", description = "")
    @GetMapping("/like/{musicId}")
    public ResponseEntity<HealingMusicResponse> getLikeHealingMusic(HttpServletRequest request,
                                                                    @PathVariable("musicId") Long musicId){

        /* 좋아요 누른 힐링 뮤직 상세 조회 */
        HealingMusicResponse healingMusicResponse = healingMusicSharingService.getLikeHealingMusic(request,musicId);

        return ResponseEntity.ok(healingMusicResponse);
    }

}
