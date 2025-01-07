package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper;

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

    /* 내가 올린 힐링 메시지 리스트 */

    /* 내가 올린 힐링 메시지 상세 조회 */

    /* 힐링 메시지 좋아요 누르기 */

    /* 좋아요 누른 힐링 메시지 리스트 조회 */

    /* 좋아요 누른 힐링 메시지 상세 조회 */

    /* 힐링 메시지에 댓글 달기 */

    /* 힐링 메시지 수정하기 */

}
