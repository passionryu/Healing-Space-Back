package website.server.Domain.HealingProgram.HealingService.HealingMusicSharing.DTO.Response;

import java.time.LocalDateTime;

public record HealingMusicCommentResponse(

        Long commentId,
        String profileImagePath,
        String nickName,
        LocalDateTime dateTime,
        String content

) {
}
