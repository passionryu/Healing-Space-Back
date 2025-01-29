package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
}
