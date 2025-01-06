package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response;

import java.time.LocalDateTime;

public record HealingMessageThumbNailResponse(

        String title,
        String nickname,
        LocalDateTime createdDate
) {
}
