package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response;

import java.time.LocalDateTime;

public record HealingMusicListResponse(
        Long musicId,
        String img,
        String title,
        String nickName,
        LocalDateTime dateTime
) {
}
