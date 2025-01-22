package website.server.Domain.HealingProgram.AiRecommend.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseDetail(
        String title,
        String content,
        LocalDateTime dateTime
) {
}
