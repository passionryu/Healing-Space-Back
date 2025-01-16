package website.server.Domain.Healing_Space_News.Our_News.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostCommentRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostNewsRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.OurNewsCommentResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.GetNewsResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.NewsListResponse;
import website.server.Domain.Healing_Space_News.Our_News.Service.OurNewsService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ournews")
@Tag(name = "Our News", description = "Our News API")
public class OurNewsController {

    private final OurNewsService ourNewsService;

    ////////////////////
    /* ADMIN FUNCTION */
    ////////////////////

    /**
     * 게시글 업로드 API
     * @param request 사용자 요청
     * @param file 이미지 파일
     * @param title 게시글 제목
     * @param content 게시글 본문
     * @return 성공 메시지
     */
    @Operation(summary = "", description = "")
    @PostMapping("/")
    public ResponseEntity<String> postNews(HttpServletRequest request,
                                           @RequestParam(value = "file", required = false) MultipartFile file, // 이미지 파일 받기
                                           @RequestParam("title") String title,
                                           @RequestParam("content") String content){

        /* DTO 등록 */
        PostNewsRequest postNewsRequest = new PostNewsRequest(title, content, file);

        /* 게시글 업로드 서비스 호출 */
        ourNewsService.postNews(request, postNewsRequest);

        return ResponseEntity.ok("게시글 업로드 완료");
    }

    /**
     * 게시글 삭제 API
     * @param ourNewsNumber 삭제할 게시글의 고유 번호
     * @return 게시글 삭제 성공 메시지
     */
    @Operation(summary = "",description = "")
    @DeleteMapping("/{ourNewsNumber}")
    public ResponseEntity<String> deleteNews(@PathVariable("ourNewsNumber") Long ourNewsNumber){

        /* 게시글 삭제 */
        ourNewsService.deleteNews(ourNewsNumber);

        return ResponseEntity.ok("게시글 삭제 성공");
    }

    ///////////////////
    /* USER FUNCTION */
    ///////////////////

    /**
     * 게시글 리스트 조회 API
     * @return 게시글 썸네일 리스트
     */
    @Operation(summary = "", description = "")
    @GetMapping("/list")
    public ResponseEntity<List<NewsListResponse>> getNewsList(){

        /* 게시글 썸네일 리스트 조회 */
        List<NewsListResponse> newsListResponseList = ourNewsService.getNewsList();

        return ResponseEntity.ok(newsListResponseList);
    }

    // 사진 경로는 반환 성공
    // 하지만 이 경로값을 가지고 프론트에서 사진을 조회할 수 있는지 확인할 것
    /**
     * 게시글 조회
     * @param ourNewsNumber 조회하고자 하는 게시글 고유번호
     * @return 게시글 조회 DTO
     */
    @Operation(summary = "", description = "")
    @GetMapping("/{ourNewsNumber}")
    public ResponseEntity<GetNewsResponse> getNews(@PathVariable("ourNewsNumber") Long ourNewsNumber){

        /* 게시글 조회 */
        GetNewsResponse getNewsResponse =  ourNewsService.getNews(ourNewsNumber);

        return ResponseEntity.ok(getNewsResponse);
    }

    /**
     * 댓글 달기 API
     * @param request 사용자 요청
     * @param postCommentRequest 댓글 업로드 요청 데이터
     * @return 댓글 업로드 성공 메시지
     */
    @Operation(summary = "댓글 달기 API", description = "")
    @PostMapping("/comment")
    public ResponseEntity<String> postComment(HttpServletRequest request,
                                              @RequestBody PostCommentRequest postCommentRequest){

        /* 댓글 달기 */
        ourNewsService.postComment(request,postCommentRequest);

        return ResponseEntity.ok("댓글 업로드 성공");
    }

    /**
     * 댓글 조회 API
     * @param request 사용자 요청
     * @param ourNewsNumber 댓글을 조회하고자 하는 게시글 고유 번호
     * @return 댓글 리스트 반환
     */
    @Operation(summary = "댓글 조회 API",description = "")
    @GetMapping("/comment/{ourNewsNumber}")
    public ResponseEntity<List<OurNewsCommentResponse>> getComment(HttpServletRequest request,
                                                                   @PathVariable("ourNewsNumber") Long ourNewsNumber ){

        /* 댓글 리스트 조회 */
        List<OurNewsCommentResponse> ourNewsCommentResponseList =ourNewsService.getComment(request,ourNewsNumber);

        return ResponseEntity.ok(ourNewsCommentResponseList);
    }


    /**
     * 댓글 삭제 API
     * @param request 사용자 요청
     * @param commentId 삭제하고자 하는 댓글의 고유번호
     * @return 삭제 성공 메시지
     */
    @Operation(summary = "",description = "")
    @DeleteMapping("/ournews/comment/{commentId}")
    public ResponseEntity<String> deleteComment(HttpServletRequest request,
                                                @PathVariable("commentId") Long commentId){

        return null;
    }

    // 게시글 조회 시 조회수 증가 - Ver2 to be continue
    // 게시글 수정 - Ver2 to be continue
    // 게시글 댓글 수정 기능 - Ver2 to be continue
}
