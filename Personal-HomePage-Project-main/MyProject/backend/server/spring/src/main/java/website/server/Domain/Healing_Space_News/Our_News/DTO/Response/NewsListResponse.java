package website.server.Domain.Healing_Space_News.Our_News.DTO.Response;

import java.time.LocalDateTime;

public record NewsListResponse(

        Long ourNewsNumber,
        String title,
        String nickName,
        LocalDateTime createdDate
) {
}
