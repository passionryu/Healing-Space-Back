package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.CommentRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.HealingMessageCreateRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.CommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageThumbNailResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper.HealingMessageMapper;
import website.server.Global.JWT.JwtService;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HealingMessageServiceImpl implements HealingMessageService{

    private final HealingMessageMapper healingMessageMapper;
    private final JwtService jwtService;

    /**
     * 힐링 메시지 쉐어링 메서드
     * @param request 사용자 요청
     * @param healingMessageCreateRequest 힐링 메시지 입력 데이터
     */
    @Override
    public void create(HttpServletRequest request, HealingMessageCreateRequest healingMessageCreateRequest) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 힐링 메시지 게시글 저장 */
        healingMessageMapper.create(userNumber,
                healingMessageCreateRequest.title(),
                healingMessageCreateRequest.imagePath(),
                healingMessageCreateRequest.content());

    }

    /**
     * 힐링 메시지 삭제 메서드
     * @param request 사용자 요청
     * @param messageId 삭제할 힐링 메시지 고유 번호
     */
    @Override
    public void delete(HttpServletRequest request,
                       Long messageId) {

        /* 힐링 메시지 삭제 */
        healingMessageMapper.delete(messageId);

    }

    // Connected
    /**
     * 힐링 메시지 게시판에서 힐링 메시지 리스트 조회
     * @return 힐링 메시지 리스트 조회
     */
    @Override
    public List<HealingMessageThumbNailResponse> getHealingMessageThumbNail() {

        /* 힐링 메시지 게시판에서 힐링 메시지 리스트 조회 */
        return healingMessageMapper.getHealingMessageThumbNail();
    }

    /**
     * 힐링 메시지 게시판에서 선택한 힐링 메시지 상세 조회
     * @param messageId 조회하고자 하는 힐링 메시지 고유 번호
     * @return 게시물 요소(제목,프로필 사진,닉네임,작성일,좋아요 수,게시글 사진,본문,댓글 리스트)
     */
    @Override
    public HealingMessageResponse getHealingMessage(Long messageId) {

        /* 힐링 메세지 게시글 상세 보기 */
        return healingMessageMapper.getHealingMessage(messageId);
    }

    /**
     * 내가 올린 힐링 메시지 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 내가 올린 힐링 메시지 리스트
     */
    @Override
    public List<HealingMessageThumbNailResponse> getMyHealingMessageThumbNail(HttpServletRequest request) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 내가 올린 힐링 메시지 리스트 조회 메서드 */
        return healingMessageMapper.getMyHealingMessageThumbNail(userNumber);
    }

    /**
     * 내가 올린 힐링 메세지 상세 조회 메서드
     * @param request 사용자 요청
     * @param messageId  조회하고자 하는 힐링 메시지 고유 번호
     * @return 게시물 요소(제목,프로필 사진,닉네임,작성일,좋아요 수,게시글 사진,본문,댓글 리스트)
     */
    @Override
    public HealingMessageResponse getMyHealingMessage(HttpServletRequest request,
                                                      Long messageId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 힐링 메세지 게시글 상세 보기 */
        return healingMessageMapper.getMyHealingMessage(userNumber,messageId);
    }

    /**
     * 힐링 메시지 좋아요 누르기
     * @param request 사용자 요청
     * @param messageId 게시글 아이디
     * @return 좋아요 누른 수
     */
    @Override
    public Long clickLike(HttpServletRequest request, Long messageId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 좋아요 클릭 여부 판단 */
        boolean clickStatus = healingMessageMapper.checkAlreadyCliked(messageId,userNumber);

        /*
        * 1. 이미 좋아요를 눌렀으면 , 좋아요 취소
        * 2. 좋아요가 기록이 없으면 , 좋아요 승인
        * */
        if(clickStatus)
            healingMessageMapper.deleteLike(messageId,userNumber);
        else
            healingMessageMapper.permitLike(messageId,userNumber);

        /* 좋아요 총량 합산 반환*/
        return healingMessageMapper.getLikeCount(messageId);
    }

    /**
     * 좋아요 누른 힐링 메시지 리스트 조회 메서드
     * @param request 사용자 요청
     * @return 좋아요 누른 힐링 메시지 리스트 조회
     */
    @Override
    public List<HealingMessageThumbNailResponse> getMyLikeMessageList(HttpServletRequest request) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 좋아요 누른 힐링 메시지 리스트 반환 */
        return healingMessageMapper.getMyLikeMessageList(userNumber);
    }

    /**
     * 좋아요 누른 힐링 메시지 상세 조회
     * @param request 사용자 요청
     * @param messageId 메시지 고유 번호
     * @return 좋아요 누른 힐링 메시지 상세 데이터 반환
     */
    @Override
    public HealingMessageResponse getMyLikeHealingMessage(HttpServletRequest request, Long messageId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 힐링 메세지 게시글 상세 보기 */
        return healingMessageMapper.getMyLikeHealingMessage(userNumber,messageId);
    }

    /**
     * 힐링 메시지에 댓글 달기 메서드
     * @param request 사용자 요청
     * @param commentRequest 댓글 내용
     * @return CommentResponse DTO
     */
    @Override
    public void postComment(HttpServletRequest request, CommentRequest commentRequest) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 댓글 게시 */
        healingMessageMapper.postComment(userNumber,commentRequest.messageId(),commentRequest.comment());
    }

    /**
     * 힐링 메시지에서  댓글 조회 메서드
     * @param request 사용자 요청
     * @param messageId 댓글을 조회하고자 하는 힐링 메시지 고유 번호
     * @return CommentResponse DTO
     * @see CommentResponse
     */
    @Override
    public List<CommentResponse> getComment(HttpServletRequest request, Long messageId) {

        List<CommentResponse> commentResponsesList = healingMessageMapper.getComment(messageId);

        return commentResponsesList;
    }

    /**
     * 힐링 메시지 댓글 삭제 메서드
     * @param request 사용자 요청
     * @param commentId 삭제하고자 하는 댓글의 고유번호
     */
    @Override
    public void deleteCommentId(HttpServletRequest request, Long commentId) {

        /* 사용자 고유 번호 조회 */
        Long userNumber = jwtService.extractUserNumberFromRequest(request);

        /* 힐링 메시지 댓글 삭제 */
        healingMessageMapper.deleteComment(userNumber,commentId);
    }

}
