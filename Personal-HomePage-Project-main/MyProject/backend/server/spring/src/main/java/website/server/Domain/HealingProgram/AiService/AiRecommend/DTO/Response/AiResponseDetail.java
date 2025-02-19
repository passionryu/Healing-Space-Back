package website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseDetail(
        String title,
        String content,
        LocalDateTime dateTime,
        String response
) {
}
