package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response;

import java.time.LocalDateTime;

public record HealingMusicResponse(

        Long musicId,
        String title,
        String nickName,
        LocalDateTime dateTime,
        String content,
        String videoLink,
        int likes
) {
}
