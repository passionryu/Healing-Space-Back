package website.server.Domain.HealingProgram.AiService.AiRecommend.DTO.Response;

import java.time.LocalDateTime;

public record AiResponseList(

        Long AiRecommendMessageId,
        String title,
        LocalDateTime dateTime
) {
}
