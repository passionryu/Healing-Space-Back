package website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response;

import java.time.LocalDate;

public record BlogResponse(
        Long blogId,
        String title,
        String author,
        String profile_img,
        LocalDate date,
        String link,
        String thumbnail

) {
}
