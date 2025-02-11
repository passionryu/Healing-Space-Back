package website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response;

import java.time.LocalDate;

public record BlogResponse(

        String title,
        String author,
        String link,
        String profile_img,
        String thumbnail

) {
}
