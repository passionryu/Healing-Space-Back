package website.server.Domain.HealingProgram.AiService.AiSnapShot.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponseDetail;
import website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response.AiResponseList;

import java.util.List;

@Mapper
public interface AiSnapShotMapper {

    /**
     * 레포트 저장
     * @param userNumber 사용자 고유 번호
     * @param aiResponse ai 응답 메시지
     */
    void saveAiReport(@Param("userNumber") Long userNumber,
                      @Param("title") String title ,
                      @Param("aiResponse") String aiResponse);

    /**
     * 응답 메시지 리스트 조회
     * @param userNumber 사용자 고유 번호
     * @return 응답 메시지 리스트 반환
     */
    List<AiResponseList> getAiResponseList(Long userNumber);

    /**
     * 응답 메시지 상세 조회
     * @param aiResponseNumber 상세조회 하고자 하는 응답 메시지 고유 번호
     * @return 응답 메시지 상세 데이터 반환
     */
    AiResponseDetail getAiResponseDetail(Long aiResponseNumber);
}
