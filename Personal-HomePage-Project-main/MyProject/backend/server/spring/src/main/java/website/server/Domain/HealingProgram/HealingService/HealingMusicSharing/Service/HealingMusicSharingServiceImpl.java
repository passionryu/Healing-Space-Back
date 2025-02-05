package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostCommentRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Request.PostRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicCommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicListResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper.HealingMusicSharingMapper;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Util.HealingMusicVideoLinkUtil;
import website.server.Global.JWT.JwtService;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealingMusicSharingServiceImpl implements HealingMusicSharingService{

    private final JwtService jwtService;
    private final HealingMusicSharingMapper healingMusicSharingMapper;
    private final HealingMusicVideoLinkUtil healingMusicVideoLinkUtil;

    /**
     * 힐링 뮤직 쉐어링 메서드
     * @param request 사용자 요청
     * @param postRequest 포스트 요청 데이터
     */
    @Override
    public void postHealingMusic(HttpServletRequest request, PostRequest postRequest) {

        /* 사용자 요청에서 사용자 고유번호 추출 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 유튜브 URL로 썸네일 URL 추출 */
        String thumbnailUrl = healingMusicVideoLinkUtil.extractThumbnailUrl(postRequest.videoLink());

        /* 썸네일 추출 실페 시 예외 처리 */
        if (thumbnailUrl == null)
            throw new RuntimeException("썸네일 추출 실패");

        /* DB에 힐링 뮤직 데이터 최종 저장 */
        healingMusicSharingMapper.HealingMusicSharing(userNumber,
                                                      postRequest.title(),
                                                      postRequest.content(),
                                                      thumbnailUrl,
                                                      postRequest.videoLink());
    }

    /**
     * 게시판에서 힐링 뮤직 리스트 조회 메서드
     * @return 힐링 뮤직 리스트 반환
     */
    @Override
    public List<HealingMusicListResponse> getHealingMusicList() {

        /* DB에서 힐링 뮤직 리스트 조회 */
        return healingMusicSharingMapper.getHealingMusicList();
    }

    /**
     * 게시판에서 힐링 뮤직 상세 조회 메서드
     * @param musicId
     * @return
     */
    @Override
    public HealingMusicResponse getHealingMusic(String musicId) {

        /* 힐링 뮤직 상세 조회 */
        return healingMusicSharingMapper.getHealingMusic(musicId);
    }

    /**
     * 힐링 뮤직 좋아요 누르기 메서드
     * @param request
     * @param musicId
     * @return 해당 게시글에 있는 총 좋아요 수
     */
    @Override
    public Integer likeHealingMusic(HttpServletRequest request, Long musicId) {

        /* 사용자 고유 번호 추출 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 좋아요 클릭 여부 판단 */
        boolean clickStatus =healingMusicSharingMapper.checkAlreadyCliked(userNumber,musicId);
        log.info("clickStatus : {}", clickStatus);

        /*
         * 1. 이미 좋아요를 눌렀으면 , Like count -1
         * 2. 좋아요가 기록이 없으면 , Like count +1
         * */
        if(clickStatus){
            healingMusicSharingMapper.deleteLike(musicId,userNumber);
            log.info("delete like"); }
        else {
            healingMusicSharingMapper.permitLike(musicId, userNumber);
            log.info("add like");
        }

        /* 좋아요 총량 합산 반환*/
        return healingMusicSharingMapper.getLikeCount(musicId);
    }

    /**
     * 힐링 뮤직 댓글 달기 메서드
     * @param request
     * @param postCommentRequest
     * @return
     */
    @Override
    public String postComment(HttpServletRequest request, PostCommentRequest postCommentRequest) {

        /* 사용자 고유 번호 추출 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에 댓글 저장 */
        healingMusicSharingMapper.postComment(postCommentRequest.musicId(),userNumber,postCommentRequest.content());

        return postCommentRequest.content();
    }

    /**
     * 힐링 뮤직 댓글 조회 메서드
     * @param request
     * @param musicId
     * @return
     */
    @Override
    public List<HealingMusicCommentResponse> getComment(HttpServletRequest request, Long musicId) {

        /* 댓글 리스트 조회 */
        return healingMusicSharingMapper.getComment(musicId);
    }

    /**
     * 힐링 뮤직 댓글 삭제 메서드
     * @param request
     * @param commentId
     */
    @Override
    public void deleteComment(HttpServletRequest request, Long commentId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에서 댓글 데이터 삭제 */
        healingMusicSharingMapper.deleteComment(userNumber,commentId);
    }

    /**
     * 내가 올린 힐링 뮤직 리스트 조회 메서도
     * @param request 사용자 요청
     * @return 힐링 뮤직 리스트
     */
    @Override
    public List<HealingMusicListResponse> getMyHealingMusicList(HttpServletRequest request) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에서 힐링 뮤직 리스트 반환 */
        return healingMusicSharingMapper.getMyHealingMusicList(userNumber);
    }

    /**
     * 내가 올린 힐링 뮤직 상세 조회 메서드
     * @param request 사용자 요청
     * @param musicId 음악 게시물 고유번호
     * @return 힐링 뮤직 게시판 상세 데이터
     */
    @Override
    public HealingMusicResponse getMyHealingMusic(HttpServletRequest request, Long musicId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에서 선택한 힐링 뮤직 게시글 상세 데이터 반환 */
        return healingMusicSharingMapper.getMyHealingMusic(userNumber,musicId);
    }

    /**
     * 내가 좋아요 누른 힐링 뮤직 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 내가 좋아요 누른 힐링 뮤직 리스트
     */
    @Override
    public List<HealingMusicListResponse> getLikeHealingMusicList(HttpServletRequest request) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* DB에서 내가 좋아요 누른 힐링 뮤직 리스트 데이터 반환 */
        return healingMusicSharingMapper.getLikeHealingMusicList(userNumber);
    }

    /**
     * 내가 좋아요 누른 힐링 뮤직 상세 조회 메서드
     * @param request 사용자 요청
     * @param musicId 조회하고자 하는 힐링 뮤직 고유 번호
     * @return 유저가 선택한 힐링 뮤직 게시글
     */
    @Override
    public HealingMusicResponse getLikeHealingMusic(HttpServletRequest request, Long musicId) {

        /* DB에서 내가 선택한 힐링 뮤직 게시글 반환 */
        return healingMusicSharingMapper.getLikeHealingMusic(musicId);
    }

}
