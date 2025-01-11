package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response.HealingMessageThumbNailResponse;

import java.util.List;

@Mapper
public interface HealingMessageMapper {

    /**
     * 힐링 메세지 쉐어링
     * @param userNumber 사용자 고유 번호
     * @param title 힐링 메시지 제목
     * @param imagePath 힐링 메시지 게시물의 사진
     * @param content 힐링 메시지 본문
     */
    void create(@Param("userNumber") Long userNumber,
                @Param("title") String title,
                @Param("imagePath") String imagePath,
                @Param("content") String content);

    /**
     * 힐링 메시지 삭제
     * @param messageId 삭제할 메시지의 고유 번호
     */
    void delete(Long messageId);

    /**
     * 힐링 메시지 게시판 썸네일 리스트 조회
     * @return 힐링 메시지 썸네일 리스트 반환
     */
    List<HealingMessageThumbNailResponse> getHealingMessageThumbNail();

    /**
     * 힐링 메시지 게시판의 힐링 메시지 상세 보기
     * @param messageId 조회하고자 하는 힐링 메시지 고유번호
     * @return 조회하고자 하는 게시글 반환
     */
    HealingMessageResponse getHealingMessage(Long messageId);

    /**
     * 내가 올린 힐링 메시지 리스트 조회
     * @param userNumber 사용자 고유 번호
     * @return 내가 올린 힐링 메시지 리스트
     */
    List<HealingMessageThumbNailResponse> getMyHealingMessageThumbNail(Long userNumber);

    /**
     * 내가 올린 힐링 메시지 상세 조회
     * @param userNumber 사용자 고유번호
     * @param messageId 조회하고자 하는 힐링 메시지 고유번호
     * @return 조회하고자 하는 게시글 반환
     */
    HealingMessageResponse getMyHealingMessage(@Param("userNumber") Long userNumber,
                                               @Param("messageId") Long messageId);

    /**
     * 동일한 messageId,userNumber 데이터가 있는지 확인
     * @param messageId 메시지 아이디
     * @param userNumber 사용자 고유 번호
     * @return
     */
    Boolean checkAlreadyCliked(@Param("messageId") Long messageId,
                               @Param("userNumber") Long userNumber);

    /**
     * 좋아요 취소
     * @param messageId 메시지 아이디
     * @param userNumber 사용자 고유 번호
     */
    void deleteLike(@Param("messageId") Long messageId,
                    @Param("userNumber") Long userNumber);

    /**
     * 좋아요 승인
     * @param messageId 메시지 아이디
     * @param userNumber 사용자 고유 번호
     */
    void permitLike(@Param("messageId") Long messageId,
                    @Param("userNumber") Long userNumber);

    /**
     * DB에서 좋아요 총량 조회
     * @param messageId 메시지 아이디
     * @return 총량 반환
     */
    Long getLikeCount(Long messageId);

    /**
     *  좋아요 누른 힐링 메시지 리스트 조회
     * @param userNumber 사용자 고유 번호
     * @return  좋아요 누른 힐링 메시지 리스트
     */
    List<HealingMessageThumbNailResponse> getMyLikeMessageList(Long userNumber);

    /**
     * 좋아요 누른 힐링 메시지 상세 조회
     * @param userNumber 사용자 고유 번호
     * @param messageId 조회하고자 하는 메시지 고유번호m
     * @return 힐링 메시지 상세 데이터 반환
     */
    HealingMessageResponse getMyLikeHealingMessage(@Param("userNumber") Long userNumber,
                                                   @Param("messageId") Long messageId);


    /* 힐링 메시지에 댓글 달기 */

    /* 힐링 메시지 수정하기 */

}
