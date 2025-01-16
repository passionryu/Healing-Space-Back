package website.server.Domain.Healing_Space_News.Our_News.DTO.Response;

import java.time.LocalDateTime;

public record OurNewsCommentResponse(

        Long commentId,
        String profile_image_path,
        String nickname,
        String content,
        LocalDateTime createdDate

) {
}
