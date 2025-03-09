package website.server.Domain.HealingProgram.AiService.AiChatBot.DTO.Response;

import java.time.LocalDateTime;

public record ChatBotDetaillResponse(
   LocalDateTime writtenDate,
   String letter
) {
}
