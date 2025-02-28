package website.server.Domain.HealingProgram.AiService.AiChatBot.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChatBotMapper {

    /**
     * Ai letter DB에 저장
     * @param userNumber 사용자 고유 번호
     * @param letter AI Letter
     */
    void saveLetter(@Param("userNumber") Long userNumber,
                    @Param("letter") String letter);
}
