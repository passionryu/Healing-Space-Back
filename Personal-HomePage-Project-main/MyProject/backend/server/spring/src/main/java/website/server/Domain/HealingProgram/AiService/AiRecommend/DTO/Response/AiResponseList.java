package website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseList(
        String title,
        LocalDateTime dateTime
) {
}
