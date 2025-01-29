package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Controller;

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
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service.HealingMusicSharingService;

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

        /* 힐링 뮤직 포스팅
            * 1. title
            * 2. videoLink
            * 3. content
        */
        healingMusicSharingService.postHealingMusic(request,postRequest);

        return ResponseEntity.ok("힐링 뮤직 포스팅 성공");
    }

    /* 게시판에서 힐링 뮤직 리스트 조회 API */
    /* 게시판에서 힐링 뮤직 상세 조회 API */
    /* 힐링 뮤직 좋아요 누르기 */

    /* 내가 올린 힐링 뮤직 리스트 조회 API */
    /* 내가 올린 힐링 뮤직 상세 조회 API */

    /* 내가 좋아요 누른 힐링 뮤직 리스트 조회 API */
    /* 내가 좋아요 누른 힐링 뮤직 상세 조회 API */

    /* 힐링 뮤직 댓글 달기 API */
    /* 힐링 뮤직 댓글 조회 API */
    /* 힐링 뮤직 댓글 삭제 API */

}
