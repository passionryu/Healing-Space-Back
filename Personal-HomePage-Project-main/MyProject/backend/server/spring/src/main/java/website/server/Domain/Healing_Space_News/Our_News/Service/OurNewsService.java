package website.server.Domain.Healing_Space_News.Our_News.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostCommentRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Request.PostNewsRequest;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.GetNewsResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.NewsListResponse;
import website.server.Domain.Healing_Space_News.Our_News.DTO.Response.OurNewsCommentResponse;
import java.util.List;

public interface OurNewsService {

    /**
     * 게시글 업로드 메서드
     * @param request|사용자 요청
     * @param postNewsRequest 게시글 데이터
     */
    void postNews(HttpServletRequest request, PostNewsRequest postNewsRequest);

    /**
     * 게시글 삭제 메서드
     * @param ourNewsNumber 삭제하고자 하는 게시글의 고유번호
     */
    void deleteNews(Long ourNewsNumber);

    /**
     * 게시글 리스트 조회 메서드
     * @return 게시글 썸네일 리스트
     */
    List<NewsListResponse> getNewsList();

    /**
     * 게시글 조회 메서드
     * @param ourNewsNumber 조회하고자 하는 게시글의 고유 번호
     * @return 게시글 정보
     */
    GetNewsResponse getNews(Long ourNewsNumber);

    /**
     * 댓글 달기 메서드
     * @param request 사용자 요청
     * @param postCommentRequest 댓글 업로드 요청 데이터
     */
    void postComment(HttpServletRequest request, PostCommentRequest postCommentRequest);

    /**
     * 댓글 조회 메서드
     * @param request 사용자 요청
     * @param ourNewNumber 댓글을 조회하고자 하는 게시글 고유 번호
     * @return 댓글 리스트 반환
     */
    List<OurNewsCommentResponse> getComment(HttpServletRequest request,Long ourNewNumber);

    /**
     * 댓글 삭제 메서드
     * @param request 사용자 요청
     * @param commentId 삭제하고자 하는 댓글의 고유번호
     */
    void deleteComment(HttpServletRequest request, Long commentId);


}
