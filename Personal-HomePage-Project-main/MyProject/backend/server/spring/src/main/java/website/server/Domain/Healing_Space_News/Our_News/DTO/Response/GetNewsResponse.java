package website.server.Domain.Healing_Space_News.Our_News.DTO.Response;

import java.time.LocalDateTime;

public record GetNewsResponse(
        String title,
        String nickname,
        String profile_img_path,
        LocalDateTime createdDate,
        String img_path,
        String content

) {
}
