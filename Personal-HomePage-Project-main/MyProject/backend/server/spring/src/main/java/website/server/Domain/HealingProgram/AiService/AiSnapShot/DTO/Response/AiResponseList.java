package website.server.Domain.HealingProgram.AiService.AiSnapShot.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseList(
        Long aiResponseNumber,
        String title,
        LocalDateTime dateTime
) {
}
