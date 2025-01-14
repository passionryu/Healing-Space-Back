package website.server.Domain.HealingProgram.HealingService.HealingMessageSharing.DTO.Response;

import java.time.LocalDateTime;

public record CommentResponse(

        Long commentId,
        String profile_image_path,
        String nickname,
        String content,
        LocalDateTime createdDate
) {
}
