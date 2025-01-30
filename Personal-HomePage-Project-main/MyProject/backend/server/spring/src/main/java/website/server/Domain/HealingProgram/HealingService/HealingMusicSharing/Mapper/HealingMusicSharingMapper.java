package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicCommentResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicListResponse;
import website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response.HealingMusicResponse;

import java.util.List;

@Mapper
public interface HealingMusicSharingMapper {

    /**
     * 힐링 뮤직 쉐어링
     * @param userNumber
     * @param title
     * @param content
     * @param thumbnailUrl
     * @param videoLink
     */
    void HealingMusicSharing(@Param("userNumber") Long userNumber,
                             @Param("title") String title,
                             @Param("content")String content,
                             @Param("imagePath")String thumbnailUrl,
                             @Param("videoLink")String videoLink);

    /**
     * 게시판에서 힐링 뮤직 리스트 조회
     * @return
     */
    List<HealingMusicListResponse> getHealingMusicList();

    /**
     * 게시판에서 힐링 뮤직 상세 조회
     * @param musicId
     * @return
     */
    HealingMusicResponse getHealingMusic(String musicId);

    /**
     * 동일한 messageId,userNumber 데이터가 있는지 확인
     * @param musicId 음악 게시글 고유번호
     * @param userNumber 사용자 고유 번호
     * @return
     */
    Boolean checkAlreadyCliked(@Param("musicId") Long musicId,
                               @Param("userNumber") Long userNumber);

    /**
     * 좋아요 취소
     * @param musicId 메시지 아이디
     * @param userNumber 사용자 고유 번호
     */
    void deleteLike(@Param("musicId") Long musicId,
                    @Param("userNumber") Long userNumber);

    /**
     * 좋아요 승인
     * @param musicId 메시지 아이디
     * @param userNumber 사용자 고유 번호
     */
    void permitLike(@Param("musicId") Long musicId,
                    @Param("userNumber") Long userNumber);

    /**
     * DB에서 좋아요 총량 조회
     * @param musicId 메시지 아이디
     * @return 총량 반환
     */
    Integer getLikeCount(Long musicId);

    /**
     * 힐링 뮤직 댓글 달기
     * @param musicId
     * @param userNumber
     * @param content
     */
    void postComment(@Param("musicId") Long musicId,
                     @Param("userNumber") Long userNumber,
                     @Param("content") String content);

    /**
     * 힐링 뮤직 댓글 조회
     * @param musicId
     * @return
     */
    List<HealingMusicCommentResponse> getComment(Long musicId);

    /**
     * 힐링 뮤직 댓글 삭제
     * @param userNumber
     * @param commentId
     */
    void deleteComment( @Param("userNumber") Long userNumber,
                        @Param("commentId") Long commentId);
}
