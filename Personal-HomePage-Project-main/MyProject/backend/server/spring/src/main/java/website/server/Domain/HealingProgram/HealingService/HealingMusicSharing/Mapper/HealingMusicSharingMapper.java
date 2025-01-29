package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
