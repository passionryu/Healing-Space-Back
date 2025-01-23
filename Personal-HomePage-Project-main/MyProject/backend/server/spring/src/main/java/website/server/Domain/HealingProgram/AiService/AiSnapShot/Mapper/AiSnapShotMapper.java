package website.server.Domain.HealingProgram.AiService.AiSnapShot.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AiSnapShotMapper {

    /**
     * 레포트 저장
     * @param userNumber 사용자 고유 번호
     * @param aiResponse ai 응답 메시지
     */
    void saveAiReport(@Param("userNumber") Long userNumber,
                      @Param("aiResponse") String aiResponse);
}
