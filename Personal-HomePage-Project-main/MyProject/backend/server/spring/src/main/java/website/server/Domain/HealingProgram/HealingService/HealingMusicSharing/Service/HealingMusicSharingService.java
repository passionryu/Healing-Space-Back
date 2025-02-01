package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostCommentRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicCommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicListResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicResponse;
import java.util.List;

public interface HealingMusicSharingService {

    /**
     * 힐링 뮤직 쉐어링 메서드
     * @param request 사용자 요청
     * @param postRequest 포스트 요청 데이터
     */
    void postHealingMusic(HttpServletRequest request, PostRequest postRequest);

    /**
     * 게시판에서 힐링 뮤직 리스트 조회 메서드
     * @return 힐링 뮤직 리스트
     */
    List<HealingMusicListResponse> getHealingMusicList();

    /**
     * 게시판에서 힐링 뮤직 상세 조회 메서드
     * @param musicId 힐링 뮤직 고유 번호
     * @return 힐링 뮤직 상세 데이터 조회
     */
    HealingMusicResponse getHealingMusic(String musicId);

    /**
     * 힐링 뮤직 좋아요 누르기 메서드
     * @param request 사용자 요청
     * @param musicId 힐링 뮤직 고유 번호
     * @return 좋아요 Total 수
     */
    Integer likeHealingMusic(HttpServletRequest request, Long musicId);

    /**
     * 힐링 뮤직 댓글 달기 메서드
     * @param request 사용자 요청
     * @param postCommentRequest 댓글 데이터
     * @return 댓글 내용 반환
     */
    String postComment(HttpServletRequest request, PostCommentRequest postCommentRequest);

    /**
     * 힐링 뮤직 댓글 조회 메서드
     * @param request 사용자 요청
     * @param musicId 힐링 뮤직 고유 번호
     * @return
     */
    List<HealingMusicCommentResponse> getComment(HttpServletRequest request,Long musicId);

    /**
     * 힐링 뮤직 댓글 삭제 메서드
     * @param request 사용자 요청
     * @param commentId 댓글 고유 번호
     */
    void deleteComment(HttpServletRequest request, Long commentId);

    /**
     * 내가 올린 힐링 뮤직 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 힐링 뮤직 리스트
     */
    List<HealingMusicListResponse> getMyHealingMusicList(HttpServletRequest request);

    /**
     * 내가 올린 힐링 뮤직 상세 조회 메서드
     * @param request 사용자 요청
     * @param musicId 음악 게시물 고유번호
     * @return 힐링 뮤직 게시판 데이터
     */
    HealingMusicResponse getMyHealingMusic(HttpServletRequest request, Long musicId);

    /**
     * 내가 좋아요 누른 힐링 뮤직 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 내가 좋아요 누른 힐링 뮤직 리스트
     */
    List<HealingMusicListResponse> getLikeHealingMusicList(HttpServletRequest request);

    /**
     * 내가 좋아요 누른 힐링 뮤직 상세 조회 메서드
     * @param request 사용자 요청
     * @param musicId 조회하고자 하는 힐링 뮤직 고유 번호
     * @return 힐링 뮤직 게시판 데이터
     */
    HealingMusicResponse getLikeHealingMusic(HttpServletRequest request, Long musicId);
}
