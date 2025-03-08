package website.server.Domain.HealingProgram.AiService.AiChatBot.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response.ChatbotListResponse;

import java.util.List;

@Mapper
public interface ChatBotMapper {

    /**
     * Ai letter DB에 저장
     * @param userNumber 사용자 고유 번호
     * @param letter AI Letter
     */
    void saveLetter(@Param("userNumber") Long userNumber,
                    @Param("letter") String letter);

    /**
     * 챗봇 기록 리스트 조회
     * @param userNumber
     * @return 챗봇 기록 리스트 반환
     */
    List<ChatbotListResponse> getList(@Param("userNumber") Long userNumber);
}
