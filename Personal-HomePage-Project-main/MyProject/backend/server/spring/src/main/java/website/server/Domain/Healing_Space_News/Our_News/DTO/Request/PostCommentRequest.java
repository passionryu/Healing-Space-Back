package website.server.Domain.Healing_Space_News.Our_News.DTO.Request;

public record PostCommentRequest(

        Long ourNewsNumber,
        String content

) {
}
