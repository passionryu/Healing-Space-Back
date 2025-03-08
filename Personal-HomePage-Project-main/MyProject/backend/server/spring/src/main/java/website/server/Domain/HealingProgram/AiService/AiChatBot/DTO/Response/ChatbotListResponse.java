package website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response;

import java.time.LocalDateTime;

public record ChatbotListResponse(
        Long chat_id,
        LocalDateTime writtenDate
) {
}
