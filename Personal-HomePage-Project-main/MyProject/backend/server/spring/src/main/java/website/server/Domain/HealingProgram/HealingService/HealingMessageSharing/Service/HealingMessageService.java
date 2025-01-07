package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Service;

import jakarta.servlet.http.HttpServletRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Request.HealingMessageCreateRequest;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageThumbNailResponse;
import java.util.List;

public interface HealingMessageService {

    /**
     * 힐링 메세지 쉐어링 메서드
     * @param request 사용자 요청
     * @param healingMessageCreateRequest 힐링 메시지 입력 데이터
     */
    void create(HttpServletRequest request, HealingMessageCreateRequest healingMessageCreateRequest);

    /**
     * 힐링 메세지 삭제 메서드
     * @param request 사용자 요청
     * @param messageId 삭제할 힐링 메시지 고유 번호
     */
    void delete(HttpServletRequest request, Long messageId);

    /**
     * 힐링 메시지 게시판에서 힐링 메시지 리스트 조회
     * @return 힐링 메시지 썸네일 리스트
     */
    List<HealingMessageThumbNailResponse> getHealingMessageThumbNail();

    /**
     * 힐링 메시지 게시판에서 선택한 힐링 메시지 상세 조회
     * @param messageId 조회하고자 하는 힐링 메시지 고유 번호
     * @return 힐링 메시지 상세 정보
     */
    HealingMessageResponse getHealingMessage(Long messageId);

    /* 내가 올린 힐링 메시지 리스트 */

    /* 내가 올린 힐링 메시지 상세 조회 */

    /* 힐링 메시지 좋아요 누르기 */

    /* 좋아요 누른 힐링 메시지 리스트 조회 */

    /* 좋아요 누른 힐링 메시지 상세 조회 */

    /* 힐링 메시지에 댓글 달기 */

    /* 힐링 메시지 수정하기 */
}

