package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    /* 힐링 메시지 삭제 */

    /* 내가 올린 힐링 메시지 리스트 */

    /* 내가 올린 힐링 메시지 상세 조회 */

    /* 힐링 메시지 좋아요 누르기 */

    /* 좋아요 누른 힐링 메시지 리스트 조회 */

    /* 좋아요 누른 힐링 메시지 상세 조회 */

    /* 힐링 메시지에 댓글 달기 */

    /* 힐링 메시지 수정하기 */

}
