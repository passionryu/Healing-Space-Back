package website.server.Domain.HealingProgram.AiService.AiRecommend.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseDetail;
import website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response.AiResponseList;
import java.util.List;

@Mapper
public interface AiRecommendMapper {

    /**
     * 추천 메시지 저장
     * @param userNumber 사용자 고유번호
     * @param content AI 반환 메시지
     */
    void saveAiRecommend(@Param("userNumber") Long userNumber,
                         @Param("title")String title,
                         @Param("content") String content
                         );

    /**
     * 추천 메시지 리스트 조회
     * @param userNumber 사용자 고유 번호
     * @return AI 답변 썸네일 리스트
     */
    List<AiResponseList> getAiRecommendList(Long userNumber);

    /**
     * 추천 메시지 상세 조회
     * @param AiRecommendMessageId 조회하고자 하는 상세 메시지 아이디
     * @return 추천 메시지 상세정보
     */
    AiResponseDetail getAiRecommendDetail(Long AiRecommendMessageId);

    /**
     * 추천 메시지 삭제
     * @param AiRecommendMessageId 삭제하고자 하는 추천 메시지 고유번호
     */
    void deleteAiRecommend(Long AiRecommendMessageId);
}
