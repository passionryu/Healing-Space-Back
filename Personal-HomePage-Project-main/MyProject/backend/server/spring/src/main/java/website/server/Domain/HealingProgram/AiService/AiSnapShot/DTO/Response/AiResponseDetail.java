package website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseDetail(
        String title,
        LocalDateTime dateTime,
        String aiResponse
) {
}
