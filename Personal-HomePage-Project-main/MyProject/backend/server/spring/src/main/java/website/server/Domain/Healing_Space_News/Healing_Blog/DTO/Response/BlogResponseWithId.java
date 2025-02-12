package website.server.Domain.Healing_Space_News.Healing_Blog.DTO.Response;

public record BlogResponseWithId(

        Long id,
        String title,
        String author,
        String profile_img,
        String link,
        String thumbnail
) {
}
